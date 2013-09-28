package com.kevin.beatblaster.screens;

import com.kevin.beatblaster.BeatBlaster;

public class StartGameScreen extends AbstractScreen {
	// The path to the selected music
	private static String selectedMusicPath = null;

	public StartGameScreen(BeatBlaster game) {
		super(game);
		actionResolver.openMusicSelectionScreen();
	}

	public static void setSelectedMusicPath(String selectedMusicPath) {
		StartGameScreen.selectedMusicPath = selectedMusicPath;
	}

	public static String getSelectedMusicPath() {
		return selectedMusicPath;
	}

	@Override
	public void resize(int width, int height) {
		// Don't start game until song is selected (find better way to do this)
		if (selectedMusicPath != null) {
			game.setScreen(new GameScreen(game));
		}
	}
}
