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
import com.freelanceProject.lavoiedroite.beans.CoursAudio;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioDetail;
import com.freelanceProject.lavoiedroite.beans.WsResponseTheme;
import com.freelanceProject.lavoiedroite.ws.URLs;
import com.freelanceProject.lavoiedroite.ws.WSHelper;
import com.freelanceProject.lavoiedroite.ws.WSHelperListener;

public class LastAddActivity extends Activity implements WSHelperListener {
	ConnectivityManager cManager;
	ListView lstViewLastCours;

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
	public void onAudioListLoaded(final List<CoursAudio> lastCours) {
		Log.i("Cours :", "" + lastCours.size());
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				lstViewLastCours.setAdapter(new LastAddAdapter(
						LastAddActivity.this, LastAddActivity.this, lastCours));
				lstViewLastCours
						.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> arg0,
									View arg1, int position, long arg3) {
								if (!lastCours.get(position).getAudioType()
										.getValue().equalsIgnoreCase("serie")) {
									Intent goToAudiodetail = new Intent(
											getApplicationContext(),
											AudioCoursActivity.class);
									goToAudiodetail.putExtra("idAudio", ""
											+ lastCours.get(position).getNid());
									goToAudiodetail.putExtra("intervenant", ""
											+ lastCours.get(position)
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
