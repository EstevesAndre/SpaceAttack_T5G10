package com.spaceattack.game.view.screens;

import com.spaceattack.game.SpaceAttackGame;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Main Menu Screen - First menu shown when the application starts
 */
public class MainMenuScreen extends MenuScreen {

    /**
     * Constructs the abstract Main Menu Screen
     *
     * @param game the game this screen belongs to. Needs to access the SpriteBatch batch
     */
    public MainMenuScreen(final SpaceAttackGame game) {
        super(game);
    }

    /**
     * Adds the buttons to the table
     * - Play Button
     * - Options Button
     * - LeaderBoard Button
     * - Exit Button
     *
     * @param table table to be added the buttons
     */
    protected void createMenuButtons(Table table) {

        table.bottom();

        addPlayButton(table, "Play");
        addOptionsButton(table);
        addLeaderBoardButton(table);
        addExitButton(table);

        table.padBottom(BOTTOM_EDGE);
    }

    /**
     * Adds an LeaderBoard Button to the table given as argument
     *
     * @param table table to be added the LeaderBoard button
     */
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

    /**
     * Adds an Options Button to the table given as argument
     *
     * @param table table to be added the Options button
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        super.show();

        Table table = new Table();
        table.setFillParent(true);

        createMenuButtons(table);

        stage.addActor(table);
    }
}