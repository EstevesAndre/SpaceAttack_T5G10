package com.spaceattack.game.view.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.spaceattack.game.SpaceAttackGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.spaceattack.game.controller.GameController;
import com.spaceattack.game.model.Game;

/**
 * Main Screen to help the different screens on the application
 */
public abstract class MenuScreen extends ScreenAdapter {

    /**
     * The current game
     */
    protected final SpaceAttackGame game;

    /**
     * The current stage
     */
    protected Stage stage;

    /**
     * The viewport associated to the Menu's stage.
     */
    private Viewport viewport;

    /**
     * The SpriteBatch used in the Menu's screen.
     */
    protected SpriteBatch batch;

    /**
     * Skin associated to the Menu.
     */
    protected Skin skin;

    /**
     * The game's background Image
     */
    private Image backgroundImg;

    /**
     * The game's title Image
     */
    private Image titleImg;

    /**
     * The width of the viewport in pixels.
     */
    protected static final float VIEWPORT_WIDTH = Gdx.graphics.getWidth();

    /**
     * The height of the viewport in pixels.
     */
    protected static final float VIEWPORT_HEIGHT = Gdx.graphics.getHeight();

    /**
     * Width of the Menu's Buttons.
     */
    protected static final float BUTTON_WIDTH = VIEWPORT_WIDTH / 2;

    /**
     * Edge of the Menu's Buttons.
     */
    protected static final float BUTTON_EDGE = VIEWPORT_WIDTH / 75;

    /**
     * Bottom Size of the Menu's Buttons.
     */
    protected static final float BOTTOM_EDGE = VIEWPORT_WIDTH / 75;

    /**
     * Default size of the Menu's buttons.
     */
    protected static final float DEFAULT_BUTTON_SIZE = VIEWPORT_WIDTH / 15;

    /**
     * Constructs the abstract Menu Screen
     *
     * @param game the game this screen belongs to. Needs to access the SpriteBatch batch
     */
    protected MenuScreen(final SpaceAttackGame game) {
        this.game = game;
        batch = game.getBatch();
        //  gameServices = game.getGameServices();
        skin = game.getSkin();

        viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        viewport.apply();

        stage = new Stage(viewport, batch);

        backgroundImg = new Image(game.getAssetManager().get("menuBackground.png", Texture.class));
        backgroundImg.setScale(VIEWPORT_WIDTH / backgroundImg.getWidth(), VIEWPORT_HEIGHT / backgroundImg.getHeight());

        titleImg = new Image(game.getAssetManager().get("space_attack_title.png", Texture.class));
        titleImg.setSize(0.9f * VIEWPORT_WIDTH, 0.3f * VIEWPORT_HEIGHT);
        titleImg.setPosition(VIEWPORT_WIDTH / 2 - titleImg.getWidth() / 2, VIEWPORT_HEIGHT * 0.98f - titleImg.getHeight());
    }

    /**
     * Constructs the abstract Menu Screen
     *
     * @param game the game this screen belongs to. Needs to access the SpriteBatch batch
     * @param width Width of the viewport
     * @param height Height of the viewport
     */
    protected MenuScreen(final SpaceAttackGame game, float width, float height) {
        this.game = game;
        batch = game.getBatch();

        skin = game.getSkin();

        viewport = new FitViewport(width, height);
        viewport.apply();

        stage = new Stage(viewport, batch);

        backgroundImg = new Image(game.getAssetManager().get("menuBackground.png", Texture.class));
        backgroundImg.setScale(width / backgroundImg.getWidth(), height / backgroundImg.getHeight());

        titleImg = new Image(game.getAssetManager().get("space_attack_title.png", Texture.class));
        titleImg.setSize(0.9f * width, 0.3f * height);
        titleImg.setPosition(width / 2 - titleImg.getWidth() / 2, height * 0.98f - titleImg.getHeight());
    }

    /**
     * Adds an Play Button to the table given as argument
     *
     * @param table table to be added the Play button
     * @param text String to be displayed as name of the button
     */
    protected void addPlayButton(Table table, String text) {
        TextButton playButton = new TextButton( text, skin);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Game.getInstance().restart();
                GameController.getInstance().restart();
                game.setScreen(new GameScreen(game));
            }
        });

        table.add(playButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
    }


    /**
     * Adds an Back To Main Menu Button to the table given as argument
     *
     * @param table table to be added the Back to Main Menu button
     */
    protected void addBackToMainMenuButton(Table table) {
        TextButton backButton = new TextButton("Main Menu", skin);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        table.add(backButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
    }

    /**
     * Adds an Exit Button to the table given as argument
     *
     * @param table table to be added the exit button
     */
    protected void addExitButton(Table table) {
        TextButton exitButton = new TextButton("Exit Game", skin);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        table.add(exitButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        stage.addActor(backgroundImg);
        stage.addActor(titleImg);
    }

    /**
     * Adds Background image on the stage
     */
    protected void showBackground()
    {
        stage.addActor(backgroundImg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        Gdx.input.setInputProcessor(stage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}