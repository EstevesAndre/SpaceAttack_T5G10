package com.spaceattack.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spaceattack.game.SpaceAttackGame;

/**
 * User Bullet View
 */
public class UserBulletView extends ObjectView{

    /**
     * Constructs a user ship bullet view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public UserBulletView(SpaceAttackGame game) {
        super(game);
    }

    /**
     * Creates a sprite representing this bullet user ship.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing the user ship bullet
     */
    @Override
    public Sprite createSprite(SpaceAttackGame game) {

        return new Sprite(createUserBulletRegion(game));
    }

    /**
     * Creates the texture used for user ship bullet
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the texture used for each bullet
     */
    private TextureRegion createUserBulletRegion(SpaceAttackGame game) {
        Texture UserShipBulletTexture = game.getAssetManager().get("userShip/laserRed.png");
        return new TextureRegion(UserShipBulletTexture, UserShipBulletTexture.getWidth(), UserShipBulletTexture.getHeight());
    }

}
