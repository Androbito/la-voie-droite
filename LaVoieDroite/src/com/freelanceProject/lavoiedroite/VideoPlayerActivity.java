package com.freelanceProject.lavoiedroite;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class VideoPlayerActivity extends Activity {
	String videoID = "";
	private ImageView back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video);
		String url = getIntent().getStringExtra("url");
		videoID = url.substring(url.indexOf("=") + 1);
		Log.i("videoId", videoID);
		back = (ImageView) findViewById(R.id.back);
		((TextView) findViewById(R.id.textVideoTitre)).setText(getIntent()
				.getStringExtra("name"));
		((TextView) findViewById(R.id.textVideoDetails)).setText(getIntent()
				.getStringExtra("titre"));
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
			}
		});
		new Thread(new Runnable() {
			@Override
			public void run() {
				final WebView myWebView = (WebView) findViewById(R.id.webVideo);
				myWebView.getSettings().setJavaScriptEnabled(true);
				myWebView.getSettings().setPluginState(PluginState.ON);
				myWebView.loadUrl("http://www.youtube.com/embed/" + videoID
						+ "?autoplay=1&vq=small");
				myWebView.setWebChromeClient(new WebChromeClient());

			}
		}).start();
	}
}
