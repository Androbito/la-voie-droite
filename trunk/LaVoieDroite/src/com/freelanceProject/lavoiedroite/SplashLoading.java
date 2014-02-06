package com.freelanceProject.lavoiedroite;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.util.ByteArrayBuffer;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
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
		downloadResources(
				"http://manager.radioking.fr:2199/tunein/liveaima.ram",
				"liveaima.ram", 50);
		downloadResources(
				"http://manager.radioking.fr:2199/tunein/wwwlavoi.ram",
				"wwwlavoi.ram", 100);
		// Perhaps you want to return something to your post execute
		return 1234;
	}

	private void downloadResources(String urlStr, String fileExt, int prog) {
		File directory = new File(Environment.getExternalStorageDirectory()
				.toString() + "/LaVoieDroite_mp3");
		if (!directory.exists())
			directory.mkdirs();
		try {
			InputStream input = null;
			OutputStream output = null;
			HttpURLConnection connection = null;
			try {
				URL url = new URL(urlStr);
				connection = (HttpURLConnection) url.openConnection();
				connection.connect();

				// expect HTTP 200 OK, so we don't mistakenly save error report
				// instead of the file
				if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
					Log.i("Server returned HTTP ", connection.getResponseCode()
							+ " " + connection.getResponseMessage());

				// this will be useful to display download percentage
				// might be -1: server did not report the length
				int fileLength = connection.getContentLength();

				// download the file
				input = connection.getInputStream();
				output = new FileOutputStream(directory.getAbsolutePath() + "/"
						+ fileExt);
				BufferedInputStream bis = new BufferedInputStream(input);
				ByteArrayBuffer baf = new ByteArrayBuffer(50);
				long total = 0;
				while ((total = bis.read()) != -1) {
					// allow canceling with back button
					if (isCancelled())
						Log.i("Cancelled ", "allow canceling with back button");
					baf.append((byte) total);
					// publishing the progress....
					// if (fileLength > 0) // only if total length is known
					Log.i("FileLength", "" + fileLength);
				}
				output.write(baf.toByteArray());
				publishProgress(prog);

			} catch (Exception e) {
				Log.e("Exception", e.toString());
			} finally {
				try {
					if (output != null)
						output.close();
					if (input != null)
						input.close();
				} catch (IOException ignored) {
				}

				if (connection != null)
					connection.disconnect();
			}
		} finally {
		}
		return;
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