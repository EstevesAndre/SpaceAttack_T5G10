package com.spaceattack.game.view.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spaceattack.game.SpaceAttackGame;
import com.spaceattack.game.controller.GameController;
import com.spaceattack.game.model.Score;

import java.util.List;


/**
 * Created by estev on 27/05/2018.
 */

public class LeaderboardMenuScreen extends MenuScreen {

    private Label message;

    private Skin skin;

    private BitmapFont font;

    private Label.LabelStyle style;

    private static final float DEFAULT_TEXT_SIZE = VIEWPORT_WIDTH / 30;

    /**
     * The Scale that should be applied to the Message Label's Font.
     */
    private static final float MESSAGE_FONT_SCALE = VIEWPORT_WIDTH / 1800;


    LeaderboardMenuScreen(SpaceAttackGame game) {
        super(game);

        skin = game.getSkin();

        font = game.getBitMapFont();

        font.getData().markupEnabled = true;

        style = new Label.LabelStyle(font, null);

        message = new Label("", skin);

    }

    private void createMenuButtons(Table table) {

        table.bottom();

        addBackToMainMenuButton(table);

        table.padBottom(BOTTOM_EDGE);
    }

    protected void addBackToMainMenuButton(Table table) {
        TextButton backButton = new TextButton("Back To Main Menu", skin);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        backButton.getLabel().setFontScale(0.8f);
        table.add(backButton).size((BUTTON_WIDTH + 50) * 0.8f, DEFAULT_BUTTON_SIZE * 0.8f).pad(BUTTON_EDGE * 0.8f).row();
    }

    private void drawHighScores(Table table) {
        List<Score> highScores = GameController.getInstance().getHighScores();

        Label[] scoresArray = new Label[10];

        for (int i = 0; i < 10; i++) {
            message = new Label("", style);

            if (i < highScores.size()) {
                if (i + 1 == 1) {
                    message.setText("[#ff0000]" + (i + 1) + "ST [#00ff00]" + highScores.get(i).getScore() + " [#0000ff] " + highScores.get(i).getDate() + " ");
                } else if (i + 1 == 2) {
                    message.setText("[#ff0000]" + (i + 1) + "ND [#00ff00]" + highScores.get(i).getScore() + " [#0000ff] " + highScores.get(i).getDate() + " ");
                } else if (i + 1 == 3) {
                    message.setText("[#ff0000]" + (i + 1) + "RD [#00ff00]" + highScores.get(i).getScore() + " [#0000ff] " + highScores.get(i).getDate() + " ");
                } else {
                    message.setText("[#ff0000]" + (i + 1) + "TH [#00ff00]" + highScores.get(i).getScore() + " [#0000ff] " + highScores.get(i).getDate() + " ");
                }
                scoresArray[i] = message;
            } else {
                scoresArray[i] = message;
            }
        }

        table.bottom();

        if (highScores.size() <= 5) {
            for (int i = 0; i < highScores.size(); i++) {
                table.add(scoresArray[i]).padBottom(DEFAULT_TEXT_SIZE).right().row();
                scoresArray[i].setFontScale(MESSAGE_FONT_SCALE);
            }
        } else {
            for (int i = 0; i < scoresArray.length / 2.0; i++) {
                table.add(scoresArray[i]).padBottom(DEFAULT_TEXT_SIZE).right();
                scoresArray[i].setFontScale(MESSAGE_FONT_SCALE);

                table.add(scoresArray[i + scoresArray.length / 2]).padBottom(DEFAULT_TEXT_SIZE).right().row();
                scoresArray[i + scoresArray.length / 2].setFontScale(MESSAGE_FONT_SCALE);
            }
        }
        table.pad(0.2f * VIEWPORT_HEIGHT);
    }

    private void drawTitle(Table table) {
        table.top();

        message = new Label("[#008fff]LEADERBOARD", style);

        table.add(message).padTop(DEFAULT_TEXT_SIZE);
    }

    @Override
    public void show() {
        super.showBackground();

        Table table = new Table();
        table.setFillParent(true);
//        table.setDebug(true);

        createMenuButtons(table);

        Table tableScores = new Table();
        tableScores.setFillParent(true);
//        tableScores.setDebug(true);

        drawHighScores(tableScores);

        Table title = new Table();
        title.setFillParent(true);
//        title.setDebug(true);

        drawTitle(title);

        stage.addActor(table);
        stage.addActor(tableScores);
        stage.addActor(title);
    }
}
