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
		Log.i("Categories", "" + wsResponseTheme.getCategories().size());
		Log.i("coranCat", "" + wsResponseTheme.getThemes().coranCat.size());
		Log.i("hadithsCat", "" + wsResponseTheme.getThemes().hadithsCat.size());
		Log.i("croyanceCat",
				"" + wsResponseTheme.getThemes().croyanceCat.size());
		Log.i("comportementsCat", ""
				+ wsResponseTheme.getThemes().comportementsCat.size());
		Log.i("jurisprudenceCat", ""
				+ wsResponseTheme.getThemes().jurisprudenceCat.size());
		Log.i("histoiresCat",
				"" + wsResponseTheme.getThemes().histoiresCat.size());
		Log.i("evenementsCat",
				"" + wsResponseTheme.getThemes().evenementsCat.size());
		Log.i("familleCat", "" + wsResponseTheme.getThemes().familleCat.size());
		Log.i("sectesgroupesCat", ""
				+ wsResponseTheme.getThemes().sectesgroupesCat.size());
		Log.i("diversCat", "" + wsResponseTheme.getThemes().diversCat.size());
	}

	@Override
	public void onErrorLoadingThemes(String string) {
		// TODO Auto-generated method stub

	}

}
