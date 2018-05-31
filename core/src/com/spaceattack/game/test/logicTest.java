package com.spaceattack.game.test;

import org.junit.Test;

import com.spaceattack.game.model.Bullet;
import com.spaceattack.game.model.Game;
import com.spaceattack.game.model.Portal;
import com.spaceattack.game.model.PowerUp;
import com.spaceattack.game.model.Ship;

import java.util.List;

import static org.junit.Assert.*;

public class logicTest {

    @Test
    public void testCreateGame() {
        Game g = Game.getInstance();
        assertEquals(g.getEnemyShips().size(), 0);
        assertEquals(g.getBullets().size(), 0);
        assertNotEquals(g.getUserShip(), null);
    }

    @Test
    public void testCreateShip() {
        Ship s = new Ship(0, 1, 2, 3, 4, 5, 6);
        assertTrue(s.getX() == 0);
        assertTrue(s.getY() == 1);
        assertTrue(s.getRotation() == 2);
        assertTrue(s.getHealth() == 3);
        assertTrue(s.getSpeed() == 4);
        assertTrue(s.getFireRate() == 5);
        assertTrue(s.getBulletSpeed() == 6);
        assertTrue(s.getFireCooldown() == 0);
        assertTrue(!s.isFlying());
    }

    @Test
    public void testShipPositionSet() {
        Ship s = new Ship(0, 0, 0, 0, 0, 0, 0);
        assertTrue(s.getX() == 0);
        assertTrue(s.getY() == 0);
        s.setPosition(1, 2);
        assertTrue(s.getX() == 1);
        assertTrue(s.getY() == 2);
    }

    @Test
    public void testShipRotationSet() {
        Ship s = new Ship(0, 0, 0, 0, 0, 0, 0);
        assertTrue(s.getRotation() == 0);
        s.setRotation(1);
        assertTrue(s.getRotation() == 1);
    }

    @Test
    public void testShipHealthChange() {
        Ship s = new Ship(0, 0, 0, 0, 0, 0, 0);
        assertTrue(s.getHealth() == 0);
        s.increaseHealth();
        assertTrue(s.getHealth() == 1);
        s.decreaseHealth();
        s.decreaseHealth();
        assertTrue(s.getHealth() == -1);
    }

    @Test
    public void testShipSpeedSet() {
        Ship s = new Ship(0, 0, 0, 0, 0, 0, 0);
        assertTrue(s.getSpeed() == 0);
        s.setSpeed(1);
        assertTrue(s.getSpeed() == 1);
    }

    @Test
    public void testShipFireRateSet() {
        Ship s = new Ship(0, 0, 0, 0, 0, 0, 0);
        assertTrue(s.getFireRate() == 0);
        s.setFireRate(1);
        assertTrue(s.getFireRate() == 1);
    }

    @Test
    public void testShipBulletSpeedSet() {
        Ship s = new Ship(0, 0, 0, 0, 0, 0, 0);
        assertTrue(s.getBulletSpeed() == 0);
        s.setBulletSpeed(1);
        assertTrue(s.getBulletSpeed() == 1);
    }

    @Test
    public void testCreateBullet() {
        Bullet b = new Bullet(0, 1, 2, 3);
        assertTrue(b.getX() == 0);
        assertTrue(b.getY() == 1);
        assertTrue(b.getRotation() == 2);
        assertTrue(b.getSpeed() == 3);
    }

    @Test
    public void testBulletPositionSet() {
        Bullet b = new Bullet(0, 0, 0, 0);
        assertTrue(b.getX() == 0);
        assertTrue(b.getY() == 0);
        b.setPosition(1, 2);
        assertTrue(b.getX() == 1);
        assertTrue(b.getY() == 2);
    }

    @Test
    public void testBulletRotationSet() {
        Bullet b = new Bullet(0, 0, 0, 0);
        assertTrue(b.getRotation() == 0);
        b.setRotation(1);
        assertTrue(b.getRotation() == 1);
    }

    @Test
    public void testBulletSpeedSet() {
        Bullet b = new Bullet(0, 0, 0, 0);
        assertTrue(b.getSpeed() == 0);
        b.setSpeed(1);
        assertTrue(b.getSpeed() == 1);
    }

    @Test
    public void testShipFire() {
        Ship s = new Ship(0, 1, 2, 3, 4, 5, 6);
        Bullet b = s.fire();
        assertTrue(b.getX() == s.getX());
        assertTrue(b.getY() == s.getY());
        assertTrue(b.getRotation() == s.getRotation());
        assertTrue(b.getSpeed() == s.getBulletSpeed());
    }

    @Test
    public void testShipCooldown() {
        Ship s = new Ship(0, 1, 2, 3, 4, 50, 6);
        Bullet b = s.fire();
        assertTrue(s.getFireCooldown() == s.getFireRate());
        s.decreaseCooldown(10);
        assertTrue(s.getFireCooldown() == 40);
        s.decreaseCooldown(50);
        assertTrue(s.getFireCooldown() == 0);
    }

    @Test
    public void testAddEnemyShips() {
        Game g = Game.getInstance();
        Ship s = new Ship(0, 1, 2, 3, 4, 5, 6);
        g.addEnemyShip(s);
        assertEquals(g.getEnemyShips().size(), 1);
        g.removeEnemyShip(s);
    }

    @Test
    public void testAddBullet() {
        Game g = Game.getInstance();
        Ship s = new Ship(0, 1, 2, 3, 4, 5, 6);
        g.addEnemyShip(s);
        g.addBullet(s.fire());
        assertEquals(g.getBullets().size(), 1);
        g.removeEnemyShip(s);
        g.removeBullet(g.getBullets().get(0));
    }

    @Test
    public void testRemoveElements() {
        Game g = Game.getInstance();
        Ship s = new Ship(0, 1, 2, 3, 4, 5, 6);
        g.addEnemyShip(s);
        g.addBullet(s.fire());
        g.removeEnemyShip(s);
        assertEquals(g.getEnemyShips().size(), 0);
        g.removeBullet(g.getBullets().get(0));
        assertEquals(g.getBullets().size(), 0);
    }

    @Test
    public void testScore() {
        Game g = Game.getInstance();
        assertTrue(g.getScore() == 0);
        g.addScore(1000);
        assertTrue(g.getScore() == 1000);
        g.resetScore();
        assertTrue(g.getScore() == 0);
    }

    @Test
    public void testPortalCreate() {
        List<Portal> l = Game.getInstance().getPortals();
        assertTrue(l.size() == 6);
    }

    @Test
    public void testPowerUps() {
        Game.getInstance().restart();
        assertTrue(Game.getInstance().getPowerUps().size() == 0);
        PowerUp p = new PowerUp(0, 0, 0, PowerUp.SHIELD_TYPE);
        assertEquals(PowerUp.SHIELD_TYPE, p.getType());
        Game.getInstance().addPowerUp(p);
        assertTrue(Game.getInstance().getPowerUps().size() == 1);
        Game.getInstance().removePowerUp(p);
        assertTrue(Game.getInstance().getPowerUps().size() == 0);
    }

    @Test
    public void testExplodingShips() {
        Game.getInstance().restart();
        assertTrue(Game.getInstance().getExplodingShips().size() == 0);
        Ship s = new Ship(0, 1, 2, 3, 4, 5, 6);
        Game.getInstance().addExplodingShip(s);
        assertTrue(Game.getInstance().getExplodingShips().size() == 1);
        Game.getInstance().removeExplodingShip(s);
        assertTrue(Game.getInstance().getExplodingShips().size() == 0);
    }

    @Test
    public void testMarkingObjects() {
        Ship s = new Ship(0, 1, 2, 3, 4, 5, 6);
        assertFalse(s.isMarked());
        s.destroy();
        assertTrue(s.isMarked());
    }
}
