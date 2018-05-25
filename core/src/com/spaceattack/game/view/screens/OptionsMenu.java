package com.spaceattack.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.spaceattack.game.SpaceAttackGame;
import com.spaceattack.game.controller.GameController;
import com.spaceattack.game.model.Game;

/**
 * Created by estev on 24/05/2018.
 */

public abstract class OptionsMenu extends Stage {

    /**
     * The width of the HUD viewport in pixels. The height is
     * automatically calculated using the screen ratio.
     */
    private static final float CONTROL_VIEWPORT_WIDTH = 1000;

    /**
     * The height of the viewport in pixels. The height is
     * automatically calculated using the screen ratio.
     */
    private static final float CONTROL_VIEWPORT_HEIGHT = CONTROL_VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());

    /**
     * The Scale that should be applied to the Message Label's Font.
     */
    private static final float MESSAGE_FONT_SCALE = CONTROL_VIEWPORT_WIDTH / 400;
    /**
     * The Scale that should be applied to the Score Label's Font.
     */
    private static final float SCORE_FONT_SCALE = CONTROL_VIEWPORT_WIDTH / 600;
    /**
     * Distance between the Labels and the other stage elements
     */
    private static final float LABEL_DISTANCE = CONTROL_VIEWPORT_HEIGHT / 40;
    /**
     * Width of the Options Menu's Buttons.
     */
    private static final float BUTTON_WIDTH = CONTROL_VIEWPORT_WIDTH / 3;
    /**
     * Height of the Options Menu's Buttons.
     */
    private static final float BUTTON_HEIGHT = CONTROL_VIEWPORT_HEIGHT / 8;
    /**
     * Distance between Buttons and other stage elements.
     */
    private static final float BUTTON_DISTANCE = CONTROL_VIEWPORT_HEIGHT / 18;


    protected SpaceAttackGame game;

    protected BitmapFont title;

    protected TextButton.TextButtonStyle textButtonStyle;

    protected Table table;

    protected Label message;

    private Skin skin;

    protected OptionsMenu(Viewport viewport, SpaceAttackGame game)
    {
        super(viewport,game.getBatch());

        this.game = game;

        table = new Table();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("virgo.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 70;
        title = generator.generateFont(parameter);

        skin = new Skin();
        TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
        skin.addRegions(buttonAtlas);

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = title;
        textButtonStyle.up = skin.getDrawable("up-button");
        textButtonStyle.down = skin.getDrawable("down-button");
        textButtonStyle.checked = skin.getDrawable("checked-button");

        createStageObjects();
    }

    @Override
    public void draw()
    {
        super.draw();
        this.act();
    }

    protected abstract void createStageObjects();

    protected void addExitButton()
    {
        TextButton exitButton = new TextButton("Exit", textButtonStyle);

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.add(exitButton).size(BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    protected void addBackToMainMenuButton()
    {
        TextButton backToMainMenuButton = new TextButton("Restart", textButtonStyle);

        backToMainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        table.add(backToMainMenuButton).size(BUTTON_WIDTH, BUTTON_HEIGHT).padBottom(BUTTON_DISTANCE).row();
    }

  /*  protected TextButton addLeaderboardButton()
    {
        TextButton leaderboardButton = new TextButton("Leaderboard", textButtonStyle);

        leaderboardButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }
        });

        return leaderboardButton;
    }*/

    protected void addResumeButton()
    {
        TextButton resumeButton = new TextButton("Resume", textButtonStyle);

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                togglePause();
            }
        });

        table.add(resumeButton).size(BUTTON_WIDTH, BUTTON_HEIGHT).padBottom(BUTTON_DISTANCE).row();
    }

    protected void initStage() {
        table.setFillParent(true);

        table.add(message).padBottom(LABEL_DISTANCE).row();
        message.setFontScale(MESSAGE_FONT_SCALE, MESSAGE_FONT_SCALE);
    }

    private void togglePause()
    {
      //  control.togglePause();
    }

    protected abstract void setMessage();

}
