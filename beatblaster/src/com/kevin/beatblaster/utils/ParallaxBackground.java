package com.kevin.beatblaster.utils;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ParallaxBackground {

	private List<ParallaxLayer> layers;
	private float width, height;

	public ParallaxBackground(float width, float height) {
		this.layers = new ArrayList<ParallaxLayer>();
		this.width = width;
		this.height = height;
	}

	public void render(float xPosition, float yPosition, SpriteBatch batch) {
		for (ParallaxLayer layer : layers) {
			float layerOffset = (xPosition * layer.getxRatio() % width);
			layer.render(xPosition - width / 2f - layerOffset, yPosition
					/*- height / 2f*/, width, height, batch);
			layer.render(xPosition + width / 2f - layerOffset, yPosition
					/* - height / 2f */, width, height, batch);
		}
	}

	public List<ParallaxLayer> getLayers() {
		return layers;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public void addLayer(ParallaxLayer parallaxLayer) {
		layers.add(parallaxLayer);
	}
}
