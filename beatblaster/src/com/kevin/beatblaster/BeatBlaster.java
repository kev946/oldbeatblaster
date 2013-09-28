package com.kevin.beatblaster;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.kevin.beatblaster.screens.SplashScreen;
import com.kevin.beatblaster.services.MusicManager;
import com.kevin.beatblaster.utils.ActionResolverAndroid;

public class BeatBlaster extends Game {
	// Constant useful for logging
	public static final String LOG = BeatBlaster.class.getSimpleName();

	// Whether we're in development mode
	public static final boolean DEV_MODE = false;

	// Libgdx helper class that can log fps
	private FPSLogger fpsLogger;

	// Allows for access of native android ui elements
	private ActionResolverAndroid actionResolver;

	// Services

	private MusicManager musicManager;

	// Services' getters

	public MusicManager getMusicManager() {
		return musicManager;
	}
	
	public ActionResolverAndroid getActionResolver() {
		return actionResolver;
	}

	public BeatBlaster(ActionResolverAndroid actionResolver) {
		this.actionResolver = actionResolver;
	}

	@Override
	public void create() {
		Gdx.app.log(BeatBlaster.LOG, "Creating game on " + Gdx.app.getType());

		musicManager = new MusicManager();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		super.render();

		// Output the current fps
		if (DEV_MODE) {
			fpsLogger.log();
		}
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		Gdx.app.log(BeatBlaster.LOG, "Resizing game to: " + width + " x "
				+ height);

		// Show the splash screen when the game is resized for the first time;
		// This approach avoids calling the screen's resize method repeatedly
		if (getScreen() == null) {
			if (DEV_MODE) {
				// setScreen(new LevelScreen(this, 0));
			} else {
				setScreen(new SplashScreen(this));
			}
		}
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
