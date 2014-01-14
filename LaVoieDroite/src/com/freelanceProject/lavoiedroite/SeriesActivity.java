package com.freelanceProject.lavoiedroite;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.freelanceProject.lavoiedroite.Adapters.SeriesAdapter;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioDetail;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioList;
import com.freelanceProject.lavoiedroite.beans.WsResponseEvents;
import com.freelanceProject.lavoiedroite.beans.WsResponseFaTArt;
import com.freelanceProject.lavoiedroite.beans.WsResponseSouSeries;
import com.freelanceProject.lavoiedroite.beans.WsResponseTheme;
import com.freelanceProject.lavoiedroite.beans.WsResponseVideo;
import com.freelanceProject.lavoiedroite.ws.WSHelper;
import com.freelanceProject.lavoiedroite.ws.WSHelperListener;

public class SeriesActivity extends Activity implements WSHelperListener {
	ListView lstViewSSeries;
	ConnectivityManager cManager;
	String titre = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.serie_layout);
		lstViewSSeries = (ListView) findViewById(R.id.listViewSerie);
		((TextView) findViewById(R.id.title)).setText(getIntent()
				.getStringExtra("title"));
		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		cManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		WSHelper.getInstance().addWSHelperListener(SeriesActivity.this);
		WSHelper.getInstance().getSerie(getIntent().getStringExtra("nid"),
				cManager, SeriesActivity.this);
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
	public void onAudioListLoaded(WsResponseAudioList wsResponseAudioList) {
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

	@Override
	public void onDetailItemLoaded(WsResponseAudioDetail wsResponseAudioDetail) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorLoadingItemDetail(String string) {
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
	public void onSerieLoaded(final WsResponseSouSeries wsResponseSserie) {
		// TODO Auto-generated method stub
		Log.i("SousSerie", "" + wsResponseSserie.getListSseries().size());
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				lstViewSSeries.setAdapter(new SeriesAdapter(
						getApplicationContext(), SeriesActivity.this,
						wsResponseSserie.getListSseries()));
				lstViewSSeries
						.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> arg0,
									View arg1, int position, long arg3) {
								Intent goToAudiodetail = new Intent(
										getApplicationContext(),
										AudioFilesActivity.class);
								goToAudiodetail.putExtra("title", titre);
								goToAudiodetail.putExtra(
										"date",
										wsResponseSserie.getListSseries()
												.get(position)
												.getDateCreation());
								goToAudiodetail.putExtra("idAudio", ""
										+ wsResponseSserie.getListSseries()
												.get(position).getNid());
								goToAudiodetail
										.putExtra(
												"intervenant",
												""
														+ wsResponseSserie
																.getListSseries()
																.get(position)
																.getIntervenant());
								startActivity(goToAudiodetail);
							}
						});
			}
		});
	}

	@Override
	public void onErrorLoadingSerie(String error) {
		// TODO Auto-generated method stub

	}

}
