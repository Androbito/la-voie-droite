package com.freelanceProject.lavoiedroite.Adapters;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.freelanceProject.lavoiedroite.R;
import com.freelanceProject.lavoiedroite.beans.AudioElement;
import com.freelanceProject.lavoiedroite.streaming.StreamingMediaPlayer;
import com.freelanceProject.lavoiedroite.web.DownloadManager;

public class AudioElementAdapter extends BaseAdapter {
	LayoutInflater mLayoutInflater;
	Context mContext;
	Activity mActivity;
	String intervenant;
	List<AudioElement> mListAudio;
	StreamingMediaPlayer audioStreamer;
	AudioManager audioManager;
	ProgressDialog mProgressDialog;
	private DownloadManager downloadManager;

	public AudioElementAdapter(StreamingMediaPlayer mediaPlayer,
			Context mContext, Activity mActivity, List<AudioElement> lstAudio,
			String mIntervenant) {
		super();
		this.mContext = mContext;
		this.mActivity = mActivity;
		this.audioStreamer = mediaPlayer;
		this.downloadManager = new DownloadManager(
				mContext.getContentResolver(),
				"com.freelanceProject.lavoiedroite");
		this.intervenant = mIntervenant;
		this.mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mListAudio = new ArrayList<AudioElement>();
		this.mListAudio.addAll(lstAudio);
		this.audioManager = (AudioManager) mActivity
				.getSystemService(Context.AUDIO_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListAudio.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mListAudio.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, final View convertView,
			final ViewGroup parent) {
		// TODO Auto-generated method stub
		final View view = mLayoutInflater.inflate(R.layout.audio_item_detail,
				parent, false);
		((TextView) view.findViewById(R.id.position)).setText(""
				+ (position + 1));
		((TextView) view.findViewById(R.id.coursTitleitem)).setText(mListAudio
				.get(position).getDescription());
		((TextView) view.findViewById(R.id.intervenantName))
				.setText(this.intervenant);
		if (position % 2 == 0)
			((LinearLayout) view.findViewById(R.id.itemLay))
					.setBackgroundColor(Color.parseColor("#004493"));
		else
			((LinearLayout) view.findViewById(R.id.itemLay))
					.setBackgroundColor(Color.parseColor("#1457BA"));
		((ImageView) view.findViewById(R.id.download))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						DownloadManager.Request request = new DownloadManager.Request(
								Uri.parse(mListAudio.get(position).getUrl()));
						request.setDescription(mListAudio.get(position)
								.getDescription());
						request.setTitle("Téléchargement du cours");
						request.setDestinationUri(Uri
								.fromFile(new File(Environment
										.getExternalStorageDirectory()
										.toString()
										+ "/LaVoieDroite_mp3", mListAudio
										.get(position)
										.getUrl()
										.substring(
												mListAudio.get(position)
														.getUrl()
														.lastIndexOf("/") + 1))));
						downloadManager.enqueue(request);
					}
				});
		((ImageView) view.findViewById(R.id.smsend))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent sendIntent = new Intent(Intent.ACTION_VIEW);
						sendIntent.putExtra("sms_body",
								"Je vous recommande le contenu suivant: "
										+ mListAudio.get(position).getUrl());
						sendIntent.setType("vnd.android-dir/mms-sms");
						mActivity.startActivity(sendIntent);
					}
				});
		((ImageView) view.findViewById(R.id.play))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View button) {
						// TODO Auto-generated method stub
						startStreamingAudio(mListAudio.get(position).getUrl(),
								((ImageView) mActivity
										.findViewById(R.id.playIcon)),
								((TextView) mActivity
										.findViewById(R.id.text_kb_streamed)),
								audioStreamer, ((ProgressBar) mActivity
										.findViewById(R.id.strmPBH)));
						((TextView) mActivity
								.findViewById(R.id.coursIntervenant))
								.setText(AudioElementAdapter.this.intervenant);
						((TextView) mActivity.findViewById(R.id.coursTitle))
								.setText(mListAudio.get(position)
										.getDescription());
						((ImageView) mActivity.findViewById(R.id.playIcon))
								.setTag(mListAudio.get(position).getUrl());
					}
				});
		return view;
	}

	private void startStreamingAudio(String url, ImageView playButton,
			TextView textStreamed, StreamingMediaPlayer audioStreamer,
			ProgressBar progressBar) {
		try {
			if (audioStreamer != null) {
				audioStreamer.interrupt();
			}
			audioStreamer = new StreamingMediaPlayer(mActivity, textStreamed,
					playButton, progressBar);
			audioStreamer.startStreaming(url, 1677, 214);
		} catch (IOException e) {
			Log.e(getClass().getName(), "Error starting to stream audio.", e);
		}

	}
}