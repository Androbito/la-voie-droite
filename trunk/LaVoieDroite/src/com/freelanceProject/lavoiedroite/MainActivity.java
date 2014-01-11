package com.freelanceProject.lavoiedroite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.freelanceProject.lavoiedroite.ui.Carousel;
import com.freelanceProject.lavoiedroite.ui.CarouselAdapter;
import com.freelanceProject.lavoiedroite.ui.CarouselAdapter.OnItemClickListener;
import com.freelanceProject.lavoiedroite.ui.CarouselAdapter.OnItemSelectedListener;

public class MainActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		final TextView txt = (TextView) (findViewById(R.id.textView1));
		Carousel carousel = (Carousel) findViewById(R.id.carousel);
		carousel.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(CarouselAdapter<?> parent, View view,
					int position, long id) {

			}

		});

		carousel.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(CarouselAdapter<?> parent, View view,
					int position, long id) {

				switch (position) {
				case 0:
					txt.setText("Evénements");
					break;
				case 1:
					txt.setText("Découvrez cours");
					break;
				case 2:
					txt.setText("Fatwas");
					break;
				case 3:
					txt.setText("Live Streaming");
					break;
				case 4:
					txt.setText("Prêche Vendredi");
					break;
				case 5:
					txt.setText("Conférence");
					break;
				case 6:
					txt.setText("Cours audio");
					break;
				case 7:
					txt.setText("Vidéos");
					break;
				case 8:
					txt.setText("Articles");
				}

			}

			public void onNothingSelected(CarouselAdapter<?> parent) {
			}

		});

		carousel.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(CarouselAdapter<?> parent, View view,
					int position, long id) {
				if (position == 0) {
					Intent rech = new Intent(view.getContext(),
							EventActivity.class);
					startActivity(rech);
				} else if (position == 1) {
					Intent rech = new Intent(view.getContext(),
							NewCoursActivity.class);
					startActivity(rech);
				} else if (position == 2) {
					Intent fatArt = new Intent(view.getContext(),
							FatawaArticlesActivity.class);
					fatArt.putExtra("type", "fatwa");
					startActivity(fatArt);
				} else if (position == 3) {
					Intent strm = new Intent(view.getContext(),
							StreamingActivity.class);
					startActivity(strm);
				} else if (position == 4) {
					Intent conference = new Intent(view.getContext(),
							PrechesActivity.class);
					conference.putExtra("tid", "10");
					startActivity(conference);
				} else if (position == 5) {
					Intent conference = new Intent(view.getContext(),
							ConferenceActivity.class);
					conference.putExtra("tid", "9");
					startActivity(conference);
				} else if (position == 6) {
					Intent rech = new Intent(view.getContext(),
							CoursActivity.class);
					rech.putExtra("tid", "8");
					startActivity(rech);
				} else if (position == 7) {
					Intent rech = new Intent(view.getContext(),
							VideoActivity.class);
					startActivity(rech);
				}else if (position == 8) {
					Intent fatArt = new Intent(view.getContext(),
							FatawaArticlesActivity.class);
					fatArt.putExtra("type", "article");
					startActivity(fatArt);
				}
			}

		});

	}

}
