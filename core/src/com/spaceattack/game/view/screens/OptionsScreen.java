package com.spaceattack.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spaceattack.game.SpaceAttackGame;

/**
 * Created by estev on 27/05/2018.
 */

public class OptionsScreen extends MenuScreen {

    private Label message;

    private BitmapFont font;

    private Skin skin12;

    private Label.LabelStyle style;

    private Label volumeMusicLabel;

    private Label volumeSoundLabel;

    private Label musicOnOffLabel;

    private Label soundOnOffLabel;

    protected static final float VIEWPORT_WIDTH = 750;

    protected static final float VIEWPORT_HEIGHT = VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());

    protected static final float BUTTON_WIDTH = VIEWPORT_WIDTH / 2;

    protected static final float BUTTON_EDGE = VIEWPORT_WIDTH / 75;

    protected static final float BOTTOM_EDGE = VIEWPORT_WIDTH / 75;

    protected static final float DEFAULT_BUTTON_SIZE = VIEWPORT_WIDTH / 15;

    private static final float DEFAULT_TEXT_SIZE = VIEWPORT_WIDTH / 30;

    public OptionsScreen(final SpaceAttackGame game) {
        super(game, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        font = game.getBitMapFont();

        skin12 = game.getSkin2();

        font.getData().markupEnabled = true;

        style = new Label.LabelStyle(font, null);

        message = new Label("", style);
    }

    protected void addButtons(Table table) {

        table.bottom();

        addBackToMainMenuButton(table);

        table.padBottom(BOTTOM_EDGE);
    }

    protected void addBackToMainMenuButton(Table table) {
        TextButton backButton = new TextButton("Main Menu", skin);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        table.add(backButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
    }

    private void drawTitle(Table table) {
        table.top();

        message = new Label("[#008fff]OPTIONS", style);

        table.add(message).padTop(DEFAULT_TEXT_SIZE);
    }

    private void addOptionElements(Table table) {

        // MUSIC VOLUME
        final Slider volumeMusicSlider = new Slider(0f, 1f, 0.1f, false, skin12);
        volumeMusicSlider.setValue(game.getPreferences().getMusicVolume());
        volumeMusicSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                game.getPreferences().setMusicVolume(volumeMusicSlider.getValue());
                game.getMusic().setVolume(volumeMusicSlider.getValue());
                return false;
            }
        });

        // SOUND VOLUME
        final Slider soundMusicSlider = new Slider(0f, 1f, 0.1f, false, skin12);
        soundMusicSlider.setValue(game.getPreferences().getSoundVolume());
        soundMusicSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                game.getPreferences().setSoundVolume(soundMusicSlider.getValue());
                return false;
            }
        });

        // MUSIC ON/OFF
        final CheckBox musicCheckBox = new CheckBox(null, skin12);
        musicCheckBox.setChecked(game.getPreferences().isMusicEnabled());
        musicCheckBox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = musicCheckBox.isChecked();
                game.getPreferences().setMusicEnabled(enabled);
                if(enabled)
                    game.getMusic().play();
                else
                    game.getMusic().pause();

                return false;
            }
        });

        // SOUND ON/OFF
        final CheckBox soundCheckBox = new CheckBox(null, skin12);
        soundCheckBox.setChecked(game.getPreferences().isSoundEnabled());
        soundCheckBox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = soundCheckBox.isChecked();
                game.getPreferences().setSoundEnabled(enabled);
                return false;
            }
        });

        volumeMusicLabel = new Label("Music Volume   ", skin);
        volumeSoundLabel = new Label("Sound Volume   ", skin);
        musicOnOffLabel = new Label("Music           ", skin);
        soundOnOffLabel = new Label("Sound           ", skin);

        table.bottom();
        table.row().pad(10,0,30,10);
        table.add(volumeMusicLabel).left();
        table.add(volumeMusicSlider);
        table.row().pad(10,0,30,10);
        table.add(musicOnOffLabel).left();
        table.add(musicCheckBox);
        table.row().pad(10,0,30,10);
        table.add(volumeSoundLabel).left();
        table.add(soundMusicSlider);
        table.row().pad(10,0,30,10);
        table.add(soundOnOffLabel).left();
        table.add(soundCheckBox);
        table.padBottom(0.15f * VIEWPORT_HEIGHT);
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

        Table options = new Table();
        options.setFillParent(true);
        //options.setDebug(true);

        addOptionElements(options);

        stage.addActor(table);
        stage.addActor(title);
        stage.addActor(options);
    }
}