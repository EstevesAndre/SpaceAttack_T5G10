package com.spaceattack.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spaceattack.game.SpaceAttackGame;

public class EnemyShipViewYellow extends ObjectView{

    /**
     * Constructs a space ship model.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public EnemyShipViewYellow(SpaceAttackGame game) {
        super(game);
    }

    /**
     * Creates a sprite representing this space ship.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this space ship
     */
    @Override
    public Sprite createSprite(SpaceAttackGame game) {

        return new Sprite(createUserShipRegion(game));
    }

    /**
     * Creates the texture used when the ship is not accelerating
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the texture used when the ship is not accelerating
     */
    private TextureRegion createUserShipRegion(SpaceAttackGame game) {
        Texture UserShipTexture = game.getAssetManager().get("enemyShips/enemyShipYellow.png");

        return new TextureRegion(UserShipTexture, UserShipTexture.getWidth(), UserShipTexture.getHeight());
    }

}
