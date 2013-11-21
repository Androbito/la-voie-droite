package com.freelanceProject.lavoiedroite;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.freelanceProject.lavoiedroite.beans.CoursAudio;
import com.freelanceProject.lavoiedroite.beans.WsResponseTheme;
import com.freelanceProject.lavoiedroite.ws.WSHelper;
import com.freelanceProject.lavoiedroite.ws.WSHelperListener;

public class AudioByThemeActivity extends Activity implements WSHelperListener {
	ConnectivityManager cManager;
	ListView lstViewCategorie;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.audiobyintervenant);
		lstViewCategorie = (ListView) findViewById(R.id.listViewCategories);
		cManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		WSHelper.getInstance().addWSHelperListener(this);
		WSHelper.getInstance().getThemes(cManager, this);
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
	public void onLastAddLoaded(List<CoursAudio> Cours) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorLoadingCours(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onThemesLoaded(WsResponseTheme wsResponseTheme) {
		// TODO Auto-generated method stub
		Log.i("Nbre de categories", "" + wsResponseTheme.getCategories().size());
		for (int i = 0; i < wsResponseTheme.getCategories().size(); i++)
			Log.i(wsResponseTheme.getCategories().get(i).getDetailCategoie(),
					""
							+ wsResponseTheme.getCategories().get(i)
									.getThemes().size());
	}

	@Override
	public void onErrorLoadingThemes(String string) {
		// TODO Auto-generated method stub

	}

}
