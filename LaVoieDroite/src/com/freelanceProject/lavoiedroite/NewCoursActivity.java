package com.freelanceProject.lavoiedroite;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class NewCoursActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.decouvrez_cours);
		((WebView) findViewById(R.id.webNewCours))
				.loadUrl("http://www.lavoiedroite.com/imagesAppIndroid/planning-cours.jpg");
	}

}
