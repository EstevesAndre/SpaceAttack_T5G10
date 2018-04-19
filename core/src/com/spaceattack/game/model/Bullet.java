package com.spaceattack.game.model;

/**
 * Bullet model representing a single bullet.
 */
public class Bullet extends GameObject{

    /**
     * The speed of this bullet.
     */
    private float speed;

    /**
     * Constructor of a Bullet.
     *
     * @param x The x coordinate of this bullet.
     * @param y The y coordinate of this bullet.
     * @param rotation The rotation of this bullet.
     * @param speed The speed of this bullet.
     */
    public Bullet(float x, float y, float rotation, float speed) {
        super(x, y, rotation);
        this.speed = speed;
    }

    /**
     * Gets the speed of this bullet.
     *
     * @return the speed.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Sets the speed of this bullet.
     *
     * @param speed new speed to be set.
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
