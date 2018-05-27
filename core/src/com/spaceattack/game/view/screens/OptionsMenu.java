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

public class OptionsMenu extends MenuScreen {

    private Label message;

    private Skin skin;

    private static final float DEFAULT_TEXT_SIZE = VIEWPORT_WIDTH / 30;

    /**
     * The Scale that should be applied to the Message Label's Font.
     */
    private static final float MESSAGE_FONT_SCALE = VIEWPORT_WIDTH / 800;


    OptionsMenu(SpaceAttackGame game) {
        super(game);

        skin = game.getSkin();

        message = new Label ("",skin);
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

        int i = 0;

        table.bottom();

        for (; i < highScores.size(); i++) {
            System.out.println(highScores.get(i).getScore());
            message = new Label("", skin);
            if (i % 2 == 0) {
                message.setText((i + 1) + "- " + highScores.get(i).getScore() + "-" + highScores.get(i).getDate() + " ");
                table.add(message).padBottom(DEFAULT_TEXT_SIZE).left();
                message.setFontScale(MESSAGE_FONT_SCALE, MESSAGE_FONT_SCALE);
            } else {
                message.setText( (i == 9 ? "" : " ") + (i + 1) + "- " + highScores.get(i).getScore() + "-" + highScores.get(i).getDate());
                table.add(message).padBottom(DEFAULT_TEXT_SIZE).left().row();
                message.setFontScale(MESSAGE_FONT_SCALE, MESSAGE_FONT_SCALE);
            }
        }
        table.pad(0.2f * VIEWPORT_HEIGHT);
    }

    private void drawTitle(Table table)
    {
        table.top();

        message = new Label("LEADERBOARD", skin);
        message.setFontScale(2);

        table.add(message).padTop(DEFAULT_TEXT_SIZE * 2);

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
