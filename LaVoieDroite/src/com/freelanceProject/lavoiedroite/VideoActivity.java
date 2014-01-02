package com.freelanceProject.lavoiedroite;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.freelanceProject.lavoiedroite.Adapters.VideoAdapter;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioDetail;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioList;
import com.freelanceProject.lavoiedroite.beans.WsResponseEvents;
import com.freelanceProject.lavoiedroite.beans.WsResponseFaTArt;
import com.freelanceProject.lavoiedroite.beans.WsResponseTheme;
import com.freelanceProject.lavoiedroite.beans.WsResponseVideo;
import com.freelanceProject.lavoiedroite.ws.WSHelper;
import com.freelanceProject.lavoiedroite.ws.WSHelperListener;

public class VideoActivity extends Activity implements WSHelperListener {
	ConnectivityManager cManager;
	ListView lstViewVideo;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_video);
		lstViewVideo = ((ListView) findViewById(R.id.listVideo));
		ImageView back = (ImageView) findViewById(R.id.back);
		cManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
			}
		});
		WSHelper.getInstance().addWSHelperListener(this);
		WSHelper.getInstance().getVideo(cManager, this);
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
	public void onVideoLoaded(final WsResponseVideo wsResponseVideo) {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				lstViewVideo.setAdapter(new VideoAdapter(
						getApplicationContext(), wsResponseVideo
								.getListVideos()));
				lstViewVideo.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						// Intent videoPlay = new
						// Intent(getApplicationContext(),
						// VideoPlayerActivity.class);
						// videoPlay.putExtra("url", wsResponseVideo
						// .getListVideos().get(position).getUrl());
						// videoPlay.putExtra("titre", wsResponseVideo
						// .getListVideos().get(position).getTitle());
						// videoPlay.putExtra("name", wsResponseVideo
						// .getListVideos().get(position).getName());
						// startActivity(videoPlay);
						String url = wsResponseVideo.getListVideos()
								.get(position).getUrl();
						Uri uri = Uri.parse(url);

						String videoID = uri.getQueryParameter("v");
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri
								.parse("vnd.youtube:" + videoID));
						intent.putExtra("VIDEO_ID", videoID);
						startActivity(intent);
					}
				});
			}
		});

	}

	@Override
	public void onErrorLoadingVideo(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub

		super.onStop();
		WSHelper.getInstance().removeWSHelperListener(this);
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
}
