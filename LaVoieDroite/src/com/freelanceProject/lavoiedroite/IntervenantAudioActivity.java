package com.freelanceProject.lavoiedroite;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.freelanceProject.lavoiedroite.Adapters.AuthorsAdapter;
import com.freelanceProject.lavoiedroite.beans.CoursAudio;
import com.freelanceProject.lavoiedroite.beans.WsResponseTheme;
import com.freelanceProject.lavoiedroite.ws.URLs;
import com.freelanceProject.lavoiedroite.ws.WSHelper;
import com.freelanceProject.lavoiedroite.ws.WSHelperListener;

public class IntervenantAudioActivity extends Activity implements
		WSHelperListener {
	ConnectivityManager cManager;
	ListView lstViewAuteurs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.audiobytheme);
		lstViewAuteurs = (ListView) findViewById(R.id.listViewAuteurs);
		cManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		WSHelper.getInstance().addWSHelperListener(this);
		WSHelper.getInstance().getAuteurs(cManager, this);
	}

	@Override
	public void onAuthorsLoaded(final List<String[]> auteurs) {
		Log.i("Taille de la liste des auteurs", "" + auteurs.size());
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				lstViewAuteurs.setAdapter(new AuthorsAdapter(
						IntervenantAudioActivity.this, auteurs));
				lstViewAuteurs
						.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> arg0,
									View arg1, int position, long arg3) {
								Intent goToAudioByIntervenant = new Intent(
										getApplicationContext(),
										AudioByFilterActivity.class);
								goToAudioByIntervenant.putExtra(
										"url",
										URLs.intervenants
												+ auteurs.get(position)[1]
												+ "&tid=8&page=0&npage=25");
								startActivity(goToAudioByIntervenant);

							}
						});
			}
		});
	}

	@Override
	public void onErrorLoadingAuthors(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAudioListLoaded(final List<CoursAudio> Cours) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorLoadingCours(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onThemesLoaded(WsResponseTheme wsResponseTheme) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorLoadingThemes(String string) {
		// TODO Auto-generated method stub

	}

}
