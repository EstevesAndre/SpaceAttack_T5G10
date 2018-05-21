package com.spaceattack.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.spaceattack.game.model.Bullet;
import com.spaceattack.game.model.Game;
import com.spaceattack.game.model.GameObject;
import com.spaceattack.game.model.Ship;
import com.spaceattack.game.view.GameView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.sin;


public class GameController implements ContactListener {

    /**
     * The arena width in meters.
     */
    public static final int ARENA_WIDTH = 1000;

    /**
     * The arena height in meters.
     */
    public static final int ARENA_HEIGHT = 500;

    /**
     * The rotation speed in radians per second.
     */
    private static final float ROTATION_SPEED = 5f;

    /**
     * The singleton instance
     */
    private static GameController instance;

    /**
     * The world controlled by this controller.
     */
    private final World world;

    /**
     * Accumulator used to calculate the simulation step.
     */
    private float accumulator;

    /**
     * A list of bodies to be removed
     */
    private List<Body> toRemove;

    /**
     * The user spaceship body
     */
    private final UserShipBody shipBody;

    /**
     * Creates a new GameController that controls the physics of a certain GameModel.
     *
     */
    private GameController() {
        world = new World(new Vector2(0, 0), true);

        toRemove = new ArrayList<Body>();

        shipBody = new UserShipBody(world, Game.getInstance().getUserShip());

        Ship s = new Ship(520, 270, 0, 2, 1500f, 0.8f, 20);

        Game.getInstance().addEnemyShip(s);

        new EnemyShipBody(world, s);

        world.setContactListener(this);
    }

    /**
     * Returns a singleton instance of a game controller
     *
     * @return the singleton instance
     */
    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }

    /**
     * Calculates the next physics step of duration delta (in seconds).
     *
     * @param delta The size of this physics step in seconds.
     */
    public void update(float delta) {

        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1/60f) {
            world.step(1/60f, 6, 2);
            accumulator -= 1/60f;
        }

        decreaseCooldown(delta);

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
            ((GameObject) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
            if (body.getUserData() instanceof Ship)
            {
                checkOutOfBounds(body);
                if(((Ship) body.getUserData()).getBulletSpeed() < 5000)
                {
                    attack(body);
                }
            }
            ((GameObject) body.getUserData()).setRotation(body.getAngle());
        }

        removeMarkedObjects();
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    /**
     * Decreases the fire cooldown of all ships.
     * Occurs periodically
     *
     * @param delta time elapsed
     */
    private void decreaseCooldown(float delta)
    {
        ((Ship) (shipBody.getBody().getUserData())).decreaseCooldown(delta);

        for(Ship s : Game.getInstance().getEnemyShips())
        {
            s.decreaseCooldown(delta);
        }
    }

    /**
     * A contact between two objects was detected
     *
     * @param contact the detected contact
     */
    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if (bodyA.getUserData() instanceof Bullet)
            bulletCollision(bodyA);
        if (bodyB.getUserData() instanceof Bullet)
            bulletCollision(bodyB);

        if (bodyA.getUserData() instanceof Bullet && bodyB.getUserData() instanceof Ship)
            bulletShipCollision(bodyA, bodyB);
        if (bodyA.getUserData() instanceof Ship && bodyB.getUserData() instanceof Bullet)
              bulletShipCollision(bodyB, bodyA);

    }

    /**
     * A bullet colided with something. Lets remove it.
     *
     * @param bulletBody the bullet that colided
     */
    private void bulletCollision(Body bulletBody) {
        Game.getInstance().remove((Bullet)bulletBody.getUserData());
        toRemove.add(bulletBody);
    }

    /**
     * A bullet collided with an ship. Reduce its health
     * @param bulletBody the bullet that collided
     * @param shipBody the ship that collided
     */
    private void bulletShipCollision(Body bulletBody, Body shipBody) {
        shipBody.setLinearVelocity(0, 0);
        shipBody.setAngularVelocity(0);

        ((Ship) shipBody.getUserData()).decreaseHealth();

        if( ((Ship) shipBody.getUserData()).getHealth() == 0)
        {
            Game.getInstance().remove((GameObject) shipBody.getUserData());
            toRemove.add(shipBody);
        }
    }

    /**
     * Rotates the spaceship left. The rotation takes into consideration the
     * constant rotation speed and the delta for this simulation step.
     *
     * @param delta Duration of the rotation in seconds.
     */
    public void rotateLeft(float delta) {
        shipBody.getBody().setTransform(shipBody.getBody().getPosition().x, shipBody.getBody().getPosition().y, shipBody.getBody().getAngle() + ROTATION_SPEED * delta);
        shipBody.getBody().setAngularVelocity(0);
    }

    /**
     * Rotates the spaceship right. The rotation takes into consideration the
     * constant rotation speed and the delta for this simulation step.
     *
     * @param delta Duration of the rotation in seconds.
     */
    public void rotateRight(float delta) {
        shipBody.getBody().setTransform(shipBody.getBody().getPosition().x, shipBody.getBody().getPosition().y, shipBody.getBody().getAngle() - ROTATION_SPEED * delta);
        shipBody.getBody().setAngularVelocity(0);
    }

    /**
     * Acceleratesins the spaceship. The acceleration takes into consideration the
     * constant acceleration force and the delta for this simulation step.
     *
     * @param delta Duration of the rotation in seconds.
     */
    public void accelerate(float delta) {
        shipBody.getBody().applyForceToCenter(-(float) sin(shipBody.getBody().getAngle()) * ((Ship) (shipBody.getBody().getUserData())).getSpeed() * delta, (float) cos(shipBody.getBody().getAngle()) * ((Ship) (shipBody.getBody().getUserData())).getSpeed() * delta, true);
    }

    /**
     * Fires a bullet from user spaceship if fire rate has passed
     */
    public void fire() {

        if(((Ship) (shipBody.getBody().getUserData())).getFireCooldown() <= 0) {
            Bullet b = (((Ship) (shipBody.getBody().getUserData())).fire());
            Game.getInstance().addBullet(b);
            if (b.getSpeed() >= 5000) {
                UserBulletBody bBody = new UserBulletBody(world, b);
                bBody.setLinearVelocity(b.getSpeed());

            }
        }
    }

    /**
     * Rotates enemy ship body to point to user ship, fires and moves
     */
    private void attack(Body body)
    {
        float angle = new Vector2(Game.getInstance().getUserShip().getX(), Game.getInstance().getUserShip().getY()).sub(body.getPosition()).angleRad() - (float)Math.PI/2 ;
        ((Ship) body.getUserData()).setRotation(angle);
        body.setTransform(body.getPosition(), angle);

        if(((Ship) (body.getUserData())).getFireCooldown() <= 0) {
            Bullet b = (((Ship) (body.getUserData())).fire());
            Game.getInstance().addBullet(b);
            EnemyBulletBody bBody = new EnemyBulletBody(world, b);
            bBody.setLinearVelocity(b.getSpeed());
        }


    }

    /**
     * Marks bullets out of the viewport
     */
    public void markBullets() {
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body body : bodies) {
            if (body.getUserData() instanceof Bullet && (GameView.isOutOfViewport((GameObject) body.getUserData()))) {
                Game.getInstance().remove((GameObject) body.getUserData());
                toRemove.add(body);
            }
        }
    }

    /**
     * Removes marked objects
     */
    private void removeMarkedObjects()
    {
        for (Body body : toRemove) {
            world.destroyBody(body);
        }

        toRemove.clear();
    }

    /**
     * Checks if body is out of arena and stops further movement
     *
     * @param body body to be checked
     */
    private void checkOutOfBounds(Body body)
    {
        boolean oob = false;
        if(((GameObject)body.getUserData()).getX() > ARENA_WIDTH)
        {
            oob = true;
            ((GameObject)body.getUserData()).setPosition(ARENA_WIDTH, ((GameObject)body.getUserData()).getY());
        }

        if(((GameObject)body.getUserData()).getX() < 0)
        {
            oob = true;
            ((GameObject)body.getUserData()).setPosition(0, ((GameObject)body.getUserData()).getY());
        }

        if(((GameObject)body.getUserData()).getY() > ARENA_HEIGHT)
        {
            oob = true;
            ((GameObject)body.getUserData()).setPosition(((GameObject)body.getUserData()).getX(), ARENA_HEIGHT);
        }

        if(((GameObject)body.getUserData()).getY() < 0)
        {
            oob = true;
            ((GameObject)body.getUserData()).setPosition(((GameObject)body.getUserData()).getX(), 0);
        }

        if(oob)
        {
            body.setLinearVelocity(0f, 0f);
            body.setAngularVelocity(0f);
        }
    }

}
