package com.spaceattack.game.model;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Port;

import static com.spaceattack.game.controller.GameController.ARENA_HEIGHT;
import static com.spaceattack.game.controller.GameController.ARENA_WIDTH;

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
     * The portals present on the game
     */
    private List<Portal> portals;

    /**
     * The power ups present on the game
     */
    private List<PowerUp> powerUps;

    /**
     * The exploding ships present on the game
     */
    private List<Ship> explodingShips;

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
        userShip = new Ship(ARENA_WIDTH / 2f, ARENA_HEIGHT / 2f, 0, 5, 3000f, 0.1f, 5000);
        enemyShips = new ArrayList<Ship>();
        bullets = new ArrayList<Bullet>();
        portals = new ArrayList<Portal>();
        powerUps = new ArrayList<PowerUp>();
        explodingShips = new ArrayList<Ship>();
        createPortals();
        score = 0;
    }

    /**
     * Creates the portals for the game
     */
    private void createPortals() {
        portals.add(new Portal(250, 150, 0));
        portals.add(new Portal(500, 150, 0));
        portals.add(new Portal(750, 150, 0));
        portals.add(new Portal(250, 350, 0));
        portals.add(new Portal(500, 350, 0));
        portals.add(new Portal(750, 350, 0));
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
     * Gets the portals
     *
     * @return the portals list.
     */
    public List<Portal> getPortals() {return portals;}

    /**
     * Gets the power ups
     *
     * @return the power ups list.
     */
    public List<PowerUp> getPowerUps() {return powerUps;}

    /**
     * Gets the exploding ships
     *
     * @return the exploding ships list.
     */
    public List<Ship> getExplodingShips() {return explodingShips;}

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
     * Adds a new power up on this instance.
     *
     * @param powerUp The new power up.
     */
    public void addPowerUp(PowerUp powerUp) {
        powerUps.add(powerUp);
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
     * Adds a new exploding ship on this instance.
     *
     * @param ship The new exploding ship.
     */
    public void addExplodingShip(Ship ship){
        explodingShips.add(ship);
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
     * Removes the given power up of this instance.
     *
     * @param powerUp The power up to be removed.
     */
    public void removePowerUp(PowerUp powerUp) {
        if(powerUps.size() != 0)
            powerUps.remove(powerUp);
    }

    /**
     * Removes the given ship of this instance.
     *
     * @param ship The Ship to be removed.
     */
    public void removeExplodingShip(Ship ship) {

        if(explodingShips.size() != 0)
            explodingShips.remove(ship);

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

    /**
     * Starts a new game
     */
    public void restart() {
        instance = new Game();
    }
}
