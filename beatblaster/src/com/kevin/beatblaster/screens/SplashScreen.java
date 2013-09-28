package com.kevin.beatblaster.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kevin.beatblaster.BeatBlaster;
import com.kevin.beatblaster.services.MusicManager.BeatBlasterMusic;

/**
 * Shows a splash image and moves on to the next screen.
 */
public class SplashScreen extends AbstractScreen {
	private Texture splashTexture;
	private Image splashImage;

	public SplashScreen(final BeatBlaster game) {
		super(game);

		// Use image atlas...
		splashTexture = new Texture(
				Gdx.files.internal("packedimages/splashscreen.png"));
		splashImage = new Image(splashTexture);
		splashImage.setFillParent(true);

		// this is needed for the fade-in effect to work correctly; we're just
		// making the image completely transparent
		splashImage.getColor().a = 0f;

		// configure the fade-in/out effect on the splash image
		splashImage.addAction(sequence(fadeIn(2.f), delay(2.0f),
				fadeOut(2.f), new Action() {
					@Override
					public boolean act(float delta) {
						game.setScreen(new MenuScreen(game));
						return true;
					}
				}));

		// and finally we add the actor to the stage
		stage.addActor(splashImage);
		
		// play menu music (this is crap code...)
		BeatBlasterMusic menuMusic = game.getMusicManager().new BeatBlasterMusic("music/menumusic.mp3");
		game.getMusicManager().play(menuMusic);
	}

	@Override
	public void show() {
		super.show();
		stage.addActor(splashImage);
	}
}
