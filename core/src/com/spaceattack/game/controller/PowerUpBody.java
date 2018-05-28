package com.spaceattack.game.controller;

import com.badlogic.gdx.physics.box2d.World;
import com.spaceattack.game.model.Bullet;
import com.spaceattack.game.model.PowerUp;

/**
 * Power Up Body
 */
public class PowerUpBody extends ObjectBody{
    /**
     * Constructor of a power up body
     *
     * @param world The world in which the body will interact
     * @param obj The corresponding model
     */
    public PowerUpBody(World world, PowerUp obj) {
        super(world, obj);

        float density = 0.1f, friction = 0.2f, restitution = 0.5f;
        int width = 100, height = 100;

        // Body
        createFixture(body, new float[]{
                0,0, 99,0, 99,99, 0,99
        }, width, height, density, friction, restitution, POWER_UP_BODY, USER_SHIP_BODY);

    }
}
