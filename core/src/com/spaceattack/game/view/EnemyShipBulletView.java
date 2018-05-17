package com.spaceattack.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spaceattack.game.SpaceAttackGame;

/**
 * Created by estev on 17/05/2018.
 */

public class EnemyShipBulletView extends ObjectView{

    /**
     * Constructs a enemy ship bullet view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public EnemyShipBulletView(SpaceAttackGame game) {
        super(game);
    }

    /**
     * Creates a sprite representing this enemy ship bullet.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing the enemy ship bullet
     */
    @Override
    public Sprite createSprite(SpaceAttackGame game) {

        return new Sprite(createEnemyShipBulletRegion(game));
    }

    /**
     * Creates the texture used for enemy ship bullet
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the texture used for each bullet
     */
    private TextureRegion createEnemyShipBulletRegion(SpaceAttackGame game) {
        Texture EnemyShipBulletTexture = game.getAssetManager().get("bullet_Enemy_Ship.png");
        return new TextureRegion(EnemyShipBulletTexture, EnemyShipBulletTexture.getWidth(), EnemyShipBulletTexture.getHeight());
    }

}
