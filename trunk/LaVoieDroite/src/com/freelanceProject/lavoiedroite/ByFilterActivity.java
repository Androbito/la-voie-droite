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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.freelanceProject.lavoiedroite.Adapters.LastAddAdapter;
import com.freelanceProject.lavoiedroite.beans.CoursAudio;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioDetail;
import com.freelanceProject.lavoiedroite.beans.WsResponseTheme;
import com.freelanceProject.lavoiedroite.ws.WSHelper;
import com.freelanceProject.lavoiedroite.ws.WSHelperListener;

public class ByFilterActivity extends Activity implements WSHelperListener {
	ListView lstViewCoursByIntervenant;
	ConnectivityManager cManager;

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
		if (getIntent().getStringExtra("tid").equals("9"))
			((TextView) findViewById(R.id.title)).setText("Conférences");
		if (getIntent().getStringExtra("tid").equals("8"))
			((TextView) findViewById(R.id.title)).equals("Cours audio");
		if (getIntent().getStringExtra("tid").equals("10"))
			((TextView) findViewById(R.id.title)).equals("Prêches");
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
						ByFilterActivity.this, ByFilterActivity.this, Cours));
				lstViewCoursByIntervenant
						.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> arg0,
									View arg1, int position, long arg3) {
								if (!Cours.get(position).getAudioType()
										.getValue().equalsIgnoreCase("serie")) {
									Intent goToAudiodetail = new Intent(
											getApplicationContext(),
											AudioCoursActivity.class);
									goToAudiodetail.putExtra("idAudio", ""
											+ Cours.get(position).getNid());
									goToAudiodetail.putExtra("intervenant", ""
											+ Cours.get(position)
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
}
