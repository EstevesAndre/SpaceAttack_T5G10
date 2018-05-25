package com.spaceattack.game.view.screens;

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


public abstract class MenuScreen extends ScreenAdapter {

    protected final SpaceAttackGame game;

    protected Stage stage;

    private Viewport viewport;

    private SpriteBatch batch;

    protected Skin skin1;

    protected Skin skin2;

    /**
     * Game Services used for Networking.
     */
    // GameServices gameServices;

    protected static final float VIEWPORT_WIDTH = 750;

    private static final float VIEWPORT_HEIGHT = VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());

    private Image backgroundImg;

    private Image titleImg;

    protected static final float DEFAULT_BUTTON_SIZE = VIEWPORT_WIDTH / 15;

    protected MenuScreen(final SpaceAttackGame game) {
        this.game = game;
        batch = game.getBatch();
        //  gameServices = game.getGameServices();
          skin1 = game.getSkin();
        //  skin2 = game.getSecondarySkin();

        viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        viewport.apply();

        stage = new Stage(viewport, batch);

        backgroundImg = new Image(game.getAssetManager().get("menuBackground.png", Texture.class));
        backgroundImg.setScale(VIEWPORT_WIDTH / backgroundImg.getWidth(), VIEWPORT_HEIGHT / backgroundImg.getHeight());

        titleImg = new Image(game.getAssetManager().get("space_attack_title.png", Texture.class));
        titleImg.setSize(0.8f * titleImg.getWidth(), 0.8f * titleImg.getHeight());
        titleImg.setPosition(VIEWPORT_WIDTH / 2 - titleImg.getWidth() / 2, VIEWPORT_HEIGHT * 0.98f - titleImg.getHeight());
    }


    protected TextButton addBackButton(boolean style) {
        TextButton backButton = new TextButton("Back", (style ? skin2 : skin1));

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        return backButton;
    }

    @Override
    public void show() {
        //Displaying the background and the Image
        stage.addActor(backgroundImg);
        stage.addActor(titleImg);
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