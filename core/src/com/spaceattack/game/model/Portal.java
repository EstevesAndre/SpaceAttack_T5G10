package com.spaceattack.game.model;

/**
 * Portal representing the place where Enemy Ship spawn
 */
public class Portal extends GameObject{

    /**
     * Constructor of a portal.
     *
     * @param x The x coordinate of this portal.
     * @param y The y coordinate of this portal.
     * @param rotation The rotation of this portal.
     */
    public Portal(float x, float y, float rotation)
    {
        super(x, y, rotation);
    }
}
