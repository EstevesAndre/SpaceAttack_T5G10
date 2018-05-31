package com.spaceattack.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spaceattack.game.SpaceAttackGame;
import com.spaceattack.game.model.GameObject;
import com.spaceattack.game.model.Ship;

/**
 * User Ship View
 */
public class UserShipView extends ObjectView{

    /**
     * The time between the animation frames
     */
    private static final float FRAME_TIME = 0.2f;

    /**
     * The animation used when the ship is accelerating
     */
    private Animation<TextureRegion> acceleratingAnimation;

    /**
     * The texture used when the ship is not accelerating
     */
    private TextureRegion notAcceleratingRegion;

    /**
     * Time since the space ship started the game. Used
     * to calculate the frame to show in animations.
     */
    private static float stateTime = 0;

    /**
     * Is the space ship accelerating.
     */
    private boolean accelerating;

    /**
     * Constructs a space ship model.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public UserShipView(SpaceAttackGame game) {
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
        acceleratingAnimation = createAcceleratingAnimation(game);
        notAcceleratingRegion = createNotAcceleratingRegion(game);

        return new Sprite(notAcceleratingRegion);
    }

    /**
     * Creates the texture used when the ship is not accelerating
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the texture used when the ship is not accelerating
     */
    private TextureRegion createNotAcceleratingRegion(SpaceAttackGame game) {
        Texture noThrustTexture = game.getAssetManager().get("userShip/userShip.png");
        return new TextureRegion(noThrustTexture, noThrustTexture.getWidth(), noThrustTexture.getHeight());
    }

    /**
     * Creates the animation used when the ship is accelerating
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the animation used when the ship is accelerating
     */
    private Animation<TextureRegion> createAcceleratingAnimation(SpaceAttackGame game) {
        Texture thrustTexture = game.getAssetManager().get("animations/user_Ship_Burst.png");
        TextureRegion[][] thrustRegion = TextureRegion.split(thrustTexture, thrustTexture.getWidth() / 5, thrustTexture.getHeight());

        TextureRegion[] frames = new TextureRegion[5];
        System.arraycopy(thrustRegion[0], 0, frames, 0, 5);

        return new Animation<TextureRegion>(FRAME_TIME, frames);
    }

    /**
     * Updates this ship model. Also save and resets
     * the accelerating flag from the model.
     *
     * @param model the model used to update this view
     */
    @Override
    public void update(GameObject model) {
        super.update(model);

        accelerating = ((Ship)model).isFlying();
        ((Ship)model).setFlying(false);
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

        if (accelerating)
            sprite.setRegion(acceleratingAnimation.getKeyFrame(stateTime, true));
        else
            sprite.setRegion(notAcceleratingRegion);

        sprite.draw(batch);
    }
}

