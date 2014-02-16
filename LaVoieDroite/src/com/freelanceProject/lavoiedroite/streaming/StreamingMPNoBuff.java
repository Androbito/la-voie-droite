package com.freelanceProject.lavoiedroite.streaming;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.freelanceProject.lavoiedroite.R;

/**
 * MediaPlayer does not yet support streaming from external URLs so this class
 * provides a pseudo-streaming function by downloading the content incrementally
 * & playing as soon as we get enough audio in our temporary storage.
 */
public class StreamingMPNoBuff {

	private static final int INTIAL_KB_BUFFER = 96 * 10 / 8;// assume
															// 96kbps*10secs/8bits
															// per byte
	private float progress = 0;
	private TextView textStreamed;

	private ImageView playButton;

	private SeekBar progressBar;

	// Track for display by progressBar
	private long mediaLengthInKb, mediaLengthInSeconds;
	private int totalKbRead = 0;

	// Create Handler to call View updates on the main UI thread.
	public Handler handler = new Handler();

	private MediaPlayer mediaPlayer;

	private File downloadingMediaFile;

	private boolean isInterrupted;

	private Context context;

	private int counter = 0;
	protected float loadProgress;

	public StreamingMPNoBuff(Context context, TextView textStreamed,
			ImageView playButton, SeekBar progressBar) {
		this.context = context;
		this.textStreamed = textStreamed;
		this.playButton = playButton;
		this.progressBar = progressBar;
	}

	/**
	 * Progressivly download the media to a temporary location and update the
	 * MediaPlayer as new content becomes available.
	 */
	public void startStreaming(final String mediaUrl, long mediaLengthInKb,
			long mediaLengthInSeconds) throws IOException {

		this.mediaLengthInKb = mediaLengthInKb;
		this.mediaLengthInSeconds = mediaLengthInSeconds;

		Runnable r = new Runnable() {
			public void run() {
				try {
					downloadAudioIncrement(mediaUrl);
				} catch (IOException e) {
					Log.e(getClass().getName(),
							"Unable to initialize the MediaPlayer for fileUrl="
									+ mediaUrl, e);
					return;
				}
			}
		};
		new Thread(r).start();
	}

	/**
	 * Download the url stream to a temporary location and then call the
	 * setDataSource for that local file
	 */
	public void downloadAudioIncrement(String mediaUrl) throws IOException {
		testMediaBuffer(mediaUrl);
	}

	private boolean validateNotInterrupted() {
		if (isInterrupted) {
			if (mediaPlayer != null) {
				mediaPlayer.pause();
				// mediaPlayer.release();
			}
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Test whether we need to transfer buffered data to the MediaPlayer.
	 * Interacting with MediaPlayer on non-main UI thread can causes crashes to
	 * so perform this using a Handler.
	 */
	private void testMediaBuffer(final String url) {
		Runnable updater = new Runnable() {
			public void run() {
				if (mediaPlayer == null) {
							startMediaPlayer(url);
				}
			}
		};
		handler.post(updater);
	}

	private void startMediaPlayer(String url) {
		try {
			// We double buffer the data to avoid potential read/write errors
			// that could happen if the
			// download thread attempted to write at the same time the
			// MediaPlayer was trying to read.
			// For example, we can't guarantee that the MediaPlayer won't open a
			// file for playing and leave it locked while
			// the media is playing. This would permanently deadlock the file
			// download. To avoid such a deadloack,
			// we move the currently loaded data to a temporary buffer file that
			// we start playing while the remaining
			// data downloads.

			mediaPlayer = createMediaPlayerfromUrl(url);
			Log.i(getClass().getName(), "File duration: "
					+ convertDuration(mediaPlayer.getDuration() / 1000));
			// We have pre-loaded enough content and started the MediaPlayer so
			// update the buttons & progress meters.
			mediaPlayer.start();
			startPlayProgressUpdater();
			playButton.setEnabled(true);
			playButton.setImageResource(R.drawable.pause);
		} catch (IOException e) {
			Log.e(getClass().getName(), "Error initializing the MediaPlayer.",
					e);
			return;
		}
	}

	private String convertDuration(long sec) {
		int hours = (int) sec / 3600;
		int minutes = ((int) sec % 3600) / 60;
		int seconds = (int) sec % 60;
		return (hours + ":" + minutes + ":" + seconds);
	}

	// private MediaPlayer createMediaPlayer(File mediaFile) throws IOException
	// {
	// MediaPlayer mPlayer = new MediaPlayer();
	// mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
	// public boolean onError(MediaPlayer mp, int what, int extra) {
	// Log.e(getClass().getName(), "Error in MediaPlayer: (" + what
	// + ") with extra (" + extra + ")");
	// return false;
	// }
	// });
	//
	// // It appears that for security/permission reasons, it is better to pass
	// // a FileDescriptor rather than a direct path to the File.
	// // Also I have seen errors such as "PVMFErrNotSupported" and
	// // "Prepare failed.: status=0x1" if a file path String is passed to
	// // setDataSource(). So unless otherwise noted, we use a FileDescriptor
	// // here.
	// FileInputStream fis = new FileInputStream(mediaFile);
	// mPlayer.setDataSource(fis.getFD());
	// mPlayer.prepare();
	// return mPlayer;
	// }
	private MediaPlayer createMediaPlayerfromUrl(String url) throws IOException {
		MediaPlayer mPlayer = new MediaPlayer();
		mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
			public boolean onError(MediaPlayer mp, int what, int extra) {
				Log.e(getClass().getName(), "Error in MediaPlayer: (" + what
						+ ") with extra (" + extra + ")");
				return false;
			}
		});

		// It appears that for security/permission reasons, it is better to pass
		// a FileDescriptor rather than a direct path to the File.
		// Also I have seen errors such as "PVMFErrNotSupported" and
		// "Prepare failed.: status=0x1" if a file path String is passed to
		// setDataSource(). So unless otherwise noted, we use a FileDescriptor
		// here.
		mPlayer.setDataSource(url);
		mPlayer.prepare();
		return mPlayer;
	}

	/**
	 * Transfer buffered data to the MediaPlayer. NOTE: Interacting with a
	 * MediaPlayer on a non-main UI thread can cause thread-lock and crashes so
	 * this method should always be called using a Handler.
	 */

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	private void seekChange(View v) {
		if (mediaPlayer.isPlaying()) {
			SeekBar sb = (SeekBar) v;
			mediaPlayer.seekTo(sb.getProgress()
					* ((int) (mediaLengthInSeconds * 10)));
		}
	}

	public void startPlayProgressUpdater() {
		mediaLengthInSeconds = mediaPlayer.getDuration() / 1000;
		float mediaPositionSeconds = mediaPlayer.getCurrentPosition() / 1000;
		float progress = (mediaPositionSeconds / mediaLengthInSeconds);
		progressBar.setProgress((int) (progress * 100));
		progressBar.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				seekChange(v);
				return false;
			}
		});
		textStreamed
				.setText(convertDuration(mediaPlayer.getCurrentPosition() / 1000)
						+ "/" + convertDuration(mediaLengthInSeconds));
		if (mediaPlayer.isPlaying()) {
			Runnable notification = new Runnable() {
				public void run() {
					startPlayProgressUpdater();
				}
			};
			handler.postDelayed(notification, 1000);
		}
	}

	public void interrupt() {
		playButton.setEnabled(false);
		isInterrupted = true;
		validateNotInterrupted();
	}

	/**
	 * Move the file in oldLocation to newLocation.
	 */
}
