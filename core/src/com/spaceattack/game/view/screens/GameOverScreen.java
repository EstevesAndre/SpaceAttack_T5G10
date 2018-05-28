package com.spaceattack.game.view.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.spaceattack.game.SpaceAttackGame;
import com.spaceattack.game.model.Game;

/**
 * Game Over Screen
 */
public class GameOverScreen extends MenuScreen {

    /**
     * Label to display the message ("GAME OVER")
     */
    private Label message;

    /**
     * Constructs the abstract Game Over Menu Screen
     *
     * @param game the game this screen belongs to. Needs to access the SpriteBatch batch
     */
    GameOverScreen(SpaceAttackGame game) {
        super(game);

        BitmapFont font = game.getBitMapFont();
        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        message = new Label ("GAME OVER!", style);
    }

    /**
     * Adds the buttons to the table
     * - message Label
     * - score Label
     * - Play Button
     * - Back to Main Menu Button
     * - Exit Button
     *
     * @param table table to be added the buttons
     */
    protected void createMenuButtons(Table table) {

        table.bottom();
        Label score = new Label("SCORE: " + (int) Game.getInstance().getScore(), skin);
        table.add(message).row();
        table.add(score).row();

        addPlayButton(table, "Play Again");

        addBackToMainMenuButton(table);

        addExitButton(table);

        table.padBottom(BOTTOM_EDGE);
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
