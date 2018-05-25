package com.spaceattack.game.view.screens;

import com.spaceattack.game.SpaceAttackGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class MainMenuScreen extends MenuScreen {

    //Layout Macros

    protected static final float BUTTON_WIDTH = VIEWPORT_WIDTH / 2;

    protected static final float BUTTON_EDGE = VIEWPORT_WIDTH / 75;

    protected static final float BOTTOM_EDGE = VIEWPORT_WIDTH / 75;

    public MainMenuScreen(final SpaceAttackGame game) {
        super(game);
    }

    protected void createMenuButtons(Table table) {

        table.bottom();

        addPlayButton(table);
        addOptionsButton(table);
        //addNetworkingButton(table);
        addExitButton(table);

        table.padBottom(BOTTOM_EDGE);
    }

    private void addExitButton(Table table) {
        TextButton exitButton = new TextButton("Exit", skin1);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        table.add(exitButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
    }

    /*private void addNetworkingButton(Table table) {
        TextButton networkingButton = new TextButton("Networking", skin1);
        networkingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new NetworkingMenuScreen(game));
            }
        });
        table.add(networkingButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
    }*/

    private void addOptionsButton(Table table) {
        TextButton optionsButton = new TextButton("Options", skin1);
        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               // game.setScreen(new CustomizeMenuScreen(game));
            }
        });
        table.add(optionsButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
    }

    private void addPlayButton(Table table) {
        TextButton playButton = new TextButton("Play", skin1);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });
        table.add(playButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
    }

    @Override
    public void show() {
        super.show();

        Table table = new Table();
        table.setFillParent(true);

        createMenuButtons(table);

        stage.addActor(table);
    }
}