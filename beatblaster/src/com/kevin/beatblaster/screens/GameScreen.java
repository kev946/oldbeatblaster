package com.kevin.beatblaster.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.kevin.beatblaster.BeatBlaster;
import com.kevin.beatblaster.services.MusicManager;
import com.kevin.beatblaster.services.MusicManager.BeatBlasterMusic;
import com.kevin.beatblaster.utils.AudioDecoder;
import com.kevin.beatblaster.utils.ParallaxBackground;
import com.kevin.beatblaster.utils.ParallaxLayer;

public class GameScreen extends AbstractScreen {
	ParallaxBackground parallaxBackground;
	Texture shipImage;
	Texture farBackgroundTexture;
	Texture midBackgroundTexture;
	TextureRegion farBackground;
	TextureRegion midBackground;
	Rectangle ship;
	OrthographicCamera camera;
	SpriteBatch batch;
	BeatBlasterMusic music;
	AudioDecoder audioDecoder;

	public GameScreen(BeatBlaster game) {
		super(game);
		MusicManager musicManager = game.getMusicManager();
		musicManager.stop();
		
		// decode music and play
		music = game.getMusicManager().new BeatBlasterMusic(StartGameScreen.getSelectedMusicPath());
		audioDecoder = new AudioDecoder(music.getFileName(), AudioDecoder.SUPPORTED_FORMATS.MP3);
		audioDecoder.play();
		
		
		shipImage = new Texture(
				Gdx.files.internal("packedimages/spaceship.png"));
		ship = new Rectangle();
		ship.x = 84; // center the bucket horizontally
		ship.y = 250; // bottom left corner of the bucket is 20 pixels above
						// the bottom screen edge
		ship.width = 64;
		ship.height = 64;

		// set up parallax background
		parallaxBackground = new ParallaxBackground(800, 480);
		shipImage = new Texture(
				Gdx.files.internal("packedimages/spaceship.png"));
		farBackgroundTexture = new Texture(
				Gdx.files.internal("packedimages/farbackground.png"));
		midBackgroundTexture = new Texture(
				Gdx.files.internal("packedimages/midbackground.png"));
		farBackground = new TextureRegion(farBackgroundTexture);
		midBackground = new TextureRegion(midBackgroundTexture);
		parallaxBackground.addLayer(new ParallaxLayer(farBackground, 0.5f));
		parallaxBackground.addLayer(new ParallaxLayer(midBackground, 1.0f));
		
		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// movement...
		camera.position.x += 5f;
		ship.y -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isTouched()) {
			ship.y += 450f * Gdx.graphics.getDeltaTime();
		}
		if (ship.y < 50)
			ship.y = 50;
		if(ship.y > 430)
			ship.y = 430;
		ship.x += 5f;

		// tell the camera to update its matrices.
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		batch.setProjectionMatrix(camera.combined);

		// send shit to the GPU
		batch.begin();
		parallaxBackground.render(camera.position.x, 0f, batch);
		batch.draw(shipImage, ship.x, ship.y);
		batch.end();
	}
}
