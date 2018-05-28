package com.spaceattack.game.view.screens;

import com.spaceattack.game.SpaceAttackGame;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuScreen extends MenuScreen {

    public MainMenuScreen(final SpaceAttackGame game) {
        super(game);
    }

    protected void createMenuButtons(Table table) {

        table.bottom();

        addPlayButton(table, "Play");
        addOptionsButton(table);
        addLeaderBoardButton(table);

        //addNetworkingButton(table);
        addExitButton(table);

        table.padBottom(BOTTOM_EDGE);
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

    private void addLeaderBoardButton(Table table) {
        TextButton leaderboardButton = new TextButton("Leaderboard", skin);
        leaderboardButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LeaderboardMenuScreen(game));
            }
        });
        table.add(leaderboardButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
    }

    private void addOptionsButton(Table table) {
        TextButton optionsButton = new TextButton("Options", skin);
        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new OptionsScreen(game));
            }
        });
        table.add(optionsButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
    }


    @Override
    public void show() {
        super.show();

        Table table = new Table();
        table.setFillParent(true);
        //table.setDebug(true);

        createMenuButtons(table);

        stage.addActor(table);
    }
}