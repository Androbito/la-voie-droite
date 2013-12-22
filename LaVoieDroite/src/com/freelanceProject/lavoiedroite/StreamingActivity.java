package com.freelanceProject.lavoiedroite;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class StreamingActivity extends Activity {

	MediaPlayer mediaPlayer = new MediaPlayer();
	AudioManager audioManager;
	ProgressBar volumeControl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player_laylout);
		volumeControl = (ProgressBar) findViewById(R.id.progress);
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		volumeControl.setProgress(100 * (audioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC) - 1) / 15);
		Toast.makeText(getApplicationContext(),
				"" + volumeControl.getProgress(), Toast.LENGTH_SHORT).show();
	}

	public void Stream(View v) {
		try {
			play(new URL("http://37.58.75.163:8060/stream"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void volumeDown(View v) {
		audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
				AudioManager.ADJUST_LOWER, 0);
		// Lower the VOlume Bar on the Screen
		volumeControl.setProgress(100 * (audioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC)
				+ AudioManager.ADJUST_LOWER - 1) / 15);
		Toast.makeText(getApplicationContext(),
				"" + volumeControl.getProgress(), Toast.LENGTH_SHORT).show();
	}

	public void volumeUp(View v) {
		audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
				AudioManager.ADJUST_RAISE, 0);
		// Raise the Volume Bar on the Screen
		volumeControl.setProgress(100 * (audioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC)
				+ AudioManager.ADJUST_RAISE - 1) / 15);
		Toast.makeText(getApplicationContext(),
				"" + volumeControl.getProgress(), Toast.LENGTH_SHORT).show();
	}

	protected void play(URL url) {
		if (!mediaPlayer.isPlaying()) {
			try {
				mediaPlayer.setDataSource(url.toString());
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				mediaPlayer.prepare();
				mediaPlayer.start();
				((ImageView) findViewById(R.id.imageView1))
						.setImageResource(R.drawable.pause_strm);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			mediaPlayer.stop();
			((ImageView) findViewById(R.id.imageView1))
					.setImageResource(R.drawable.play_strm);
		}
	}
}
