package com.freelanceProject.lavoiedroite;

import java.util.List;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.freelanceProject.lavoiedroite.Adapters.LastFatArtAdapter;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioDetail;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioList;
import com.freelanceProject.lavoiedroite.beans.WsResponseEvents;
import com.freelanceProject.lavoiedroite.beans.WsResponseFaTArt;
import com.freelanceProject.lavoiedroite.beans.WsResponseTheme;
import com.freelanceProject.lavoiedroite.beans.WsResponseVideo;
import com.freelanceProject.lavoiedroite.ws.WSHelper;
import com.freelanceProject.lavoiedroite.ws.WSHelperListener;

public class FAByThemeActivity extends Activity implements WSHelperListener {
	ListView lstViewLastFA;
	ConnectivityManager cManager;
	String titre = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fatartbytheme);
		lstViewLastFA = (ListView) findViewById(R.id.listViewFA);
		cManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		WSHelper.getInstance().addWSHelperListener(FAByThemeActivity.this);
		WSHelper.getInstance().getLastFatArt(getIntent().getStringExtra("url"),
				cManager, FAByThemeActivity.this);
		if (getIntent().getStringExtra("type").equals("fatwa"))
			titre = "Fatwas";
		if (getIntent().getStringExtra("type").equals("article"))
			titre = "Articles";
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
	public void onFatArtLoaded(final WsResponseFaTArt wsResponseFaTArt) {
		// TODO Auto-generated method stub
		Log.i("onFatArtLoaded", ""
				+ wsResponseFaTArt.getListFatArts().get(0).getNid());
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				lstViewLastFA.setAdapter(new LastFatArtAdapter(
						getApplicationContext(), wsResponseFaTArt
								.getListFatArts()));
				lstViewLastFA.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						// TODO Auto-generated method stub
						Intent reader = new Intent(getApplicationContext(),
								FAReaderActivity.class);
						reader.putExtra("pdf", wsResponseFaTArt
								.getListFatArts().get(position).getPdf());
						reader.putExtra("type", titre);
						startActivity(reader);
					}
				});
			}
		});

	}

	private void loadDocInReader(String doc) throws ActivityNotFoundException,
			Exception {

		try {
			Intent intent = new Intent();

			intent.setPackage("com.adobe.reader");
			intent.setDataAndType(Uri.parse(doc), "application/pdf");

			startActivity(intent);

		} catch (ActivityNotFoundException activityNotFoundException) {
			activityNotFoundException.printStackTrace();

			throw activityNotFoundException;
		} catch (Exception otherException) {
			otherException.printStackTrace();

			throw otherException;
		}
	}

	@Override
	public void onErrorLoadingFatArt(String error) {
		// TODO Auto-generated method stub

	}
}
