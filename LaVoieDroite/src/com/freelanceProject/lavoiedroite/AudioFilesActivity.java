package com.freelanceProject.lavoiedroite;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.freelanceProject.lavoiedroite.Adapters.AudioElementAdapter;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioDetail;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioList;
import com.freelanceProject.lavoiedroite.beans.WsResponseEvents;
import com.freelanceProject.lavoiedroite.beans.WsResponseFaTArt;
import com.freelanceProject.lavoiedroite.beans.WsResponseSouSeries;
import com.freelanceProject.lavoiedroite.beans.WsResponseTheme;
import com.freelanceProject.lavoiedroite.beans.WsResponseVideo;
import com.freelanceProject.lavoiedroite.streaming.StreamingMediaPlayer;
import com.freelanceProject.lavoiedroite.ws.WSHelper;
import com.freelanceProject.lavoiedroite.ws.WSHelperListener;

public class AudioFilesActivity extends Activity implements WSHelperListener {

	ConnectivityManager cManager;
	ListView lstViewAudios;

	WakeLock wl;
	PowerManager pm;
	ImageView playButton, back;
	private boolean isPlaying;
	protected StreamingMediaPlayer audioStreamer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.audiodetail);
		pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "MyTag");
		lstViewAudios = (ListView) findViewById(R.id.listAudio);
		cManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		WSHelper.getInstance().addWSHelperListener(this);
		WSHelper.getInstance().getItemDetails(cManager, this,
				getIntent().getStringExtra("idAudio"));
		((TextView) findViewById(R.id.title)).setText(getIntent()
				.getStringExtra("title"));
		if (getIntent().getStringExtra("title").equalsIgnoreCase("conférences"))
			((ImageView) findViewById(R.id.imageView1))
					.setImageResource(R.drawable.conference);
		if (getIntent().getStringExtra("title").equalsIgnoreCase("prêches"))
			((ImageView) findViewById(R.id.imageView1))
					.setImageResource(R.drawable.preche);
		back = (ImageView) findViewById(R.id.back);
		playButton = (ImageView) findViewById(R.id.playIcon);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		playButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (lstViewAudios.getAdapter() != null) {
					audioStreamer = ((AudioElementAdapter) lstViewAudios
							.getAdapter()).getAudioStreamer();
					if (audioStreamer != null) {
						if (audioStreamer.getMediaPlayer().isPlaying()) {
							audioStreamer.getMediaPlayer().pause();
							playButton.setImageResource(R.drawable.player);
						} else {
							audioStreamer.getMediaPlayer().start();
							audioStreamer.startPlayProgressUpdater();
							playButton.setImageResource(R.drawable.pause);
						}
						isPlaying = !isPlaying;
					}
				}
			}
		});
	}

	@Override
	public void onAuthorsLoaded(List<String[]> Auteurs) {

	}

	@Override
	public void onErrorLoadingAuthors(String string) {

	}

	@Override
	public void onErrorLoadingCours(String string) {

	}

	@Override
	public void onThemesLoaded(WsResponseTheme wsResponseTheme) {

	}

	@Override
	public void onErrorLoadingThemes(String string) {

	}

	@Override
	public void onDetailItemLoaded(
			final WsResponseAudioDetail wsResponseAudioDetail) {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				lstViewAudios.setAdapter(new AudioElementAdapter(
						getApplicationContext(), wl, AudioFilesActivity.this,
						wsResponseAudioDetail.getListAudio(), getIntent()
								.getStringExtra("intervenant")));
			}
		});
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (audioStreamer != null && audioStreamer.getMediaPlayer() != null) {
			audioStreamer.getMediaPlayer().stop();
			audioStreamer.getMediaPlayer().reset();
		}
		if (wl.isHeld())
			wl.release();
		WSHelper.getInstance().removeWSHelperListener(this);
	}

	@Override
	public void onErrorLoadingItemDetail(String string) {

	}

	@Override
	public void onAudioListLoaded(WsResponseAudioList wsResponseAudioList) {

	}

	@Override
	public void onVideoLoaded(WsResponseVideo wsResponseVideo) {

	}

	@Override
	public void onErrorLoadingVideo(String string) {

	}

	@Override
	public void onEventsLoaded(WsResponseEvents wsResponseEvents) {

	}

	@Override
	public void onErrorLoadingEvents(String string) {

	}

	@Override
	public void onFatArtLoaded(WsResponseFaTArt wsResponseFaTArt) {

	}

	@Override
	public void onErrorLoadingFatArt(String error) {

	}

	@Override
	public void onSerieLoaded(WsResponseSouSeries wsResponseSserie) {

	}

	@Override
	public void onErrorLoadingSerie(String error) {

	}

}
