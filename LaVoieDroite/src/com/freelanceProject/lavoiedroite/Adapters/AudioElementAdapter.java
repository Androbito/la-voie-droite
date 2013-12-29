package com.freelanceProject.lavoiedroite.Adapters;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freelanceProject.lavoiedroite.R;
import com.freelanceProject.lavoiedroite.beans.AudioElement;

public class AudioElementAdapter extends BaseAdapter {
	LayoutInflater mLayoutInflater;
	Context mContext;
	Activity mActivity;
	String intervenant;
	List<AudioElement> mListAudio;
	MediaPlayer mediaPlayer = new MediaPlayer();
	AudioManager audioManager;
	ProgressDialog mProgressDialog;

	public AudioElementAdapter(Context mContext, Activity mActivity,
			List<AudioElement> lstAudio, String mIntervenant) {
		super();
		this.mContext = mContext;
		this.mActivity = mActivity;
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
		((ImageView) view.findViewById(R.id.play))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View button) {
						// TODO Auto-generated method stub

						((TextView) mActivity
								.findViewById(R.id.coursIntervenant))
								.setText(AudioElementAdapter.this.intervenant);
						((TextView) mActivity.findViewById(R.id.coursTitle))
								.setText(mListAudio.get(position)
										.getDescription());
						Stream(button, view, mListAudio.get(position).getUrl());
					}
				});
		return view;
	}

	public void Stream(View button, View view, final String url) {
		if (!mediaPlayer.isPlaying()) {
			mProgressDialog = new ProgressDialog(this.mActivity);
			mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			mProgressDialog.setMessage("Stream loading...");
			mProgressDialog.show();
			new Thread((new Runnable() {
				Message msg = null;

				public void run() {
					try {
						play(new URL(url));
						msg = mHandler.obtainMessage(1);
						// sends the message to our handler
						mHandler.sendMessage(msg);
						mediaPlayer.start();
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			})).start();
			((ImageView) view.findViewById(R.id.play))
					.setImageResource(R.drawable.pause);
		} else if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
			mediaPlayer.reset();
			((ImageView) mActivity.findViewById(R.id.playIcon))
					.setImageResource(R.drawable.player);
			((ImageView) view.findViewById(R.id.play))
					.setImageResource(R.drawable.player);
		}
	}

	protected void play(URL url) {
		try {
			mediaPlayer.setDataSource(url.toString());
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	final Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				mProgressDialog.dismiss();
				((ImageView) mActivity.findViewById(R.id.playIcon))
						.setImageResource(R.drawable.pause);
				break;
			}
		}
	};
}