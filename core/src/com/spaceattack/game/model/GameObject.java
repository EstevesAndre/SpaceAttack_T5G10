package com.spaceattack.game.model;

/**
 * Abstract object representing an entity of the current game model.
 */
public abstract class GameObject {

    /**
     * The x coordinate of this model in meters.
     */
    private float x;

    /**
     * The y coordinate of this model in meters.
     */
    private float y;

    /**
     * The rotation of this model in radians.
     */
    private float rotation;

    /**
     * Constructor of a certain model with a given position and rotation.
     *
     * @param x The x coordinate of this certain game object.
     * @param y The y coordinate of this certain game object.
     * @param rotation The rotation of this certain game object.
     */
    public GameObject(float x, float y, float rotation){
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    /**
     * Gets the x position of this object.
     *
     * @return The x coordinate of this model in meters.
     */
    public float getX() {
        return x;
    }

    /**
     * Gets the y position of this object.
     *
     * @return The y coordinate of this model in meters.
     */
    public float getY() {
        return y;
    }

    /**
     * Gets the rotation of this object.
     *
     * @return The rotation of this model in radians.
     */
    public float getRotation() {
        return rotation;
    }

    /**
     * Sets the position, x and y coordinates, of this object.
     *
     * @param x The x coordinate of this model in meters.
     * @param y The y coordinate of this model in meters.
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the rotation of the object.
     *
     * @param rotation The rotation of this model in radians.
     */
    public void setRotation(float rotation) { this.rotation = rotation; }

}

