package com.kevin.beatblaster.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ParallaxLayer {

	private TextureRegion region;
	private float xRatio;

	public ParallaxLayer(TextureRegion region, float xRatio) {
		super();
		this.region = region;
		this.xRatio = xRatio;
	}

	public TextureRegion getRegion() {
		return region;
	}

	public float getxRatio() {
		return xRatio;
	}

	public void render(float xPosition, float yPosition, float width,
			float height, SpriteBatch batch) {
		batch.draw(region, xPosition, yPosition, width, height);
	}
}