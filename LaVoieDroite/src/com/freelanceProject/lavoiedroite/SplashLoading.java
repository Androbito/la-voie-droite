package com.freelanceProject.lavoiedroite;

import java.io.File;

import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ProgressBar;

public class SplashLoading extends AsyncTask<String, Integer, Integer> {

	public interface LoadingTaskFinishedListener {
		void onTaskFinished();
	}

	// This is the progress bar you want to update while the task is in progress
	private final ProgressBar progressBar;

	// This is the listener that will be told when this task is finished
	private final LoadingTaskFinishedListener finishedListener;

	/**
	 * A Loading task that will load some resources that are necessary for the
	 * app to start
	 * 
	 * @param progressBar
	 *            - the progress bar you want to update while the task is in
	 *            progress
	 * @param finishedListener
	 *            - the listener that will be told when this task is finished
	 */
	public SplashLoading(ProgressBar progressBar,
			LoadingTaskFinishedListener finishedListener) {
		this.progressBar = progressBar;
		this.finishedListener = finishedListener;
	}

	@Override
	protected Integer doInBackground(String... params) {
		if (resourcesDontAlreadyExist()) {
			downloadResources();
		}
		// Perhaps you want to return something to your post execute
		return 1234;
	}

	private boolean resourcesDontAlreadyExist() {

		return true;
	}

	private void downloadResources() {
		File directory = new File(Environment.getExternalStorageDirectory()
				.toString() + "/LaVoieDroite_mp3");
		if (!directory.exists())
			directory.mkdirs();
		int count = 10;
		for (int i = 0; i < count; i++) {

			// Update the progress bar after every step
			int progress = (int) ((i / (float) count) * 100);
			publishProgress(progress);

			// Do some long loading things
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ignore) {
			}
		}
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		progressBar.setProgress(values[0]);
		// This is ran on the UI thread so it is ok to update our progress bar (
		// a UI view ) here

	}

	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		finishedListener.onTaskFinished(); // Tell whoever was listening we have
											// finished
	}
}