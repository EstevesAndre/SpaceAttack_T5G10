package com.spaceattack.game.model;


public class GameObject {

    private float x;

    private float y;

    private float rotation;

    GameObject(float x, float y, float rotation){
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRotation() {
        return rotation;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
