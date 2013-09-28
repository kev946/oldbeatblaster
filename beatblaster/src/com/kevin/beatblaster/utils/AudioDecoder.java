package com.kevin.beatblaster.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.io.Mpg123Decoder;
import com.badlogic.gdx.files.FileHandle;

// so far only supports mp3 decoding
public class AudioDecoder {
	// The device to stream pcm data to
	private AudioDevice audioDevice;
	private Mpg123Decoder mpg123Decoder;
	private boolean playing = false;
	private short[] samples = new short[2048];
	private Thread playbackThread;
	
	public static enum SUPPORTED_FORMATS {
		MP3
	}
	
	public AudioDecoder(String musicPath, SUPPORTED_FORMATS format) {
		if(format == SUPPORTED_FORMATS.MP3) {
			//mpg123Decoder = new Mpg123Decoder(Gdx.files.external(musicPath)); 
			FileHandle file = new FileHandle("data/marina.mp3");
			mpg123Decoder = new Mpg123Decoder(file);
			//audioDevice = Gdx.audio.newAudioDevice(mpg123Decoder.getRate(), mpg123Decoder.getChannels() == 1 ? true : false);
			audioDevice = Gdx.audio.newAudioDevice(44100, false);
		}
	}
	
	public void play() {
		//playbackThread = new Thread(new Runnable() {
			//@Override
			//public void run() {
				int readSamples = 0;
				// problem here...
				playing = true;
				while(playing && (readSamples = mpg123Decoder.readSamples(samples, 0, samples.length)) > 0)
					audioDevice.writeSamples(samples, 0, readSamples);
			//}
		//});
		//playbackThread.setDaemon(true);
		//playing = true;
		//playbackThread.start();
	}
	
	public void stop() {
		playing = false;
	}
}
