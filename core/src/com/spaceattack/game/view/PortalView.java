package com.spaceattack.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spaceattack.game.SpaceAttackGame;

public class PortalView extends ObjectView{

    /**
     * Constructs a portal model.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public PortalView(SpaceAttackGame game) {
        super(game);
    }

    /**
     * Creates a sprite representing this space ship.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this portal
     */
    @Override
    public Sprite createSprite(SpaceAttackGame game) {

        return new Sprite(createPortalRegion(game));
    }

    /**
     * Creates the texture for the portal
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the texture used for the portal
     */
    private TextureRegion createPortalRegion(SpaceAttackGame game) {
        Texture PortalTexture = game.getAssetManager().get("portal.png");
        return new TextureRegion(PortalTexture, PortalTexture.getWidth(), PortalTexture.getHeight());
    }

}
