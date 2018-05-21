package com.spaceattack.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spaceattack.game.SpaceAttackGame;

/**
 * Created by estev on 17/05/2018.
 */

public class EnemyBulletView extends ObjectView{

    /**
     * Constructs a enemy ship bullet view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public EnemyBulletView(SpaceAttackGame game) {
        super(game);
    }

    /**
     * Creates a sprite representing this bullet enemy ship.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing the enemy ship bullet
     */
    @Override
    public Sprite createSprite(SpaceAttackGame game) {

        return new Sprite(createUserBulletRegion(game));
    }

    /**
     * Creates the texture used for enemy ship bullet
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the texture used for each bullet
     */
    private TextureRegion createUserBulletRegion(SpaceAttackGame game) {
        Texture UserShipBulletTexture = game.getAssetManager().get("laserGreen.png");
        return new TextureRegion(UserShipBulletTexture, UserShipBulletTexture.getWidth(), UserShipBulletTexture.getHeight());
    }

}
