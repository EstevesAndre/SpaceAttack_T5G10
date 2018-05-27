package com.spaceattack.game.view.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.spaceattack.game.SpaceAttackGame;
//import com.spaceattack.game.GameServices;

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


public abstract class MenuScreen extends ScreenAdapter {

    protected final SpaceAttackGame game;

    protected Stage stage;

    private Viewport viewport;

    protected SpriteBatch batch;

    protected Skin skin;

    private Image backgroundImg;

    private Image titleImg;

    /**
     * Game Services used for Networking.
     */
    // GameServices gameServices;

    protected static final float VIEWPORT_WIDTH = Gdx.graphics.getWidth();

    protected static final float VIEWPORT_HEIGHT = Gdx.graphics.getHeight();

    protected static final float BUTTON_WIDTH = VIEWPORT_WIDTH / 2;

    protected static final float BUTTON_EDGE = VIEWPORT_WIDTH / 75;

    protected static final float BOTTOM_EDGE = VIEWPORT_WIDTH / 75;

    protected static final float DEFAULT_BUTTON_SIZE = VIEWPORT_WIDTH / 15;

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

    @Override
    public void show() {
        //Displaying the background and the Image
        stage.addActor(backgroundImg);
        stage.addActor(titleImg);
    }

    protected void showBackground()
    {
        stage.addActor(backgroundImg);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}