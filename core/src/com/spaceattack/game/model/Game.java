package com.spaceattack.game.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static Game instance;

    private Ship userShip;

    private List<Ship> enemyShips;

    private List<Bullet> bullets;

    public static Game getInstance() {
        if (instance == null)
            instance = new Game();
        return instance;
    }

    private Game()
    {
        userShip = new Ship(0, 0, 0, 5, 10, 5, 15);
        enemyShips = new ArrayList<Ship>();
        bullets = new ArrayList<Bullet>();
    }

    public Ship getUserShip() {
        return userShip;
    }

    public List<Ship> getEnemyShips() {
        return enemyShips;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }
}
