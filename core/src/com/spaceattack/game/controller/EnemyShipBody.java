package com.spaceattack.game.controller;

import com.badlogic.gdx.physics.box2d.World;
import com.spaceattack.game.model.Ship;

/**
 * Enemy Ship body
 */
public class EnemyShipBody extends ObjectBody{
    /**
     * Constructor of a user ship body
     *
     * @param world The world in which the body will interact
     * @param obj The corresponding model
     */
    public EnemyShipBody(World world, Ship obj) {
        super(world, obj);

        float density = 0.1f, friction = 0.2f, restitution = 0.5f;
        int width = 200, height = 166;

        // Body
        createFixture(body, new float[]{
                74,24, 96,8, 106,8, 125,24, 120,79, 100,86, 80,79
        }, width, height, density, friction, restitution, ENEMY_SHIP_BODY, (short) (USER_SHIP_BODY| USER_BULLET_BODY));

        // Right Wing
        createFixture(body, new float[]{
                126,29, 152,37, 158,32, 176,63, 163,157, 147,150, 143,75, 118,78
        }, width, height, density, friction, restitution, ENEMY_SHIP_BODY, (short) (USER_SHIP_BODY| USER_BULLET_BODY));

        // Left Wing
        createFixture(body, new float[]{
                74,29, 48,37, 41,32, 24,63, 37,157, 53,150, 57,75, 83, 78
        }, width, height, density, friction, restitution, ENEMY_SHIP_BODY, (short) (USER_SHIP_BODY| USER_BULLET_BODY));


    }
}
