package com.spaceattack.game.controller;

import com.badlogic.gdx.physics.box2d.World;
import com.spaceattack.game.model.Bullet;

public class EnemyBulletBody extends ObjectBody{
    /**
     * Constructor of a enemy bullet body
     *
     * @param world The world in which the body will interact
     * @param obj The corresponding model
     */
    public EnemyBulletBody(World world, Bullet obj) {
        super(world, obj);

        float density = 0.1f, friction = 0.2f, restitution = 0.5f;
        int width = 20, height = 73;

        // Body
        createFixture(body, new float[]{
                0,0, 19,0, 19,72, 0,72
        }, width, height, density, friction, restitution, ENEMY_BULLET_BODY, USER_SHIP_BODY);

    }
}
