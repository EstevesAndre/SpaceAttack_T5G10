package com.spaceattack.game.controller;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.spaceattack.game.model.GameObject;

import static com.spaceattack.game.view.GameView.PIXEL_TO_METER;

public class ObjectBody {
    final static short USER_SHIP_BODY = 0x0001;
    final static short ENEMY_SHIP_BODY = 0x0002;
    final static short USER_BULLET_BODY = 0x0003;
    final static short ENEMY_BULLET_BODY = 0x0004;
    final static short POWER_UP_BODY = 0x0005;

    /**
     * The Box2D body
     */
    final Body body;

    /**
     * Constructor of a body
     *
     * @param world The world in which the body will interact
     * @param obj The corresponding model
     */
    ObjectBody(World world, GameObject obj) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(obj.getX(), obj.getY());
        bodyDef.angle = obj.getRotation();

        body = world.createBody(bodyDef);
        body.setUserData(obj);
    }

    /**
     * Creates a fixture for the body
     * @param body The corresponding body
     * @param vertexes The vertexes delimiting the fixture
     * @param width The width of the fixture
     * @param height The height of the fixture
     * @param density The density of the fixture
     * @param friction The friction of the fixture
     * @param restitution The restitution of the fixture
     * @param category ID for the fixture for collision purposes
     * @param mask Filter for collision purposes
     */
    final void createFixture(Body body, float[] vertexes, int width, int height, float density, float friction, float restitution, short category, short mask) {

        // Transform pixels into meters, center and invert the y-coordinate
        for (int i = 0; i < vertexes.length; i++) {
            if (i % 2 == 0) vertexes[i] -= width / 2;   // center the vertex x-coordinate
            if (i % 2 != 0) vertexes[i] -= height / 2;  // center the vertex y-coordinate

            if (i % 2 != 0) vertexes[i] *= -1;          // invert the y-coordinate

            vertexes[i] *= PIXEL_TO_METER;              // scale from pixel to meter
        }

        PolygonShape polygon = new PolygonShape();
        polygon.set(vertexes);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;

        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        fixtureDef.filter.categoryBits = category;
        fixtureDef.filter.maskBits = mask;

        body.createFixture(fixtureDef);

        polygon.dispose();
    }

    /**
     * Gets the body instance
     *
     * @return the body
     */
    Body getBody()
    {
        return body;
    }

    /**
     * Sets the angular velocity of this object in the direction it is rotated.
     *
     * @param velocity the new linear velocity angle for this body
     */
    public void setLinearVelocity(float velocity) {
        body.setLinearVelocity((float)(velocity * -Math.sin(body.getAngle())), (float) (velocity * Math.cos(body.getAngle())));
    }
}
