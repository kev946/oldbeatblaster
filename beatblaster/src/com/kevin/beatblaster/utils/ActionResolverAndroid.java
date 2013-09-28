package com.kevin.beatblaster.utils;

import android.app.Activity;
import android.content.Intent;

// This class implements the native android methods we wish to use.
public class ActionResolverAndroid {
	// private Handler uiThread;
	private Activity activity;

	public ActionResolverAndroid(Activity activity) {
		// uiThread = new Handler();
		this.activity = activity;
	}

	public void openMusicSelectionScreen() {
		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
		activity.startActivityForResult(intent, 1);
	}
}
