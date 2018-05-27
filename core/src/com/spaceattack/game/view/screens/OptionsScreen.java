package com.spaceattack.game.view.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.spaceattack.game.SpaceAttackGame;

/**
 * Created by estev on 27/05/2018.
 */

public class OptionsScreen extends MenuScreen {

    private Label message;

    private BitmapFont font;

    private Label.LabelStyle style;

    private static final float DEFAULT_TEXT_SIZE = VIEWPORT_WIDTH / 30;

    public OptionsScreen(final SpaceAttackGame game) {
        super(game);

        font = game.getBitMapFont();

        font.getData().markupEnabled = true;

        style = new Label.LabelStyle(font, null);

        message = new Label("",style);
    }

    protected void addButtons(Table table) {

        table.bottom();
        addBackToMainMenuButton(table);

        table.padBottom(BOTTOM_EDGE);
    }

    private void drawTitle(Table table) {
        table.top();

        message = new Label("[#008fff]OPTIONS", style);

        table.add(message).padTop(DEFAULT_TEXT_SIZE);
    }

    @Override
    public void show() {
        super.showBackground();

        Table table = new Table();
        table.setFillParent(true);
        //table.setDebug(true);

        addButtons(table);

        Table title = new Table();
        title.setFillParent(true);
//        title.setDebug(true);

        drawTitle(title);

        stage.addActor(table);
        stage.addActor(title);
    }
}