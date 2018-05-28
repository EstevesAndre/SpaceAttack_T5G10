package com.spaceattack.game.controller;

import com.badlogic.gdx.physics.box2d.World;
import com.spaceattack.game.model.Ship;

/**
 * User Ship Body
 */
public class UserShipBody extends ObjectBody{
    /**
     * Constructor of a user ship body
     *
     * @param world The world in which the body will interact
     * @param obj The corresponding model
     */
    public UserShipBody(World world, Ship obj) {
        super(world, obj);

        float density = 0.1f, friction = 0.2f, restitution = 0.5f;
        int width = 201, height = 157;

        // Body
        createFixture(body, new float[]{
                102,0, 88,24, 82,143, 88,161, 114,160, 120,143, 117,25 //7
        }, width, height, density, friction, restitution, USER_SHIP_BODY, (short) (ENEMY_SHIP_BODY| ENEMY_BULLET_BODY | POWER_UP_BODY));

        // Left Winglet
        createFixture(body, new float[]{
                88,24, 57,45, 57,70, 82,55 //4
        }, width, height, density, friction, restitution, USER_SHIP_BODY, (short) (ENEMY_SHIP_BODY| ENEMY_BULLET_BODY | POWER_UP_BODY));

        // Right Winglet
        createFixture(body, new float[]{
                117,25, 147,46, 146,70, 121,55 //4
        }, width, height, density, friction, restitution, USER_SHIP_BODY, (short) (ENEMY_SHIP_BODY| ENEMY_BULLET_BODY| POWER_UP_BODY));

        // Left Wingend
        createFixture(body, new float[]{
                15,85, 6,92, 7,137, 16,131, 15,122, 30,121, 30,91, 15,101 //8
        }, width, height, density, friction, restitution, USER_SHIP_BODY, (short) (ENEMY_SHIP_BODY| ENEMY_BULLET_BODY| POWER_UP_BODY));

        // Right Wingend
        createFixture(body, new float[]{
                188,85, 197,92, 195,136, 189,130, 187,122, 172,122, 172,92, 187,101 //8
        }, width, height, density, friction, restitution, USER_SHIP_BODY, (short) (ENEMY_SHIP_BODY| ENEMY_BULLET_BODY| POWER_UP_BODY));

        // Left Wing
        createFixture(body, new float[]{
                170,78, 171,127, 154,128, 150,122, 122,121, 121,57, 149,76, 151,68 //8
        }, width, height, density, friction, restitution, USER_SHIP_BODY, (short) (ENEMY_SHIP_BODY| ENEMY_BULLET_BODY| POWER_UP_BODY));

        // Right Wing
        createFixture(body, new float[]{
                52,68, 53,76, 82,56, 80,121, 53,122, 47,127, 30,127, 32,79 //8
        }, width, height, density, friction, restitution, USER_SHIP_BODY, (short) (ENEMY_SHIP_BODY| ENEMY_BULLET_BODY| POWER_UP_BODY));

        // Back Wing
        createFixture(body, new float[]{
                51,143, 151,143, 151,135, 129,122, 74,122, 52,136 //6
        }, width, height, density, friction, restitution, USER_SHIP_BODY, (short) (ENEMY_SHIP_BODY| ENEMY_BULLET_BODY| POWER_UP_BODY));
    }
}
