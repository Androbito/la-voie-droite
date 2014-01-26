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

import com.freelanceProject.lavoiedroite.Adapters.LastAddAdapter;
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

public class LastAddActivity extends Activity implements WSHelperListener {
	ConnectivityManager cManager;
	ListView lstViewLastCours;
	private String titre;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.audiobylastadd);
		lstViewLastCours = (ListView) findViewById(R.id.listViewLastCours);
		cManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		WSHelper.getInstance().addWSHelperListener(this);
		WSHelper.getInstance().getItemByFilter(
				URLs.lastAdd + "tid=" + getIntent().getStringExtra("tid")
						+ "&page=0&npage=25", cManager, this);
		if (getIntent().getStringExtra("tid").equals("9"))
			titre = "Conférences";
		if (getIntent().getStringExtra("tid").equals("8"))
			titre = "Cours audio";
		if (getIntent().getStringExtra("tid").equals("10"))
			titre = "Prêches";
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
	public void onAudioListLoaded(final WsResponseAudioList wsResponseAudioList) {
		Log.i("Cours :", "" + wsResponseAudioList.getListCoursAudio().size());
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (wsResponseAudioList.getListCoursAudio().size() > 10)
					lstViewLastCours.setAdapter(new LastAddAdapter(
							LastAddActivity.this, LastAddActivity.this,
							wsResponseAudioList.getListCoursAudio().subList(0,
									10)));
				else
					lstViewLastCours.setAdapter(new LastAddAdapter(
							LastAddActivity.this, LastAddActivity.this,
							wsResponseAudioList.getListCoursAudio()));
				lstViewLastCours
						.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> arg0,
									View arg1, int position, long arg3) {
								if (!wsResponseAudioList.getListCoursAudio()
										.get(position).getAudioType()
										.getValue().equalsIgnoreCase("serie")) {
									Intent goToAudiodetail = new Intent(
											getApplicationContext(),
											AudioFilesActivity.class);
									goToAudiodetail.putExtra("title", titre);
									goToAudiodetail.putExtra(
											"date",
											wsResponseAudioList
													.getListCoursAudio()
													.get(position)
													.getDateCreation());
									goToAudiodetail
											.putExtra(
													"idAudio",
													""
															+ wsResponseAudioList
																	.getListCoursAudio()
																	.get(position)
																	.getNid());
									goToAudiodetail
											.putExtra(
													"intervenant",
													""
															+ wsResponseAudioList
																	.getListCoursAudio()
																	.get(position)
																	.getIntervenant());
									startActivity(goToAudiodetail);
								}

							}
						});
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
	public void onSerieLoaded(WsResponseSouSeries wsResponseSserie) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorLoadingSerie(String error) {
		// TODO Auto-generated method stub

	}

}
