package com.spaceattack.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spaceattack.game.SpaceAttackGame;

public class ShieldPowerUpView extends ObjectView{

    /**
     * Constructs a health power up model.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public ShieldPowerUpView(SpaceAttackGame game) {
        super(game);
    }

    /**
     * Creates a sprite representing this power up.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this power up
     */
    @Override
    public Sprite createSprite(SpaceAttackGame game) {

        return new Sprite(createPowerUpRegion(game));
    }

    /**
     * Creates the texture used
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the texture used
     */
    private TextureRegion createPowerUpRegion(SpaceAttackGame game) {
        Texture PowerUpTexture = game.getAssetManager().get("shieldPower.png");
        return new TextureRegion(PowerUpTexture, PowerUpTexture.getWidth(), PowerUpTexture.getHeight());
    }

}
