package com.spaceattack.game.model;

public class Ship extends GameObject{

    private boolean flying;

    private int health;

    private float speed;

    private int fireRate;

    private float bulletSpeed;

    Ship(float x, float y, float rotation, int health, float speed, int fireRate, float bulletSpeed) {
        super(x, y, rotation);
        flying = false;
        this.health = health;
        this.speed = speed;
        this.fireRate = fireRate;
        this.bulletSpeed = bulletSpeed;
    }

    public boolean isFlying() {
        return flying;
    }

    public int getHealth() {
        return health;
    }

    public float getSpeed() {
        return speed;
    }

    public int getFireRate() {
        return fireRate;
    }

    public float getBulletSpeed() {
        return bulletSpeed;
    }

    public void increaseHealth() {
        health++;
    }

    public void decreaseHealth() {
        health--;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }

    public void setBulletSpeed(float bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }
}
