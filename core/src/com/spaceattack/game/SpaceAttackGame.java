package com.spaceattack.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.spaceattack.game.view.screens.MainMenuScreen;

/**
 * Game Class - SpaceAttackGame
 */
public class SpaceAttackGame extends Game {


    private SpriteBatch batch;

    private AssetManager assetManager;

    private AppPreferences preferences;

    private Music music;

    private BitmapFont font;

    private Skin skin1;

    private Skin skin2;

    @Override
    public void create() {
        batch = new SpriteBatch();
        assetManager = new AssetManager();
        preferences = new AppPreferences();


        skin1 = new Skin(Gdx.files.internal("skins/skin.json"), new TextureAtlas("skins/skin.atlas"));

        skin2 = new Skin(Gdx.files.internal("skins/flat-earth-ui.json"), new TextureAtlas("skins/flat-earth-ui.atlas"));

        loadAssets();
        setMusic();
        createBitmapFont();

        startGame();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        assetManager.dispose();
    }

    /**
     * Gets the AppPreferences Clas of the Game
     *
     * @return respective preferences
     */
    public AppPreferences getPreferences() {
        return this.preferences;
    }

    /**
     * Creates BitmapFont to use on different menus
     */
    private void createBitmapFont() {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("virgo.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 70;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.assetManager.load("menuBackground.png", Texture.class);
        this.assetManager.load("space_attack_title.png", Texture.class);

        // User Ships
        this.assetManager.load("userShip/userShip.png", Texture.class);
        this.assetManager.load("userShip/shipShield.png", Texture.class);
        this.assetManager.load("userShip/laserRed.png", Texture.class);

        // Enemy Ships
        this.assetManager.load("enemyShips/laserGreen.png", Texture.class);
        this.assetManager.load("enemyShips/enemyShipRed.png", Texture.class);
        this.assetManager.load("enemyShips/enemyShipGreen.png", Texture.class);
        this.assetManager.load("enemyShips/enemyShipYellow.png", Texture.class);
        this.assetManager.load("enemyShips/enemyShipBlue.png", Texture.class);
        this.assetManager.load("enemyShips/enemyShipPurple.png", Texture.class);

        // Game Screen
        this.assetManager.load("gameScreen/background.png", Texture.class);
        this.assetManager.load("gameScreen/heart.png", Texture.class);
        this.assetManager.load("gameScreen/fireButton.png", Texture.class);
        this.assetManager.load("gameScreen/throttleButton.png", Texture.class);

        // PowerUps
        this.assetManager.load("powerUps/1UP.png", Texture.class);
        this.assetManager.load("powerUps/shieldPower.png", Texture.class);
        this.assetManager.load("powerUps/bulletPower.png", Texture.class);

        // Animations
        this.assetManager.load("animations/portal.png", Texture.class);
        this.assetManager.load("animations/redExplosion.png", Texture.class);
        this.assetManager.load("animations/blueExplosion.png", Texture.class);
        this.assetManager.load("animations/greenExplosion.png", Texture.class);
        this.assetManager.load("animations/yellowExplosion.png", Texture.class);
        this.assetManager.load("animations/purpleExplosion.png", Texture.class);

        this.assetManager.finishLoading();
    }

    /**
     * Starts the Game.
     */
    private void startGame() {
        setScreen(new MainMenuScreen(this));
    }

    /**
     * Set the Game's Music.
     */
    private void setMusic() {
        music = Gdx.audio.newMusic(Gdx.files.internal("musics/backgroundMusic.ogg"));
        music.setVolume(preferences.getMusicVolume());
        music.setLooping(true);

        if (preferences.isMusicEnabled())
            music.play();
        else
            music.pause();
    }

    /**
     * Returns the asset manager used to load all textures and sounds.
     *
     * @return the asset manager
     */
    public AssetManager getAssetManager() {
        return assetManager;
    }

    /**
     * Returns the sprite batch used to improve drawing performance.
     *
     * @return the sprite batch
     */
    public SpriteBatch getBatch() {
        return batch;
    }

    /**
     * Gets the BitmapFont
     *
     * @return the BitmapFont font
     */
    public BitmapFont getBitMapFont() {
        return font;
    }

    /**
     * Gets the Skin to menu buttons
     *
     * @return the skin1
     */
    public Skin getSkin() {
        return skin1;
    }

    /**
     * Gets the Skin to Sliders and Check Boxes
     *
     * @return the skin2
     */
    public Skin getSkin2() {
        return skin2;
    }

    /**
     * Gets the Music to play on the Game
     *
     * @return the music
     */
    public Music getMusic() {
        return music;
    }

}
