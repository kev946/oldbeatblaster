package com.kevin.beatblaster.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.kevin.beatblaster.BeatBlaster;

public class MenuScreen extends AbstractScreen {
	public MenuScreen(BeatBlaster game) {
		super(game);
	}

	@Override
	public void show() {
		super.show();
		Table table = getTable();

		// register the button "start game"
		TextButton startGameButton = new TextButton("Start game", getSkin());
		startGameButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new StartGameScreen(game));
			}
		});
		table.add(startGameButton).size(300, 60).uniform().spaceBottom(10);
		table.row();
	}
}
