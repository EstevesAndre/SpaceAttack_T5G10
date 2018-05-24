package com.spaceattack.game.model;

public class PowerUp extends GameObject{

    final public static int HEALTH_TYPE = 1;
    final public static int TRIPLE_SHOT_TYPE = 2;
    final public static int SHIELD_TYPE = 3;

    /**
     * Powerup Type
     */
    private int type;

    /**
     * Constructor of a portal.
     *
     * @param x The x coordinate of this portal.
     * @param y The y coordinate of this portal.
     * @param rotation The rotation of this portal.
     */
    public PowerUp(float x, float y, float rotation, int type)
    {
        super(x, y, rotation);
        this.type = type;
    }

    /**
     * Returns the type of powerup
     *
     * @return the powerup type
     */
    public int getType()
    {
        return type;
    }

}
