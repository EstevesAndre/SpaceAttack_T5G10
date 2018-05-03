package com.spaceattack.game.controller;

import com.badlogic.gdx.physics.box2d.World;
import com.spaceattack.game.model.Ship;

public class UserShipBody extends ObjectBody{
    /**
     * Constructor of a user ship body
     *
     * @param world The world in which the body will interact
     * @param obj The corresponding model
     */
    public UserShipBody(World world, Ship obj) {
        super(world, obj);

        float density = 0.5f, friction = 0.4f, restitution = 0.5f;
        int width = 201, height = 157;

        /*// Body
        createFixture(body, new float[]{
                97,1, 88,20, 81,52, 83,140, 88,157, 105,1, 114,20, 120,52, 119,140, 113,157
        }, width, height, density, friction, restitution, USER_SHIP_BODY, (short) (ENEMY_SHIP_BODY| ENEMY_BULLET_BODY));

        // Left Winglet
        createFixture(body, new float[]{
               88,20, 56,42, 56,66, 86,48
        }, width, height, density, friction, restitution, USER_SHIP_BODY, (short) (ENEMY_SHIP_BODY| ENEMY_BULLET_BODY));

        // Right Winglet
        createFixture(body, new float[]{
                114,20, 146,42, 146,66, 116,48
        }, width, height, density, friction, restitution, USER_SHIP_BODY, (short) (ENEMY_SHIP_BODY| ENEMY_BULLET_BODY));

        // Left Wing
        createFixture(body, new float[]{
                82,53, 15,98, 15,82, 6,90, 6,133, 15,127, 13,119, 82,119
        }, width, height, density, friction, restitution, USER_SHIP_BODY, (short) (ENEMY_SHIP_BODY| ENEMY_BULLET_BODY));

        // Right Wing
        createFixture(body, new float[]{
                120,53, 187,98, 187,82, 196,90, 196,133, 187,127, 187,119, 120,119
        }, width, height, density, friction, restitution, USER_SHIP_BODY, (short) (ENEMY_SHIP_BODY| ENEMY_BULLET_BODY));

        // Back Wing
        createFixture(body, new float[]{
                83,119, 73,119, 52,133, 52,140, 82,140, 88,157, 114,157, 120,141, 150,140, 150,132, 128,119, 121,119
        }, width, height, density, friction, restitution, USER_SHIP_BODY, (short) (ENEMY_SHIP_BODY| ENEMY_BULLET_BODY));*/
    }
}
