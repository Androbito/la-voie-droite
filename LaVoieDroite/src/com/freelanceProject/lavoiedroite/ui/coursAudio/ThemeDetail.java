package com.freelanceProject.lavoiedroite.ui.coursAudio;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import com.freelanceProject.lavoiedroite.R;
import com.freelanceProject.lavoiedroite.Adapters.CoursParThemeAdapter;
import com.freelanceProject.lavoiedroite.beans.CoursParTheme;
import com.freelanceProject.lavoiedroite.beans.WsResponseTheme;
import com.freelanceProject.lavoiedroite.connexions.WsRequest;
import com.google.gson.Gson;

public class ThemeDetail extends Activity{


	String urlthmes = "http://lavoiedroite.com/pre_prod/ws/themes";
	ListView listTheme;

	Gson gson = new Gson();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_theme_detail);

		listTheme = (ListView)findViewById(R.id.list_theme);
		InputStream sourceThemes = WsRequest.retrieveStream(urlthmes);

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


			CoursParTheme cours_data[] = new CoursParTheme[]
					{
//					new CoursParTheme(R.drawable.albumempty,"Les Bons Comportements"),
//				    new CoursParTheme(R.drawable.albumempty,"Les Mauvais Comportements"), 
//					new	CoursParTheme(R.drawable.albumempty,"Les Comportements avec autrui")

					};

			CoursParThemeAdapter adapter = new CoursParThemeAdapter(this, 
					R.layout.recherche_cours_audio_theme, cours_data);
			listTheme.setAdapter(adapter);
		}
		//
		//		View header = (View)getLayoutInflater().inflate(R.layout.recherche_cours_audio_theme, null);
		//		listTheme.addHeaderView(header);



	}



}