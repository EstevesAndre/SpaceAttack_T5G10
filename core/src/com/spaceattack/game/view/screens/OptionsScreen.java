package com.spaceattack.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
 * Options Screen
 */
public class OptionsScreen extends MenuScreen {

    /**
     * Message to be added to the table and the stage
     */
    private Label message;

    /**
     * BitmapFont to be applied to the Label.LabelStyle
     */
    private BitmapFont font;

    /**
     * Skin to be applied to the sliders and checkBoxes
     */
    private Skin slider_CheckBox_Skin;

    /**
     * style to be applied to the message Label
     */
    private Label.LabelStyle style;

    /**
     * volumeMusic Label
     */
    private Label volumeMusicLabel;

    /**
     * volumeSound Label
     */
    private Label volumeSoundLabel;

    /**
     * musicOnOff Label
     */
    private Label musicOnOffLabel;

    /**
     * soundOnOff Label
     */
    private Label soundOnOffLabel;

    /**
     * The width of the viewport in pixels.
     */
    protected static final float VIEWPORT_WIDTH = 750;

    /**
     * The height of the viewport in pixels.
     */
    protected static final float VIEWPORT_HEIGHT = VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());

    private static final float DEFAULT_TEXT_SIZE = VIEWPORT_WIDTH / 30;

    /**
     * Constructs the abstract Options Menu Screen
     *
     * @param game the game this screen belongs to. Needs to access the SpriteBatch batch
     */
    public OptionsScreen(final SpaceAttackGame game) {
        super(game, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        font = game.getBitMapFont();

        slider_CheckBox_Skin = game.getSkinSliderCheckBox();

        font.getData().markupEnabled = true;

        style = new Label.LabelStyle(font, null);

        message = new Label("", style);
    }

    /**
     * Adds the back to main menu button to the given table
     *
     * @param table table to be added the buttons
     */
    protected void addButtons(Table table) {

        table.bottom();

        addBackToMainMenuButton(table);

        table.padBottom(BOTTOM_EDGE);
    }

    /**
     * Adds Back to Main Menu Button to the table given as argument
     *
     * @param table table to be added the Back to Main Menu button
     */
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

    /**
     * Adds the message, "OPTIONS", to the given table
     *
     * @param table table to be added the Label
     */
    private void drawTitle(Table table) {
        table.top();

        message = new Label("[#008fff]OPTIONS", style);

        table.add(message).padTop(DEFAULT_TEXT_SIZE);
    }

    /**
     * Adds the Option Elements to the given table
     * - Slider volumeMusicSlider
     * - Slider soundMusicSlider
     * - CheckBox musicCheckBox
     * - CheckBox soundCheckBox
     * - Label volumeMusicLabel
     * - Label volumeSoundLabel
     * - Label musicOnOffLabel
     * - Label soundOnOffLabel
     *
     * @param table table to be added the Option Elements
     */
    private void addOptionElements(Table table) {

        // MUSIC VOLUME
        final Slider volumeMusicSlider = new Slider(0f, 1f, 0.1f, false, slider_CheckBox_Skin);
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
        final Slider soundMusicSlider = new Slider(0f, 1f, 0.1f, false, slider_CheckBox_Skin);
        soundMusicSlider.setValue(game.getPreferences().getSoundVolume());
        soundMusicSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                game.getPreferences().setSoundVolume(soundMusicSlider.getValue());
                return false;
            }
        });

        // MUSIC ON/OFF
        final CheckBox musicCheckBox = new CheckBox(null, slider_CheckBox_Skin);
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
        final CheckBox soundCheckBox = new CheckBox(null, slider_CheckBox_Skin);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        super.showBackground();

        Table table = new Table();
        table.setFillParent(true);
        addButtons(table);

        Table title = new Table();
        title.setFillParent(true);
        drawTitle(title);

        Table options = new Table();
        options.setFillParent(true);
        addOptionElements(options);

        stage.addActor(table);
        stage.addActor(title);
        stage.addActor(options);
    }
}