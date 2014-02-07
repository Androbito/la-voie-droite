package com.freelanceProject.lavoiedroite;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;

public class NewCoursActivity extends Activity {
	WebView wv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.decouvrez_cours);
		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		wv = ((WebView) findViewById(R.id.webNewCours));
		wv.getSettings().setBuiltInZoomControls(true);
		wv.getSettings().setSupportZoom(true);
		wv.loadUrl("http://www.lavoiedroite.com/imagesAppIndroid/planning-cours.jpg");
	}

}
