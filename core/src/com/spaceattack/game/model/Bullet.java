package com.spaceattack.game.model;


public class Bullet extends GameObject{

    private float speed;

    Bullet(float x, float y, float rotation, float speed) {
        super(x, y, rotation);
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
