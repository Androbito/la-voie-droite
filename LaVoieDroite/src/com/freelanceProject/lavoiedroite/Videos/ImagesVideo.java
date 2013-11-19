package com.freelanceProject.lavoiedroite.Videos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.freelanceProject.lavoiedroite.R;

public class ImagesVideo extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_video);

		ImageView back = (ImageView) findViewById(R.id.back);
		ImageView play_video = (ImageView) findViewById(R.id.play_video);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent menu = new Intent(ImagesVideo.this, CoursVideo.class);
				startActivity(menu);
			}
		});

		play_video.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent menu = new Intent(ImagesVideo.this, CoursVideo.class);
				startActivity(menu);
			}
		});
	}
}
