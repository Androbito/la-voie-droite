package com.freelanceProject.lavoiedroite;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.freelanceProject.lavoiedroite.Videos.ImagesVideo;
import com.freelanceProject.lavoiedroite.event.MainActivity;
import com.freelanceProject.lavoiedroite.ui.Carousel;
import com.freelanceProject.lavoiedroite.ui.CarouselAdapter;
import com.freelanceProject.lavoiedroite.ui.CarouselAdapter.OnItemClickListener;
import com.freelanceProject.lavoiedroite.ui.CarouselAdapter.OnItemSelectedListener;

public class Menu extends Activity {

	MediaPlayer mediaPlayer = new MediaPlayer();

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
					txt.setText("Fatawas");
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

				if (position == 6) {
					ProgressDialog dialog = ProgressDialog.show(Menu.this, "",
							"Chargement...", true);
					Intent rech = new Intent(view.getContext(),
							CoursAudioActivity.class);
					startActivity(rech);
					dialog.setCancelable(true);
				} else if (position == 3) {
					try {
						play(new URL("http://37.58.75.163:8060/stream"));
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (position == 0) {
					ProgressDialog dialog = ProgressDialog.show(Menu.this, "",
							"Chargement...", true);
					Intent rech = new Intent(view.getContext(),
							MainActivity.class);
					startActivity(rech);
					dialog.setCancelable(true);
				} else if (position == 7) {
					ProgressDialog dialog = ProgressDialog.show(Menu.this, "",
							"Chargement...", true);
					Intent rech = new Intent(view.getContext(),
							ImagesVideo.class);
					startActivity(rech);
					dialog.setCancelable(true);
				}
			}

		});

	}

	protected void play(URL url) {
		// TODO Auto-generated method stub
		try {
			mediaPlayer.setDataSource(url.toString());
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
