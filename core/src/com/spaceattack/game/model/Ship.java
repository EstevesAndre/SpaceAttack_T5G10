package com.spaceattack.game.model;

/**
 * Ship model representing a single ship.
 */
public class Ship extends GameObject{

    /**
     * To check if this object is moving or if it is stopped.
     * flying == false ? 'Stopped' : 'Moving'.
     */
    private boolean flying;

    /**
     * Current health of this object.
     */
    private int health;

    /**
     * Current speed of this object.
     */
    private float speed;

    /**
     * Current fire rate of this object.
     */
    private float fireRate;

    /**
     * Current fire cooldown of this object.
     */
    private float fireCooldown;

    /**
     * Current bullet speed of this object.
     */
    private float bulletSpeed;

    /**
     * Auxiliary flag to mark ship to health reduction
     */
    private boolean isHit;

    /**
     * Creates a new ship model with a given position, rotation, health, speed, fireRate and bulletSpeed.
     *
     * @param x The x coordinate of this Ship in meters.
     * @param y The y coordinate of this Ship in meters.
     * @param rotation The rotation of this Ship in radians.
     * @param health The health of this Ship.
     * @param speed The speed of this Ship in meters per second.
     * @param fireRate Fire rate of this Ship.
     * @param bulletSpeed The bullet speed of this Ship in meters per second.
     */
    public Ship(float x, float y, float rotation, int health, float speed, float fireRate, float bulletSpeed) {
        super(x, y, rotation);
        flying = false;
        this.health = health;
        this.speed = speed;
        this.fireRate = fireRate;
        fireCooldown = 0;
        this.bulletSpeed = bulletSpeed;
        this.isHit = false;

    }

    /**
     * Gets the current status of this ship. flying variable.
     * It is false if this ship is stopped.
     * It is true if this ship is moving.
     *
     * @return the flying variable.
     */
    public boolean isFlying() {
        return flying;
    }

    /**
     * Gets the current health of this ship.
     *
     * @return the health variable.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gets the speed of this ship.
     *
     * @return the speed variable in meters per second.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Gets the fire cooldown of this ship.
     *
     * @return the fireCooldown variable.
     */
    public float getFireCooldown() {
        return fireCooldown;
    }

    /**
     * Gets the fire rate of this ship.
     *
     * @return the fireRate variable.
     */
    public float getFireRate() {
        return fireRate;
    }

    /**
     * Gets the bullet speed of this ship.
     *
     * @return the bulletSpeed variable in meters per second.
     */
    public float getBulletSpeed() {
        return bulletSpeed;
    }

    /**
     * Returns the hit status of the ship
     *
     * @return the isHit field
     */
    public boolean isHit() {
        return isHit;
    }

    /**
     * Increases the health of this ship.
     * Could be with a power-up or a certain game state.
     * The ship gains a single point on his health bar.
     */
    public void increaseHealth() {
        health++;
    }

    /**
     * Decreases the health of this ship.
     * Occurs when the ship is hit.
     * The ship loses a single point on his health bar.
     */
    public void decreaseHealth() {
        health--;
    }

    /**
     * Decreases the fire cooldown of this ship.
     * Occurs periodically
     *
     * @param delta time elapsed
     */
    public void decreaseCooldown(float delta) {
        fireCooldown -= delta;
        if(fireCooldown < 0)
            fireCooldown = 0;
    }

    /**
     * Sets the flying parameter with the given parameter.
     *
     * @param flying The new status for flying parameter of this ship.
     */
    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    /**
     * Sets the speed of this ship with the given parameter, speed.
     *
     * @param speed The new speed of the ship.
     *              If is moving, speed > 0.
     *              If is stopped, speed = 0.
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Sets the fire rate of this ship with the given parameter, fireRate.
     *
     * @param fireRate The new fire rate of this ship.
     *                 Can't be lower than 0.
     */
    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }

    /**
     * Sets the bullet speed of this ship with the given parameter, bulletSpeed.
     *
     * @param bulletSpeed The new bullet speed of this ship.
     *                    Can't be lower than 0.
     */
    public void setBulletSpeed(float bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    /**
     * Sets the hit status of the ship
     *
     * @param status The new hit status
     */
    public void setHitStatus(boolean status) {
        this.isHit = status;
    }

    /**
     * Creates a new bullet fired by this current ship.
     *
     * @return Bullet fired.
     */
    public Bullet fire() {

       Bullet bullet = new Bullet(getX(),getY(),getRotation(),bulletSpeed);

       fireCooldown = fireRate;

       return bullet;
    }
}
