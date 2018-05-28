package com.spaceattack.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spaceattack.game.SpaceAttackGame;

/**
 * Portal (Enemy Ship Spawn) View
 */
public class ExplodingGreenShipView extends ObjectView{

    /**
     * The time between the animation frames
     */
    private static final float FRAME_TIME = 0.05f;

    /**
     * The animation used
     */
    private Animation<TextureRegion> animation;

    /**
     * The texture used
     */
    private TextureRegion stopRegion;

    /**
     * Time since the space ship started the game. Used
     * to calculate the frame to show in animations.
     */
    private static float stateTime = 0;

    /**
     * Constructs a space ship model.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public ExplodingGreenShipView(SpaceAttackGame game) {
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
        animation = createAnimation(game);
        stopRegion = createRegion(game);

        return new Sprite(stopRegion);
    }

    /**
     * Creates the texture used when the ship is not accelerating
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the texture used when the ship is not accelerating
     */
    private TextureRegion createRegion(SpaceAttackGame game) {
        Texture stopTexture = game.getAssetManager().get("animations/greenExplosion.png");
        return new TextureRegion(stopTexture, stopTexture.getWidth() / 11, stopTexture.getHeight());
    }

    /**
     * Creates the animation used when the ship is accelerating
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the animation used when the ship is accelerating
     */
    private Animation<TextureRegion> createAnimation(SpaceAttackGame game) {
        Texture portalTexture = game.getAssetManager().get("animations/greenExplosion.png");
        TextureRegion[][] portalRegion = TextureRegion.split(portalTexture, portalTexture.getWidth() / 11, portalTexture.getHeight());

        TextureRegion[] frames = new TextureRegion[11];
        System.arraycopy(portalRegion[0], 0, frames, 0, 11);

        return new Animation<TextureRegion>(FRAME_TIME, frames);
    }


    /**
     * Draws the sprite from this view using a sprite batch.
     * Chooses the correct texture or animation to be used
     * depending on the accelerating flag.
     *
     * @param batch The sprite batch to be used for drawing.
     */
    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        sprite.setRegion(animation.getKeyFrame(stateTime, true));

        sprite.draw(batch);
    }

}
