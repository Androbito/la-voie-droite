package com.freelanceProject.lavoiedroite.ui.coursAudio;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.freelanceProject.lavoiedroite.R;
import com.freelanceProject.lavoiedroite.Adapters.CoursParThemeAdapter;
import com.freelanceProject.lavoiedroite.beans.CoursParTheme;
import com.freelanceProject.lavoiedroite.beans.WsResponseTheme;
import com.freelanceProject.lavoiedroite.ws.WsRequest;
import com.google.gson.Gson;

public class RechercheCoursAudioParTheme extends Activity {

	ProgressDialog dialog;
	String urlthmes = "http://www.lavoiedroite.com/ws/themes";
	ListView listTheme;

	Gson gson = new Gson();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_theme);

		ImageView back = (ImageView) findViewById(R.id.back);
		TextView intervenant = (TextView) findViewById(R.id.option_meu_txt2);

		intervenant.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent menu = new Intent(RechercheCoursAudioParTheme.this,
						RechercheCoursAudioParIntervenant.class);
				startActivity(menu);
			}
		});

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent menu = new Intent(RechercheCoursAudioParTheme.this,
						com.freelanceProject.lavoiedroite.Menu.class);
				startActivity(menu);
			}
		});

		listTheme = (ListView) findViewById(R.id.list_theme);
		InputStream sourceThemes = WsRequest.retrieveStream(urlthmes);

		try {

			Reader readerThemes = new InputStreamReader(sourceThemes);

			WsResponseTheme responseTheme = gson.fromJson(readerThemes,
					WsResponseTheme.class);

			int i = 0;
			for (String[] s : responseTheme.themes.comportements) {
				i++;
				Log.i("Cours N°", i + "");
				Log.i("id", s[0]);
				Log.i("titre", s[1]);
				Log.i("contenu", s[2]);
			}

			CoursParTheme cours_data[] = new CoursParTheme[] {
					new CoursParTheme(R.drawable.albumempty, "",
							R.drawable.albumempty),
					new CoursParTheme(R.drawable.coran, "Coran",
							R.drawable.group_down),
					new CoursParTheme(R.drawable.hadith, "Hadiyths",
							R.drawable.group_down),
					new CoursParTheme(R.drawable.adoration, "Adoration",
							R.drawable.group_down),
					new CoursParTheme(R.drawable.biographie, "Biographie",
							R.drawable.group_down),
					new CoursParTheme(R.drawable.croyance, "Croyance",
							R.drawable.group_down),
					new CoursParTheme(R.drawable.compotement, "Comportement",
							R.drawable.group_down),
					new CoursParTheme(R.drawable.albumempty, "",
							R.drawable.albumempty)

			};

			CoursParThemeAdapter adapter = new CoursParThemeAdapter(this,
					R.layout.recherche_cours_audio_theme, cours_data);

			View header = (View) getLayoutInflater().inflate(
					R.layout.recherche_cours_audio_theme, null);
			listTheme.addHeaderView(header);

			listTheme.setAdapter(adapter);

		} catch (Exception e) {
			System.out.println("error" + e.getMessage());
		}

	}

}