package com.spaceattack.game.model;

public class Portal extends GameObject{

    /**
     * If this portal has spawned a unit this step
     */
    private boolean exhausted;

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
        this.exhausted = false;
    }

    /**
     * Checks if the portal has spawn a unit this step
     *
     * @return the exhausted field
     */
    public boolean isExhausted()
    {
        return exhausted;
    }

    /**
     * Set the exhausted status
     *
     * @param exhausted the status to be attributed to exhausted
     */
    public void setExhausted(boolean exhausted)
    {
        this.exhausted = exhausted;
    }
}
