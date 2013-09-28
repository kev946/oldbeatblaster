package com.kevin.beatblaster;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.kevin.beatblaster.screens.StartGameScreen;
import com.kevin.beatblaster.utils.ActionResolverAndroid;

public class MainActivity extends AndroidApplication {
	private ActionResolverAndroid actionResolver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useGL20 = false;

		actionResolver = new ActionResolverAndroid(this);

		initialize(new BeatBlaster(actionResolver), cfg);
	}

	// Returns the ID of the selected song
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check which request it is that we're responding to
		if (requestCode == 1) {
			// Make sure the request was successful
			if (resultCode == RESULT_OK) {
				String selectedMusicPath = getPathFromUri(data.getData());
				// Ghetto way of doing this... (static member)
				StartGameScreen.setSelectedMusicPath(selectedMusicPath);		
			}
		}
	}

	// @return the path to the selected music  
	private String getPathFromUri(Uri contentUri) {
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = getContentResolver().query(contentUri, proj, null,
				null, null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}
}