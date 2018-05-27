package com.spaceattack.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spaceattack.game.SpaceAttackGame;


public class PauseMenu extends MenuScreen{

    private final GameScreen gs;
    private Label message;

    PauseMenu(SpaceAttackGame game, GameScreen gs) {
        super(game);
        this.gs = gs;
        BitmapFont font = game.getBitMapFont();
        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        message = new Label ("PAUSE\n", style);
    }


    protected void createMenuButtons(Table table) {

        table.bottom();
        table.add(message).row();

        addPlayButton(table, "Continue");

        addBackToMainMenuButton(table);

        table.padBottom(BOTTOM_EDGE);
    }

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
