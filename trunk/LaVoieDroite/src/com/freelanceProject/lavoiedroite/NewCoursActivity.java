package com.freelanceProject.lavoiedroite;

import com.freelanceProject.lavoiedroite.ui.DownloadImage;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class NewCoursActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.decouvrez_cours);
		ImageView iv = ((ImageView) findViewById(R.id.imgCours));
		iv.setTag("http://www.lavoiedroite.com/imagesAppIndroid/planning-cours.jpg");
		new DownloadImage().execute(iv);
	}

}
