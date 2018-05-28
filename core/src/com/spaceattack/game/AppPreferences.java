package com.spaceattack.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * App Preferences to use on Game Sounds and Musics
 */
public class AppPreferences {

    private static final String PREF_MUSIC_VOLUME = "volume";
    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREF_SOUND_ENABLED = "sound.enabled";
    private static final String PREF_SOUND_VOL = "sound";
    private static final String PREFS_NAME = "spaceattack";

    /**
     * Gets the preferences according with PREFS_NAME
     *
     * @return Return respective preferences
     */
    protected Preferences getPrefs() {
        return Gdx.app.getPreferences(PREFS_NAME);
    }

    /**
     * Gets Sound Check Box Variable (True or False)
     *
     * @return false if is not checked, true otherwise
     */
    public boolean isSoundEnabled() {
        return getPrefs().getBoolean(PREF_SOUND_ENABLED, true);
    }

    /**
     * Sets Sound Check Box Variable and updates preferences
     *
     * @param soundEffectsEnabled Boolean to set Sound Check Box
     */
    public void setSoundEnabled(boolean soundEffectsEnabled) {
        Preferences pref = getPrefs().putBoolean(PREF_SOUND_ENABLED, soundEffectsEnabled);
        pref.flush();
    }

    /**
     * Gets Music Check Box Variable (True or False)
     *
     * @return false if not checked, true otherwise
     */
    public boolean isMusicEnabled() {
        return getPrefs().getBoolean(PREF_MUSIC_ENABLED, true);
    }

    /**
     * Sets Music Check Box Variable and updates preferences
     *
     * @param musicEnabled Boolean to set Music Check Box
     */
    public void setMusicEnabled(boolean musicEnabled) {
        Preferences pref = getPrefs().putBoolean(PREF_MUSIC_ENABLED, musicEnabled);
        pref.flush();
    }

    /**
     * Gets Music Volume
     *
     * @return Returns music volume or 0.5f as default value
     */
    public float getMusicVolume() {
        return getPrefs().getFloat(PREF_MUSIC_VOLUME, 0.5f);
    }

    /**
     * Sets Music Volume and updates preferences
     *
     * @param volume value to set the music volume between 0.0f and 1.0f
     */
    public void setMusicVolume(float volume) {
        Preferences pref = getPrefs().putFloat(PREF_MUSIC_VOLUME, volume);
        pref.flush();
    }

    /**
     * Gets the volume of sounds of the game
     *
     * @return Returns sound volume or 0.5f as default value
     */
    public float getSoundVolume() {
        return getPrefs().getFloat(PREF_SOUND_VOL, 0.5f);
    }

    /**
     * Sets sound volume and updates preferences
     *
     * @param volume value to set sound volume between 0.0f and 1.0f
     */
    public void setSoundVolume(float volume) {
        Preferences pref = getPrefs().putFloat(PREF_SOUND_VOL, volume);
        pref.flush();
    }
}
