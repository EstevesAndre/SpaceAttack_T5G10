package com.spaceattack.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.spaceattack.game.model.Game;
import com.spaceattack.game.model.GameObject;


public class GameController implements ContactListener {

    /**
     * The arena width in meters.
     */
    public static final int ARENA_WIDTH = 10000;

    /**
     * The arena height in meters.
     */
    public static final int ARENA_HEIGHT = 5000;

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
     * The user spaceship body
     */
    private final UserShipBody shipBody;

    /**
     * Creates a new GameController that controls the physics of a certain GameModel.
     *
     */
    private GameController() {
        world = new World(new Vector2(0, 0), true);

        shipBody = new UserShipBody(world, Game.getInstance().getUserShip());

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

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
            ((GameObject) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
            ((GameObject) body.getUserData()).setRotation(body.getAngle());
        }
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
     * A contact between two objects was detected
     *
     * @param contact the detected contact
     */
    @Override
    public void beginContact(Contact contact) {


    }
}
