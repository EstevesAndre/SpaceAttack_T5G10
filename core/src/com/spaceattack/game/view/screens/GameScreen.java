package com.spaceattack.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.spaceattack.game.SpaceAttackGame;
import com.spaceattack.game.controller.GameController;
import com.spaceattack.game.model.Bullet;
import com.spaceattack.game.model.Game;
import com.spaceattack.game.model.GameObject;
import com.spaceattack.game.model.Portal;
import com.spaceattack.game.model.PowerUp;
import com.spaceattack.game.model.Ship;
import com.spaceattack.game.view.*;

import static com.spaceattack.game.controller.GameController.ARENA_HEIGHT;
import static com.spaceattack.game.controller.GameController.ARENA_WIDTH;
import static com.spaceattack.game.model.PowerUp.HEALTH_TYPE;
import static com.spaceattack.game.model.PowerUp.SHIELD_TYPE;
import static com.spaceattack.game.model.PowerUp.TRIPLE_SHOT_TYPE;

/**
 * Created by estev on 25/05/2018.
 */

public class GameScreen extends ScreenAdapter {

    public final static float PIXEL_TO_METER = 0.04f;

    private static final float VIEWPORT_WIDTH = 100;

    private static final float VIEWPORT_HEIGHT = VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());

    private final OrthographicCamera camera;

    private final BitmapFont font;

    private SpaceAttackGame game;

    private Texture gameBackground;

    private Viewport viewport;

    private boolean pause;

    public GameScreen(SpaceAttackGame game) {
        this.game = game;

        font = game.getBitMapFont();

        camera = createCamera();

        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        gameBackground = game.getAssetManager().get("gameScreen/background.png", Texture.class);
        gameBackground.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        pause = false;
    }

    /**
     * Creates the camera used to show the viewport.
     *
     * @return the camera
     */
    private OrthographicCamera createCamera() {
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_HEIGHT / PIXEL_TO_METER);

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        return camera;
    }


    /**
     * Renders this screen.
     *
     * @param delta time since last renders in seconds.
     */
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInputs(delta);

        GameController.getInstance().markBullets();
        GameController.getInstance().update(delta);

        updateCamera();

        if(Game.getInstance().getUserShip().getHealth() == 0)
        {
            game.setScreen(new GameOverScreen(game));
        }

        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        drawBackground();
        drawEntities();
        drawHealth();
        drawScore();
        drawButtons();
        //blockSprite.draw(game.getBatch());
        game.getBatch().end();

    }

    /**
     * Updates camera's position according to the player's position.
     */
    private void updateCamera() {
        float shipX = Game.getInstance().getUserShip().getX() / PIXEL_TO_METER;
        float shipY = Game.getInstance().getUserShip().getY() / PIXEL_TO_METER;

        if (shipX > (ARENA_WIDTH / PIXEL_TO_METER - (VIEWPORT_WIDTH / PIXEL_TO_METER) / 2)) {
            shipX = (ARENA_WIDTH / PIXEL_TO_METER - (VIEWPORT_WIDTH / PIXEL_TO_METER) / 2);
        }

        if (shipX < (VIEWPORT_WIDTH / PIXEL_TO_METER) / 2) {
            shipX = (VIEWPORT_WIDTH / PIXEL_TO_METER) / 2;
        }

        if (shipY > (ARENA_HEIGHT / PIXEL_TO_METER - (VIEWPORT_WIDTH / PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth())) / 2)) {
            shipY = (ARENA_HEIGHT / PIXEL_TO_METER - (VIEWPORT_WIDTH / PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth())) / 2);
        }

        if (shipY < (VIEWPORT_WIDTH / PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth())) / 2) {
            shipY = (VIEWPORT_WIDTH / PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth())) / 2;
        }

        camera.position.set(shipX, shipY, 0);
        camera.update();
    }

    /**
     * Draws buttons on game screen
     */
    private void drawButtons() {

        float xStartPos = (camera.position.x * PIXEL_TO_METER - (VIEWPORT_WIDTH / 2) + 9) / PIXEL_TO_METER;
        float yStartPos = ((camera.position.y * PIXEL_TO_METER - (VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth())) / 2 + 6) / PIXEL_TO_METER);

        Sprite s;
        Texture throttleTexture = game.getAssetManager().get("gameScreen/throttleButton.png");
        TextureRegion t = new TextureRegion(throttleTexture, throttleTexture.getWidth(), throttleTexture.getHeight());
        s = new Sprite(t);
        s.setScale(2f, 2f);

        s.setCenter(xStartPos, yStartPos);
        s.draw(game.getBatch());

        xStartPos = (camera.position.x * PIXEL_TO_METER + (VIEWPORT_WIDTH / 2) - 9) / PIXEL_TO_METER;

        Texture fireTexture = game.getAssetManager().get("gameScreen/fireButton.png");
        t = new TextureRegion(fireTexture, fireTexture.getWidth(), fireTexture.getHeight());
        s = new Sprite(t);
        s.setScale(2f, 2f);

        s.setCenter(xStartPos, yStartPos);
        s.draw(game.getBatch());

        /*xStartPos = (camera.position.x);

        Texture pauseTexture = game.getAssetManager().get("gameScreen/pauseButton.png");
        t = new TextureRegion(pauseTexture, pauseTexture.getWidth(), pauseTexture.getHeight());
        s = new Sprite(t);
        s.setScale(2f, 2f);

        s.setCenter(xStartPos, yStartPos);
        s.draw(game.getBatch());*/
    }

    /**
     * Draws user score on screen
     */
    private void drawScore() {

        float xStartPos = (camera.position.x * PIXEL_TO_METER - (VIEWPORT_WIDTH / 2) + 2) / PIXEL_TO_METER;
        float yStartPos = ((camera.position.y * PIXEL_TO_METER + (VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth())) / 2 - 2) / PIXEL_TO_METER);

        font.draw(game.getBatch(), "Score: " + (int) Game.getInstance().getScore(), xStartPos, yStartPos);
    }

    /**
     * Draws user health on screen
     */
    private void drawHealth() {

        float xStartPos = (camera.position.x * PIXEL_TO_METER + (VIEWPORT_WIDTH / 2) - 4) / PIXEL_TO_METER;
        float yStartPos = ((camera.position.y * PIXEL_TO_METER + (VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth())) / 2 - 4) / PIXEL_TO_METER);

        Sprite s;
        Texture HeartTexture = game.getAssetManager().get("gameScreen/heart.png");
        TextureRegion t = new TextureRegion(HeartTexture, HeartTexture.getWidth(), HeartTexture.getHeight());
        s = new Sprite(t);
        s.setScale(0.2f, 0.2f);

        for (int i = 0; i < Game.getInstance().getUserShip().getHealth(); i++) {
            s.setCenter(xStartPos - (i * HeartTexture.getWidth() * 0.2f), yStartPos);
            s.draw(game.getBatch());
        }


    }

    /**
     * Handles any inputs and passes them to the controller.
     *
     * @param delta time since last time inputs where handled in seconds
     */
    private void handleInputs(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            GameController.getInstance().rotateLeft(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            GameController.getInstance().rotateRight(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            GameController.getInstance().accelerate(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            GameController.getInstance().accelerate(-delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            GameController.getInstance().fire();
        }

        if (Gdx.input.getAccelerometerY() > 1) {
            GameController.getInstance().rotateRight(delta);
        }
        if (Gdx.input.getAccelerometerY() < -1) {
            GameController.getInstance().rotateLeft(delta);
        }

        if (Gdx.input.isTouched()) {
            float xStartPos = (camera.position.x * PIXEL_TO_METER - (VIEWPORT_WIDTH / 2)) / PIXEL_TO_METER;
            float xEndPos = (camera.position.x * PIXEL_TO_METER + (VIEWPORT_WIDTH / 2)) / PIXEL_TO_METER;
            float yStartPos = ((camera.position.y * PIXEL_TO_METER - (VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth())) / 2) / PIXEL_TO_METER);

            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(mousePos);

            Texture t = game.getAssetManager().get("gameScreen/fireButton.png");

            if (mousePos.y > yStartPos && mousePos.y < yStartPos + t.getHeight() * 2) {
                if (mousePos.x > xStartPos && mousePos.x < xStartPos + t.getWidth() * 2)
                    GameController.getInstance().accelerate(delta);
                if (mousePos.x < xEndPos && mousePos.x > xEndPos - t.getWidth() * 2)
                    GameController.getInstance().fire();

            }
        }
    }


    /**
     * Draws the entities to the screen.
     */
    private void drawEntities() {
        for (int i = 0; i < Game.getInstance().getPortals().size(); i++) {
            Portal p = Game.getInstance().getPortals().get(i);

            PortalView pView = new PortalView(game);
            pView.update(p);
            pView.draw(game.getBatch());
        }

        Ship ship = Game.getInstance().getUserShip();

        if (Game.getInstance().getUserShip().getShield() > 0) {
            UserShipShieldView view = new UserShipShieldView(game);
            view.update(ship);
            view.draw(game.getBatch());
        } else {
            UserShipView view = new UserShipView(game);
            view.update(ship);
            view.draw(game.getBatch());
        }

        for (int i = 0; i < Game.getInstance().getBullets().size(); i++) {
            Bullet b = Game.getInstance().getBullets().get(i);

            if (b.getSpeed() >= 5000) {
                UserBulletView bView = new UserBulletView(game);
                bView.update(b);
                bView.draw(game.getBatch());
            } else {
                EnemyBulletView bView = new EnemyBulletView(game);
                bView.update(b);
                bView.draw(game.getBatch());
            }
        }

        for (int i = 0; i < Game.getInstance().getEnemyShips().size(); i++) {
            Ship s = Game.getInstance().getEnemyShips().get(i);

            if (s.getSpeed() == 1500) {
                EnemyShipView sView = new EnemyShipView(game);
                sView.update(s);
                sView.draw(game.getBatch());
            } else if (s.getSpeed() == 2000) {
                EnemyShipViewBlue sView = new EnemyShipViewBlue(game);
                sView.update(s);
                sView.draw(game.getBatch());
            } else if (s.getSpeed() == 2500) {
                EnemyShipViewGreen sView = new EnemyShipViewGreen(game);
                sView.update(s);
                sView.draw(game.getBatch());
            } else if (s.getSpeed() == 3000) {
                EnemyShipViewYellow sView = new EnemyShipViewYellow(game);
                sView.update(s);
                sView.draw(game.getBatch());
            } else {
                EnemyShipViewPurple sView = new EnemyShipViewPurple(game);
                sView.update(s);
                sView.draw(game.getBatch());
            }

        }

        for (int i = 0; i < Game.getInstance().getPowerUps().size(); i++) {
            PowerUp p = Game.getInstance().getPowerUps().get(i);

            switch (p.getType()) {
                case HEALTH_TYPE: {
                    HealthPowerUpView pView = new HealthPowerUpView(game);
                    pView.update(p);
                    pView.draw(game.getBatch());
                    break;
                }
                case SHIELD_TYPE: {
                    ShieldPowerUpView pView = new ShieldPowerUpView(game);
                    pView.update(p);
                    pView.draw(game.getBatch());
                    break;
                }
                case TRIPLE_SHOT_TYPE: {
                    TripleFirePowerUpView pView = new TripleFirePowerUpView(game);
                    pView.update(p);
                    pView.draw(game.getBatch());
                    break;
                }
            }

        }
    }

    /**
     * Draws the background
     */
    private void drawBackground() {
        game.getBatch().draw(gameBackground, 0, 0, 0, 0, (int) (ARENA_WIDTH / PIXEL_TO_METER), (int) (ARENA_HEIGHT / PIXEL_TO_METER));
    }


    /**
     * Checks if object is out of viewport
     *
     * @param obj the object to be tested
     * @return true if object is out of viewport, false otherwise
     */
    public static boolean isOutOfViewport(GameObject obj) {
        if (obj.getX() > Game.getInstance().getUserShip().getX() + VIEWPORT_WIDTH / 2)
            return true;

        if (obj.getX() < Game.getInstance().getUserShip().getX() - VIEWPORT_WIDTH / 2)
            return true;

        if (obj.getY() > Game.getInstance().getUserShip().getY() + (VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth())) / 2)
            return true;

        if (obj.getY() < Game.getInstance().getUserShip().getY() - (VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth())) / 2)
            return true;

        return false;
    }

}
