package com.freelanceProject.lavoiedroite;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class StreamingActivity extends Activity {

	MediaPlayer mediaPlayer = new MediaPlayer();
	AudioManager audioManager;
	ProgressBar volumeControl;
	ProgressDialog mProgressDialog;
	WakeLock wl;
	PowerManager pm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.streaming);
		pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "MyTag");
		ImageView back = (ImageView) findViewById(R.id.back);
		Log.i("url",getIntent().getStringExtra("url"));

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		volumeControl = (ProgressBar) findViewById(R.id.progress);
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		volumeControl.setProgress(100 * (audioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC) - 1) / 15);
	}

	public void Stream(View v) {
		if (!mediaPlayer.isPlaying()) {
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			mProgressDialog.setMessage("Stream loading...");
			mProgressDialog.show();
			new Thread((new Runnable() {
				Message msg = null;

				public void run() {
					try {
						play(new URL(getIntent().getStringExtra("url")));
						msg = mHandler.obtainMessage(1);
						// sends the message to our handler
						mHandler.sendMessage(msg);
						mediaPlayer.start();
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			})).start();
		} else if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
			mediaPlayer.reset();
			((ImageView) findViewById(R.id.imageView1))
					.setImageResource(R.drawable.play_strm);
		}
	}

	public void volumeDown(View v) {
		audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
				AudioManager.ADJUST_LOWER, 0);
		// Lower the VOlume Bar on the Screen
		volumeControl.setProgress(100 * (audioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC)
				+ AudioManager.ADJUST_LOWER - 1) / 15);
	}

	public void volumeUp(View v) {
		audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
				AudioManager.ADJUST_RAISE, 0);
		// Raise the Volume Bar on the Screen
		volumeControl.setProgress(100 * (audioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC)
				+ AudioManager.ADJUST_RAISE - 1) / 15);
	}

	protected void play(URL url) {
		try {
			mediaPlayer.setDataSource(url.toString());
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.prepare();
			mediaPlayer.start();

			wl.acquire();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (wl.isHeld())
			wl.release();
		mediaPlayer.stop();
		mediaPlayer.reset();
	}

	final Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				mProgressDialog.dismiss();
				((ImageView) findViewById(R.id.imageView1))
						.setImageResource(R.drawable.pause_strm);
				break;
			}
		}
	};
}
