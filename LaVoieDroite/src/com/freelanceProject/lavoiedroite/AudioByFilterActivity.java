package com.freelanceProject.lavoiedroite;

import java.util.List;

import com.freelanceProject.lavoiedroite.Adapters.LastAddAdapter;
import com.freelanceProject.lavoiedroite.beans.CoursAudio;
import com.freelanceProject.lavoiedroite.beans.WsResponseTheme;
import com.freelanceProject.lavoiedroite.ws.URLs;
import com.freelanceProject.lavoiedroite.ws.WSHelper;
import com.freelanceProject.lavoiedroite.ws.WSHelperListener;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class AudioByFilterActivity extends Activity implements
		WSHelperListener {
	ListView lstViewCoursByIntervenant;
	ConnectivityManager cManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.audiobyintervenant);
		lstViewCoursByIntervenant = (ListView) findViewById(R.id.listViewCours);
		cManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		WSHelper.getInstance().addWSHelperListener(
				AudioByFilterActivity.this);
		WSHelper.getInstance().getAudioCours(getIntent().getStringExtra("url"),
				cManager, AudioByFilterActivity.this);
	}

	@Override
	public void onAuthorsLoaded(List<String[]> Auteurs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorLoadingAuthors(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAudioListLoaded(final List<CoursAudio> Cours) {
		// TODO Auto-generated method stub
		Log.i("Cours :", "" + Cours.size());
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				lstViewCoursByIntervenant.setAdapter(new LastAddAdapter(
						AudioByFilterActivity.this,
						AudioByFilterActivity.this, Cours));
			}
		});
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

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		WSHelper.getInstance().removeWSHelperListener(this);
	}
}
