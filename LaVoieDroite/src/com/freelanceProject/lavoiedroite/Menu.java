package com.freelanceProject.lavoiedroite;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.freelanceProject.lavoiedroite.Videos.ImagesVideo;
import com.freelanceProject.lavoiedroite.event.MainActivity;
import com.freelanceProject.lavoiedroite.ui.Carousel;
import com.freelanceProject.lavoiedroite.ui.CarouselAdapter;
import com.freelanceProject.lavoiedroite.ui.CarouselAdapter.OnItemClickListener;
import com.freelanceProject.lavoiedroite.ui.CarouselAdapter.OnItemSelectedListener;

public class Menu extends  Activity{



	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		final TextView txt = (TextView)(findViewById(R.id.textView1));
		Carousel carousel = (Carousel)findViewById(R.id.carousel);
		carousel.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(CarouselAdapter<?> parent, View view,
					int position, long id) {	

			}

		});

		carousel.setOnItemSelectedListener(new OnItemSelectedListener(){

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

				if(position==6){
					ProgressDialog dialog = ProgressDialog.show(Menu.this, "", "Chargement...",
							true);
					Intent rech = new Intent(view.getContext(),
							RechercheCoursAudioParTheme.class);
					startActivity(rech);
					dialog.setCancelable(true);
				}
				else if(position==0){
					ProgressDialog dialog = ProgressDialog.show(Menu.this, "", "Chargement...",
							true);
					Intent rech = new Intent(view.getContext(),
							MainActivity.class);
					startActivity(rech);
					dialog.setCancelable(true);
				}
				else if(position==7){
					ProgressDialog dialog = ProgressDialog.show(Menu.this, "", "Chargement...",
							true);
					Intent rech = new Intent(view.getContext(),
							ImagesVideo.class);
					startActivity(rech);
					dialog.setCancelable(true);
				}
			}

		});



	}

}

