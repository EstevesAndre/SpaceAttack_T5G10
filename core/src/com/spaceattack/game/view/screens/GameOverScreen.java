package com.spaceattack.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.spaceattack.game.SpaceAttackGame;
import com.spaceattack.game.model.Game;

public class GameOverScreen extends MenuScreen {

    private Label message;

    GameOverScreen(SpaceAttackGame game) {
        super(game);

        BitmapFont font = game.getBitMapFont();
        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        message = new Label ("GAME OVER!", style);
    }


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
