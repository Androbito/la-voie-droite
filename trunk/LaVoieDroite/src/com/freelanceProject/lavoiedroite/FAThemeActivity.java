package com.freelanceProject.lavoiedroite;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SimpleExpandableListAdapter;

import com.freelanceProject.lavoiedroite.Adapters.ThemeAdapter;
import com.freelanceProject.lavoiedroite.beans.Categorie;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioDetail;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioList;
import com.freelanceProject.lavoiedroite.beans.WsResponseEvents;
import com.freelanceProject.lavoiedroite.beans.WsResponseFaTArt;
import com.freelanceProject.lavoiedroite.beans.WsResponseSouSeries;
import com.freelanceProject.lavoiedroite.beans.WsResponseTheme;
import com.freelanceProject.lavoiedroite.beans.WsResponseVideo;
import com.freelanceProject.lavoiedroite.ws.URLs;
import com.freelanceProject.lavoiedroite.ws.WSHelper;
import com.freelanceProject.lavoiedroite.ws.WSHelperListener;

public class FAThemeActivity extends Activity implements WSHelperListener {
	ConnectivityManager cManager;
	ExpandableListView expandableList;
	private List<Categorie> listCategories = new ArrayList<Categorie>(0);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.themes);
		expandableList = (ExpandableListView) findViewById(R.id.list_theme);

		cManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		WSHelper.getInstance().addWSHelperListener(this);
		WSHelper.getInstance().getThemes(getIntent().getStringExtra("tid"),
				cManager, this);

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
	public void onErrorLoadingCours(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onThemesLoaded(WsResponseTheme wsResponseTheme) {
		Log.i("Nbre de categories", "" + wsResponseTheme.getCategories().size());
		for (int i = 0; i < wsResponseTheme.getCategories().size(); i++)
			Log.i(wsResponseTheme.getCategories().get(i).getDetailCategoie(),
					""
							+ wsResponseTheme.getCategories().get(i)
									.getThemes().size());
		listCategories.addAll(wsResponseTheme.getCategories());
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				expandableList.setAdapter(new ThemeAdapter(
						getApplicationContext(), listCategories));
				expandableList
						.setOnChildClickListener(new OnChildClickListener() {

							@Override
							public boolean onChildClick(
									ExpandableListView parent, View v,
									int groupPosition, int childPosition,
									long id) {
								// TODO Auto-generated method stub

								Intent bythemes = new Intent(
										getApplicationContext(),
										FAByThemeActivity.class);
								bythemes.putExtra(
										"url",
										URLs.FatArtUrl
												+ getIntent().getStringExtra(
														"type")
												+ "&cat="
												+ listCategories
														.get(groupPosition)
														.getThemes()
														.get(childPosition)
														.getIdTheme());
								bythemes.putExtra("type", getIntent()
										.getStringExtra("type"));
								startActivity(bythemes);
								return true;
							}
						});
			}
		});
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

	@Override
	public void onDetailItemLoaded(WsResponseAudioDetail wsResponseAudioDetail) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorLoadingItemDetail(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAudioListLoaded(WsResponseAudioList wsResponseAudioList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onVideoLoaded(WsResponseVideo wsResponseVideo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorLoadingVideo(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEventsLoaded(WsResponseEvents wsResponseEvents) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorLoadingEvents(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFatArtLoaded(WsResponseFaTArt wsResponseFaTArt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorLoadingFatArt(String error) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSerieLoaded(WsResponseSouSeries wsResponseSserie) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorLoadingSerie(String error) {
		// TODO Auto-generated method stub

	}

}
