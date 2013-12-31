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
import android.widget.ImageView;
import android.widget.ListView;

import com.freelanceProject.lavoiedroite.Adapters.EvenementAdapter;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioDetail;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioList;
import com.freelanceProject.lavoiedroite.beans.WsResponseEvents;
import com.freelanceProject.lavoiedroite.beans.WsResponseTheme;
import com.freelanceProject.lavoiedroite.beans.WsResponseVideo;
import com.freelanceProject.lavoiedroite.ws.WSHelper;
import com.freelanceProject.lavoiedroite.ws.WSHelperListener;

public class EventActivity extends Activity implements WSHelperListener {

	ConnectivityManager cManager;
	ListView lstViewEvenement;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.evenements);
		cManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		ImageView back = (ImageView) findViewById(R.id.back);
		lstViewEvenement = (ListView) findViewById(R.id.list_event);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent menu = new Intent(EventActivity.this,
						com.freelanceProject.lavoiedroite.Menu.class);
				startActivity(menu);
			}
		});
		WSHelper.getInstance().addWSHelperListener(this);
		WSHelper.getInstance().getEvents(cManager, this);
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
	public void onEventsLoaded(final WsResponseEvents wsResponseEvents) {
		Log.i("nombre d'evenements", "" + wsResponseEvents.listEvents.size());
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				lstViewEvenement.setAdapter(new EvenementAdapter(
						getApplicationContext(), wsResponseEvents.listEvents));
			}
		});
	}

	@Override
	public void onErrorLoadingEvents(String string) {
		// TODO Auto-generated method stub
		Log.i("Error", string);
	}

}