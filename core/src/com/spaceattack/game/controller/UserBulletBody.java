package com.spaceattack.game.controller;

import com.badlogic.gdx.physics.box2d.World;
import com.spaceattack.game.model.Bullet;

public class UserBulletBody extends ObjectBody{
    /**
     * Constructor of a user bullet body
     *
     * @param world The world in which the body will interact
     * @param obj The corresponding model
     */
    public UserBulletBody(World world, Bullet obj) {
        super(world, obj);

        float density = 0.1f, friction = 0.2f, restitution = 0.5f;
        int width = 10, height = 35;

        // Body
        createFixture(body, new float[]{
                3,0, 6,0, 9,3, 9,9, 0,9, 0,3
        }, width, height, density, friction, restitution, USER_BULLET_BODY, ENEMY_SHIP_BODY);

    }
}
