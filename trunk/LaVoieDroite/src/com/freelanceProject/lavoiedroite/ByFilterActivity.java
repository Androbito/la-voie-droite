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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.freelanceProject.lavoiedroite.Adapters.LastAddAdapter;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioDetail;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioList;
import com.freelanceProject.lavoiedroite.beans.WsResponseEvents;
import com.freelanceProject.lavoiedroite.beans.WsResponseTheme;
import com.freelanceProject.lavoiedroite.beans.WsResponseVideo;
import com.freelanceProject.lavoiedroite.ws.WSHelper;
import com.freelanceProject.lavoiedroite.ws.WSHelperListener;

public class ByFilterActivity extends Activity implements WSHelperListener {
	ListView lstViewCoursByIntervenant;
	ConnectivityManager cManager;
	String titre = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.byfiltre);
		lstViewCoursByIntervenant = (ListView) findViewById(R.id.listViewCours);
		cManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		WSHelper.getInstance().addWSHelperListener(ByFilterActivity.this);
		WSHelper.getInstance().getItemByFilter(
				getIntent().getStringExtra("url"), cManager,
				ByFilterActivity.this);
		if (getIntent().getBooleanExtra("isByInter", false)) {
			((LinearLayout) findViewById(R.id.interBarInfo))
					.setVisibility(View.VISIBLE);
			((TextView) findViewById(R.id.intervenantName)).setText(getIntent()
					.getStringExtra("intervenantName"));
		}
		if (getIntent().getStringExtra("tid").equals("9"))
			titre = "Conférences";
		if (getIntent().getStringExtra("tid").equals("8"))
			titre = "Cours audio";
		if (getIntent().getStringExtra("tid").equals("10"))
			titre = "Prêches";
		((TextView) findViewById(R.id.title)).setText(titre);
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
		// TODO Auto-generated method stub
		Log.i("Cours :", "" + wsResponseAudioList.getListCoursAudio().size());
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				lstViewCoursByIntervenant.setAdapter(new LastAddAdapter(
						ByFilterActivity.this, ByFilterActivity.this,
						wsResponseAudioList.getListCoursAudio()));
				if (getIntent().getBooleanExtra("isByInter", false)) {
					((TextView) findViewById(R.id.nbreArt)).setText("("
							+ wsResponseAudioList.getInterInfoBar()
									.getArticles() + ")");
					((TextView) findViewById(R.id.nbreConf)).setText("("
							+ wsResponseAudioList.getInterInfoBar()
									.getConferences() + ")");
					((TextView) findViewById(R.id.nbreCours)).setText("("
							+ wsResponseAudioList.getInterInfoBar()
									.getCours_audio() + ")");
					((TextView) findViewById(R.id.nbrePre)).setText("("
							+ wsResponseAudioList.getInterInfoBar()
									.getPreches() + ")");
				}
				lstViewCoursByIntervenant
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
}
