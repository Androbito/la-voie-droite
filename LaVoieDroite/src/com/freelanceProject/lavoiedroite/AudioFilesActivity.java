package com.freelanceProject.lavoiedroite;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.freelanceProject.lavoiedroite.Adapters.AudioElementAdapter;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioDetail;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioList;
import com.freelanceProject.lavoiedroite.beans.WsResponseTheme;
import com.freelanceProject.lavoiedroite.beans.WsResponseVideo;
import com.freelanceProject.lavoiedroite.ws.WSHelper;
import com.freelanceProject.lavoiedroite.ws.WSHelperListener;

public class AudioFilesActivity extends Activity implements WSHelperListener {

	ConnectivityManager cManager;
	ListView lstViewAudios;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.audiodetail);
		lstViewAudios = (ListView) findViewById(R.id.listAudio);
		cManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		WSHelper.getInstance().addWSHelperListener(this);
		WSHelper.getInstance().getItemDetails(cManager, this,
				getIntent().getStringExtra("idAudio"));
		((TextView) findViewById(R.id.title)).setText(getIntent()
				.getStringExtra("title"));
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
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorLoadingThemes(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDetailItemLoaded(
			final WsResponseAudioDetail wsResponseAudioDetail) {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				lstViewAudios.setAdapter(new AudioElementAdapter(
						getApplicationContext(), AudioFilesActivity.this,
						wsResponseAudioDetail.getListAudio(), getIntent()
								.getStringExtra("intervenant")));
			}
		});
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub

		super.onStop();
		WSHelper.getInstance().removeWSHelperListener(this);
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

}
