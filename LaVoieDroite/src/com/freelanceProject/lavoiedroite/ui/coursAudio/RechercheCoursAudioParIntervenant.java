package com.freelanceProject.lavoiedroite.ui.coursAudio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.freelanceProject.lavoiedroite.R;
import com.freelanceProject.lavoiedroite.Adapters.CoursParAuthorAdapter;
import com.freelanceProject.lavoiedroite.beans.CoursParAuthors;
import com.google.gson.Gson;

public class RechercheCoursAudioParIntervenant extends  Activity{

	//String urlAuthors = "http://casaboulot.com/lavoiedroite.net/ws/authors";
	ListView listAuthor;

	Gson gson = new Gson();


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_author);

		listAuthor = (ListView)findViewById(R.id.list_author);

		ImageView back=(ImageView) findViewById(R.id.back);
		TextView ajoutes =(TextView) findViewById(R.id.option_meu_txt3);
		
		
		ajoutes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent menu = new Intent(RechercheCoursAudioParIntervenant.this,
						RechercheCoursAudioParIntervenant.class);
				startActivity(menu);				
			}
		});
		
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent menu = new Intent(RechercheCoursAudioParIntervenant.this,
						com.freelanceProject.lavoiedroite.Menu.class);
				startActivity(menu);				
			}
		});
		

		//InputStream sourceAuthors = WsRequest.retrieveStream(urlAuthors);

		//try {

		//Reader readerAuthors = new InputStreamReader(sourceAuthors);




		//WsResponseAuthor responseAuthor = gson.fromJson(readerAuthors,
		//WsResponseAuthor.class);

		//int i = 0;

		//			for (String[] s : responseAuthor.authors) {
		//
		//				i++;
		//				Log.i("Auteur N°", i + "");
		//				Log.i("Nom", s[0]);
		//				Log.i("Id", s[1]);


		CoursParAuthors authors_data[] = new CoursParAuthors[]
				{
				new CoursParAuthors("","",R.drawable.albumempty,R.drawable.albumempty,R.drawable.albumempty),
				new CoursParAuthors("1","Abbas Abou Layth",R.drawable.player,R.drawable.messageplayer,R.drawable.telecharger),
				new CoursParAuthors("1","Abbas Abou Layth",R.drawable.player,R.drawable.messageplayer,R.drawable.telecharger),
				new CoursParAuthors("2","Abbas Abou Layth",R.drawable.player,R.drawable.messageplayer,R.drawable.telecharger),
				new CoursParAuthors("3","Abbas Abou Layth",R.drawable.player,R.drawable.messageplayer,R.drawable.telecharger),
				new CoursParAuthors("4","Abbas Abou Layth",R.drawable.player,R.drawable.messageplayer,R.drawable.telecharger),
				new CoursParAuthors("5","Abbas Abou Layth",R.drawable.player,R.drawable.messageplayer,R.drawable.telecharger),
				new CoursParAuthors("6","Abbas Abou Layth",R.drawable.player,R.drawable.messageplayer,R.drawable.telecharger),
				new CoursParAuthors("7","Abbas Abou Layth",R.drawable.player,R.drawable.messageplayer,R.drawable.telecharger),
				new CoursParAuthors("8","Abbas Abou Layth",R.drawable.player,R.drawable.messageplayer,R.drawable.telecharger),
				new CoursParAuthors("9","yassine anajar",R.drawable.player,R.drawable.messageplayer,R.drawable.telecharger),
				new CoursParAuthors("","",R.drawable.albumempty,R.drawable.albumempty,R.drawable.albumempty),
				new CoursParAuthors("","",R.drawable.albumempty,R.drawable.albumempty,R.drawable.albumempty)

				};


		CoursParAuthorAdapter adapter = new CoursParAuthorAdapter(this, 
				R.layout.recherche_cours_audio_intervenant, authors_data);
		
		
	
		//}
		//		} catch (Exception e) {
		//			System.out.println("error"+e.getMessage());
		////		}
		View header = (View)getLayoutInflater().inflate(R.layout.recherche_cours_audio_intervenant, null);
		listAuthor.addHeaderView(header);

		listAuthor.setAdapter(adapter);

	}
}



