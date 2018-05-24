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
import com.spaceattack.game.model.Portal;
import com.spaceattack.game.model.PowerUp;
import com.spaceattack.game.model.Ship;
import com.spaceattack.game.view.GameView;

import java.util.Collections;


import static com.spaceattack.game.model.PowerUp.HEALTH_TYPE;
import static com.spaceattack.game.model.PowerUp.SHIELD_TYPE;
import static com.spaceattack.game.model.PowerUp.TRIPLE_SHOT_TYPE;
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
    private static final float ROTATION_SPEED = 2.5f;

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
     * Periodic time counter for ship spawning
     */
    private float spawnTimer = 0;

    /**
     * Periodic time counter for score increase
     */
    private float scoreTimer = 0;

    /**
     * The user spaceship body
     */
    private final UserShipBody userShip;

    /**
     * Creates a new GameController that controls the physics of a certain GameModel.
     */
    private GameController() {
        world = new World(new Vector2(0, 0), true);

        userShip = new UserShipBody(world, Game.getInstance().getUserShip());

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
        while (accumulator >= 1 / 60f) {
            world.step(1 / 60f, 6, 2);
            accumulator -= 1 / 60f;
        }

        increaseScore(delta);

        Game.getInstance().getUserShip().reduceShield(delta);
        decreaseCooldown(delta);

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
            ((GameObject) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
            if (body.getUserData() instanceof Ship) {
                if (((Ship) body.getUserData()).isHit()) {
                    ((Ship) body.getUserData()).decreaseHealth();
                    ((Ship) body.getUserData()).setHitStatus(false);
                }

                if (((Ship) body.getUserData()).isHealed()) {
                    ((Ship) body.getUserData()).increaseHealth();
                    ((Ship) body.getUserData()).setHealedStatus(false);
                }

                if (((Ship) body.getUserData()).getHealth() == 0) {
                    if (((Ship) body.getUserData()).getBulletSpeed() < 5000) {
                        Game.getInstance().addScore(((Ship) body.getUserData()).getSpeed() - 1000);
                        ((Ship) body.getUserData()).destroy();
                        Game.getInstance().removeEnemyShip((Ship) body.getUserData());

                        generatePowerUp(body);
                    } else {
                        Game.getInstance().restart();
                        instance = new GameController();
                        return;
                    }

                } else {
                    checkOutOfBounds(body);
                    if (((Ship) body.getUserData()).getBulletSpeed() < 5000) {
                        attack(body, delta);
                    }
                }
            }
            ((GameObject) body.getUserData()).setRotation(body.getAngle());
        }
        removeMarkedObjects();
        spawnShips(delta);
    }

    /**
     * Possibly spawns a power up in the position of a destroyed enemy ship
     */
    private void generatePowerUp(Body body) {
        double prob = Math.random();

        if (prob > 0.4)
            return;

        if (((Ship) body.getUserData()).getSpeed() == 3500) {
            PowerUp p = new PowerUp(((Ship) body.getUserData()).getX(), ((Ship) body.getUserData()).getY(), 0, HEALTH_TYPE);
            Game.getInstance().addPowerUp(p);
            new PowerUpBody(world, p);
        } else if (((Ship) body.getUserData()).getSpeed() == 3000) {
            PowerUp p = new PowerUp(((Ship) body.getUserData()).getX(), ((Ship) body.getUserData()).getY(), 0, TRIPLE_SHOT_TYPE);
            Game.getInstance().addPowerUp(p);
            new PowerUpBody(world, p);

        } else if (((Ship) body.getUserData()).getSpeed() == 2500) {
            PowerUp p = new PowerUp(((Ship) body.getUserData()).getX(), ((Ship) body.getUserData()).getY(), 0, SHIELD_TYPE);
            Game.getInstance().addPowerUp(p);
            new PowerUpBody(world, p);
        }
    }

    /**
     * Increases score if one second passed since last increase
     *
     * @param delta time elapsed since last check
     */
    private void increaseScore(float delta) {
        scoreTimer += delta;

        if (scoreTimer >= 1) {
            Game.getInstance().addScore(10);
            scoreTimer = 0;
        }
    }

    /**
     * Spawns up to a enemy ship in each portal
     */
    private void spawnShips(float delta) {
        spawnTimer += delta;
        Collections.shuffle(Game.getInstance().getPortals());
        for (Portal p : Game.getInstance().getPortals()) {
            if (Game.getInstance().getEnemyShips().size() < 3) {
                spawnShipAtPortal(p);
            } else if (spawnTimer >= 4 && Game.getInstance().getEnemyShips().size() < 10) {
                spawnShipAtPortal(p);
                spawnTimer = 0;
            }
        }
    }

    /**
     * Spawns a enemy ship in a specific portal
     *
     * @param p the chosen portal
     */
    private void spawnShipAtPortal(Portal p) {
        Ship s;
        if (Game.getInstance().getScore() == 0) {
            s = new Ship(p.getX(), p.getY(), 0, 2, 1500f, 1.6f, 30);
        } else {
            double prob = Math.random() * 300 / Game.getInstance().getScore();

            if (prob > 0.1) {
                s = new Ship(p.getX(), p.getY(), 0, 2, 1500f, 1.6f, 30);
            } else if (prob > 0.075) {
                s = new Ship(p.getX(), p.getY(), 0, 3, 2000f, 1.4f, 35);
            } else if (prob > 0.05) {
                s = new Ship(p.getX(), p.getY(), 0, 4, 2500f, 1.2f, 35);
            } else if (prob > 0.025) {
                s = new Ship(p.getX(), p.getY(), 0, 5, 3000f, 1f, 40);
            } else {
                s = new Ship(p.getX(), p.getY(), 0, 6, 3500f, 0.8f, 40);
            }
        }

        Game.getInstance().addEnemyShip(s);
        new EnemyShipBody(world, s);
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
    private void decreaseCooldown(float delta) {
        ((Ship) (userShip.getBody().getUserData())).decreaseCooldown(delta);

        for (Ship s : Game.getInstance().getEnemyShips()) {
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

        if (bodyA.getUserData() instanceof Bullet && bodyB.getUserData() instanceof Ship)
            bulletShipCollision(bodyA, bodyB);
        if (bodyA.getUserData() instanceof Ship && bodyB.getUserData() instanceof Bullet)
            bulletShipCollision(bodyB, bodyA);

        if (bodyA.getUserData() instanceof PowerUp && bodyB.getUserData() instanceof Ship)
            powerUpShipCollision(bodyA, bodyB);
        if (bodyA.getUserData() instanceof Ship && bodyB.getUserData() instanceof PowerUp)
            powerUpShipCollision(bodyB, bodyA);

    }

    /**
     * A power up was collected by a ship.
     *
     * @param powerUpBody the power up that collided
     * @param shipBody    the ship that collided
     */
    private void powerUpShipCollision(Body powerUpBody, Body shipBody) {
        switch (((PowerUp) powerUpBody.getUserData()).getType()) {
            case HEALTH_TYPE: {
                ((Ship) shipBody.getUserData()).setHealedStatus(true);
                break;
            }
            case SHIELD_TYPE: {
                ((Ship) shipBody.getUserData()).shield();
                break;
            }
            case TRIPLE_SHOT_TYPE: {
                ((Ship) shipBody.getUserData()).setTripleFire(true);
                break;
            }
        }

        ((PowerUp) powerUpBody.getUserData()).destroy();
        Game.getInstance().removePowerUp((PowerUp) powerUpBody.getUserData());
    }

    /**
     * A bullet collided with an ship. Reduce its health
     *
     * @param bulletBody the bullet that collided
     * @param shipBody   the ship that collided
     */
    private void bulletShipCollision(Body bulletBody, Body shipBody) {
        ((Bullet) bulletBody.getUserData()).destroy();
        Game.getInstance().removeBullet((Bullet) bulletBody.getUserData());

        shipBody.setLinearVelocity(0, 0);
        shipBody.setAngularVelocity(0);

        if (((Ship) shipBody.getUserData()).getShield() == 0) {
            ((Ship) shipBody.getUserData()).setHitStatus(true);
            ((Ship) shipBody.getUserData()).setTripleFire(false);
        }
    }

    /**
     * Rotates the spaceship left. The rotation takes into consideration the
     * constant rotation speed and the delta for this simulation step.
     *
     * @param delta Duration of the rotation in seconds.
     */
    public void rotateLeft(float delta) {
        userShip.getBody().setTransform(userShip.getBody().getPosition().x, userShip.getBody().getPosition().y, userShip.getBody().getAngle() + ROTATION_SPEED * delta);
        userShip.getBody().setAngularVelocity(0);
    }

    /**
     * Rotates the spaceship right. The rotation takes into consideration the
     * constant rotation speed and the delta for this simulation step.
     *
     * @param delta Duration of the rotation in seconds.
     */
    public void rotateRight(float delta) {
        userShip.getBody().setTransform(userShip.getBody().getPosition().x, userShip.getBody().getPosition().y, userShip.getBody().getAngle() - ROTATION_SPEED * delta);
        userShip.getBody().setAngularVelocity(0);
    }

    /**
     * Acceleratesins the spaceship. The acceleration takes into consideration the
     * constant acceleration force and the delta for this simulation step.
     *
     * @param delta Duration of the rotation in seconds.
     */
    public void accelerate(float delta) {
        userShip.getBody().applyForceToCenter(-(float) sin(userShip.getBody().getAngle()) * ((Ship) (userShip.getBody().getUserData())).getSpeed() * delta, (float) cos(userShip.getBody().getAngle()) * ((Ship) (userShip.getBody().getUserData())).getSpeed() * delta, true);
    }

    /**
     * Fires a bullet from user spaceship if fire rate has passed
     */
    public void fire() {

        if (((Ship) (userShip.getBody().getUserData())).getFireCooldown() <= 0) {
            Bullet b = (((Ship) (userShip.getBody().getUserData())).fire());
            Game.getInstance().addBullet(b);
            UserBulletBody bBody = new UserBulletBody(world, b);
            bBody.setLinearVelocity(b.getSpeed());

            if(Game.getInstance().getUserShip().getTripleFire())
            {
                Bullet b1 = new Bullet(b.getX() - (float)cos(b.getRotation()) * 3, b.getY() - (float)sin(b.getRotation()) * 3, b.getRotation() - 25, b.getSpeed());
                Game.getInstance().addBullet(b1);
                UserBulletBody bBody1 = new UserBulletBody(world, b1);
                bBody1.setLinearVelocity(b1.getSpeed());

                Bullet b2 = new Bullet(b.getX() - (float)cos(b.getRotation()) * -3, b.getY() - (float)sin(b.getRotation()) * -3, b.getRotation() + 25, b.getSpeed());
                Game.getInstance().addBullet(b2);
                UserBulletBody bBody2 = new UserBulletBody(world, b2);
                bBody2.setLinearVelocity(b2.getSpeed());
            }
        }
    }

    /**
     * Rotates enemy ship body to point to user ship, fires and moves
     *
     * @param body  the enemy ship body
     * @param delta The size of this physics step in seconds.
     */
    private void attack(Body body, float delta) {
        float angle = new Vector2(Game.getInstance().getUserShip().getX(), Game.getInstance().getUserShip().getY()).sub(body.getPosition()).angleRad() - (float) Math.PI / 2;
        ((Ship) body.getUserData()).setRotation(angle);
        body.setTransform(body.getPosition(), angle);

        if (new Vector2(((Ship) body.getUserData()).getX(), ((Ship) body.getUserData()).getY()).dst(Game.getInstance().getUserShip().getX(), Game.getInstance().getUserShip().getY()) > 30) {
            body.setLinearVelocity(-(float) sin(body.getAngle()) * ((Ship) (body.getUserData())).getSpeed() * delta, (float) cos(body.getAngle()) * ((Ship) (body.getUserData())).getSpeed() * delta);
        } else {
            if (((Ship) (body.getUserData())).getFireCooldown() <= 0) {
                Bullet b = (((Ship) (body.getUserData())).fire());
                Game.getInstance().addBullet(b);
                EnemyBulletBody bBody = new EnemyBulletBody(world, b);
                bBody.setLinearVelocity(b.getSpeed());
            }

            body.setLinearVelocity(0, 0);
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
                ((Bullet) body.getUserData()).destroy();
                Game.getInstance().removeBullet((Bullet) body.getUserData());
            }
        }
    }

    /**
     * Removes marked objects
     */
    private void removeMarkedObjects() {
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body body : bodies) {
            if (((GameObject) body.getUserData()).isMarked())
                world.destroyBody(body);
        }

    }

    /**
     * Checks if body is out of arena and stops further movement
     *
     * @param body body to be checked
     */
    private void checkOutOfBounds(Body body) {
        boolean oob = false;
        if (((GameObject) body.getUserData()).getX() > ARENA_WIDTH) {
            oob = true;
            ((GameObject) body.getUserData()).setPosition(ARENA_WIDTH, ((GameObject) body.getUserData()).getY());
        }

        if (((GameObject) body.getUserData()).getX() < 0) {
            oob = true;
            ((GameObject) body.getUserData()).setPosition(0, ((GameObject) body.getUserData()).getY());
        }

        if (((GameObject) body.getUserData()).getY() > ARENA_HEIGHT) {
            oob = true;
            ((GameObject) body.getUserData()).setPosition(((GameObject) body.getUserData()).getX(), ARENA_HEIGHT);
        }

        if (((GameObject) body.getUserData()).getY() < 0) {
            oob = true;
            ((GameObject) body.getUserData()).setPosition(((GameObject) body.getUserData()).getX(), 0);
        }

        if (oob) {
            body.setLinearVelocity(0f, 0f);
            body.setAngularVelocity(0f);
        }
    }

}
