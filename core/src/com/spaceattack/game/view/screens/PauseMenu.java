package com.spaceattack.game.view.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spaceattack.game.SpaceAttackGame;


/**
 * Pause Menu Screen
 */
public class PauseMenu extends MenuScreen{

    /**
     * GameScreen used to resume the paused game
     */
    private final GameScreen gs;

    /**
     * Label to be added to the table on the current stage
     */
    private Label message;

    /**
     * Constructs the abstract Pause Menu Screen
     *
     * @param game the game this screen belongs to. Needs to access the SpriteBatch batch
     * @param gs GameScreen to back to the game screen
     */
    PauseMenu(SpaceAttackGame game, GameScreen gs) {
        super(game);
        this.gs = gs;
        BitmapFont font = game.getBitMapFont();
        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        message = new Label ("PAUSE\n", style);
    }

    /**
     * Adds the buttons to the table
     * - Continue(Play) Button
     * - Back to Main Menu Button
     *
     * @param table table to be added the buttons
     */
    protected void createMenuButtons(Table table) {

        table.bottom();
        table.add(message).row();

        addPlayButton(table, "Continue");

        addBackToMainMenuButton(table);

        table.padBottom(BOTTOM_EDGE);
    }

    /**
     * Adds an Play Button to the table given as argument
     *
     * @param table table to be added the Play button
     */
    protected void addPlayButton(Table table, String text) {
        TextButton playButton = new TextButton( text, skin);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(gs);
            }
        });

        table.add(playButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
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
