package com.spaceattack.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spaceattack.game.SpaceAttackGame;
import com.spaceattack.game.model.Game;

/**
 * Created by estev on 24/05/2018.
 */

public class EndGameMenu extends MenuScreen {

    private Label message;

    private Skin skin;

    EndGameMenu(SpaceAttackGame game) {
        super(game);

        skin = game.getSkin();
        message = new Label ("GAME OVER!", skin);
        message.setFontScale(2);
    }


    protected void createMenuButtons(Table table) {

        table.bottom();
        Label score = new Label("SCORE: " + (int)Game.getInstance().getScore(), skin);
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
