package com.spaceattack.game.view.screens;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.spaceattack.game.SpaceAttackGame;

/**
 * Created by estev on 24/05/2018.
 */

public class EndGameMenu extends OptionsMenu {


    EndGameMenu(Viewport viewport, SpaceAttackGame game) {
        super(viewport, game);
        setMessage();
    }

    @Override
    protected void createStageObjects() {
        initStage();
        addBackToMainMenuButton();
        addExitButton();
        this.addActor(table);
    }

    @Override
    protected void setMessage() {
        message.setText("YOU LOST!");
    }
}
