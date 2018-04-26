package com.spaceattack.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Game model representing the game.
 */
public class Game {

    /**
     * The singleton instance of game model.
     */
    private static Game instance;

    /**
     * The user's space ship in this game.
     */
    private Ship userShip;

    /**
     * The enemy ships in this game.
     */
    private List<Ship> enemyShips;

    /**
     * The bullets of current state of the game.
     */
    private List<Bullet> bullets;

    /**
     * The current score of the game.
     */
    private double score;

    /**
     * Returns a singleton instance of game model.
     *
     * @return the singleton instance.
     */
    public static Game getInstance() {
        if (instance == null)
            instance = new Game();
        return instance;
    }

    /**
     * Constructor of the game.
     * Initializes with a user's ship in a certain position.
     */
    private Game()
    {
        userShip = new Ship(0, 0, 0, 5, 10, 5, 15);
        enemyShips = new ArrayList<Ship>();
        bullets = new ArrayList<Bullet>();
        score = 0;
    }

    /**
     * Gets the player's space ship.
     *
     * @return the space ship.
     */
    public Ship getUserShip() {
        return userShip;
    }

    /**
     * Gets the enemy's ships.
     *
     * @return the enemy's ships list.
     */
    public List<Ship> getEnemyShips() {
        return enemyShips;
    }

    /**
     * Gets the bullets of either user's ship or enemy's ships.
     *
     * @return the bullets list.
     */
    public List<Bullet> getBullets() {
        return bullets;
    }

    /**
     * Gets the score achieved by the user so fara
     *
     * @return the current score.
     */
    public double getScore() {
        return score;
    }

    /**
     * Adds a new enemy Ship on this instance.
     *
     * @param enemyShip The new enemy Ship.
     */
    public void addEnemyShip(Ship enemyShip) {
        enemyShips.add(enemyShip);
    }

    /**
     * Adds a new Bullet fired by any Ship on this instance.
     *
     * @param bullet The new bullet fired.
     */
    public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }

    /**
     * Removes the given ship of this instance.
     *
     * @param enemyShip The Ship to be removed.
     */
    public void removeEnemyShip(Ship enemyShip) {
        if(enemyShips.size() != 0)
            enemyShips.remove(enemyShip);
    }

    /**
     * Removes the given bullet of this instance.
     *
     * @param bullet The Bullet to be removed.
     */
    public void removeBullet(Bullet bullet){
        if(bullets.size() != 0)
            bullets.remove(bullet);
    }

    /**
     * Increases user score
     *
     * @param sc The increase amount
     */
    public void addScore(double sc){
        score += sc;
    }

    /**
     * Resets user score to starting value
     */
    public void resetScore(){
        score = 0;
    }
}