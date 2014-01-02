package com.freelanceProject.lavoiedroite.ui;

import java.io.IOException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class DownloadImage extends AsyncTask<ZoomImageView, Void, Bitmap> {

	ProgressBar p = null;
	ImageView vue = null;

	public DownloadImage(ProgressBar pbr) {
		this.p = pbr;

	}

	public DownloadImage() {

	}

	@Override
	protected void onPostExecute(Bitmap result) {
		if (result != null) {
			vue.setImageBitmap(result);
		}
		if (p != null) {
			p.setVisibility(View.GONE);
		}
	}

	private Bitmap download_Image(String url) {
		// ---------------------------------------------------
		Bitmap bm = null;
		try {
			URL aURL = new URL(url);
			bm = BitmapFactory.decodeStream(aURL.openConnection()
					.getInputStream());
		} catch (IOException e) {
			Log.e("Hub", "Error getting the image from server : "
					+ e.getMessage().toString());
		}
		return bm;
		// ---------------------------------------------------
	}

	@Override
	protected Bitmap doInBackground(ZoomImageView... params) {
		this.vue = (ImageView) params[0];

		return download_Image((String) vue.getTag());
	}

	
//	  protected void doInBackground(ProgressBar... params) { //this.imageView =
//	  (ImageButton)params[0]; this.p = (ProgressBar)params[0]; return
//	  download_Image((String)imageView.getTag()); }
	 

	protected void onPreExecute() {
		super.onPreExecute();

	}

	protected void onProgressUpdate(String... progress) {

	}
}
