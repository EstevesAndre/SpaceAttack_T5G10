package com.spaceattack.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.spaceattack.game.SpaceAttackGame;
import com.spaceattack.game.controller.GameController;
import com.spaceattack.game.model.Bullet;
import com.spaceattack.game.model.Game;
import com.spaceattack.game.model.GameObject;
import com.spaceattack.game.model.Ship;

import static com.spaceattack.game.controller.GameController.ARENA_HEIGHT;
import static com.spaceattack.game.controller.GameController.ARENA_WIDTH;

public class GameView extends ScreenAdapter{
    /**
     * The width of the viewport in meters. The height is
     * automatically calculated using the screen ratio.
     */
    private static final float VIEWPORT_WIDTH = 100;

    /**
     * How much meters does a pixel represent.
     */
    public final static float PIXEL_TO_METER = 0.04f;

    /**
     * The game this screen belongs to.
     */
    private final SpaceAttackGame game;

    /**
     * The camera used to show the viewport.
     */
    private final OrthographicCamera camera;

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public GameView(SpaceAttackGame game) {
        this.game = game;

        loadAssets();

        camera = createCamera();
    }

    /**
     * Creates the camera used to show the viewport.
     *
     * @return the camera
     */
    private OrthographicCamera createCamera() {
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH / PIXEL_TO_METER  * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        return camera;
    }

    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load( "background.png" , Texture.class);
        this.game.getAssetManager().load( "user_ship.png" , Texture.class);
        this.game.getAssetManager().load( "laserRed.png" , Texture.class);
        this.game.getAssetManager().load( "enemyShipRed.png" , Texture.class);

        this.game.getAssetManager().finishLoading();
    }

    /**
     * Renders this screen.
     *
     * @param delta time since last renders in seconds.
     */
    @Override
    public void render(float delta) {
        handleInputs(delta);
        GameController.getInstance().markBullets();

        GameController.getInstance().update(delta);

        float shipX = Game.getInstance().getUserShip().getX() / PIXEL_TO_METER;
        float shipY = Game.getInstance().getUserShip().getY() / PIXEL_TO_METER;

        if(shipX > (ARENA_WIDTH / PIXEL_TO_METER - (VIEWPORT_WIDTH / PIXEL_TO_METER)/2))
        {
            shipX = (ARENA_WIDTH / PIXEL_TO_METER - (VIEWPORT_WIDTH / PIXEL_TO_METER)/2);
        }

        if(shipX < (VIEWPORT_WIDTH / PIXEL_TO_METER)/2)
        {
            shipX = (VIEWPORT_WIDTH / PIXEL_TO_METER)/2;
        }

        if(shipY > (ARENA_HEIGHT / PIXEL_TO_METER - (VIEWPORT_WIDTH / PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()))/2))
        {
            shipY = (ARENA_HEIGHT / PIXEL_TO_METER - (VIEWPORT_WIDTH / PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()))/2);
        }

        if(shipY < (VIEWPORT_WIDTH / PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()))/2)
        {
            shipY = (VIEWPORT_WIDTH / PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()))/2;
        }

        camera.position.set(shipX, shipY, 0);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);



        game.getBatch().begin();
        drawBackground();
        drawEntities();
        game.getBatch().end();
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
    }


    /**
     * Draws the entities to the screen.
     */
    private void drawEntities() {
        Ship ship = Game.getInstance().getUserShip();
        UserShipView view = new UserShipView(game);
        view.update(ship);
        view.draw(game.getBatch());

        for(int i = 0; i < Game.getInstance().getBullets().size(); i++)
        {
            Bullet b = Game.getInstance().getBullets().get(i);

            if(b.getSpeed() >= 5000) {
                UserBulletView bView = new UserBulletView(game);
                bView.update(b);
                bView.draw(game.getBatch());
            }
        }

        for(int i = 0; i < Game.getInstance().getEnemyShips().size(); i++)
        {
            Ship s = Game.getInstance().getEnemyShips().get(i);

            EnemyShipView sView = new EnemyShipView(game);
            sView.update(s);
            sView.draw(game.getBatch());
        }
    }

    /**
     * Draws the background
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("background.png", Texture.class);
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        game.getBatch().draw(background, 0, 0, 0, 0, (int)(ARENA_WIDTH / PIXEL_TO_METER), (int) (ARENA_HEIGHT / PIXEL_TO_METER));
    }

    /**
     * Checks if object is out of viewport
     *
     * @param obj the object to be tested
     *
     * @return true if object is out of viewport, false otherwise
     */
    public static boolean isOutOfViewport(GameObject obj)
    {
        if(obj.getX() > Game.getInstance().getUserShip().getX() + VIEWPORT_WIDTH / PIXEL_TO_METER)
            return true;

        if(obj.getX() < Game.getInstance().getUserShip().getX() - VIEWPORT_WIDTH / PIXEL_TO_METER)
            return true;

        if(obj.getY() > Game.getInstance().getUserShip().getY() + VIEWPORT_WIDTH / PIXEL_TO_METER  * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()))
            return true;

        if(obj.getY() < Game.getInstance().getUserShip().getY() - VIEWPORT_WIDTH / PIXEL_TO_METER  * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()))
            return true;

        return false;
    }
}
