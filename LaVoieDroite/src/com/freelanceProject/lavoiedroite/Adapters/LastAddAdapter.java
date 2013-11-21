package com.freelanceProject.lavoiedroite.Adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freelanceProject.lavoiedroite.R;
import com.freelanceProject.lavoiedroite.beans.CoursAudio;

public class LastAddAdapter extends BaseAdapter {
	LayoutInflater mLayoutInflater;
	Context mContext;
	Activity mActivity;
	List<CoursAudio> mListAudio;

	public LastAddAdapter(Context mContext, Activity mActivity,
			List<CoursAudio> lstAudio) {
		super();
		this.mContext = mContext;
		this.mActivity = mActivity;
		this.mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mListAudio = new ArrayList<CoursAudio>();
		this.mListAudio.addAll(lstAudio);
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
		final View view = mLayoutInflater.inflate(R.layout.audio_item, parent,
				false);
		((TextView) view.findViewById(R.id.coursTitleitem)).setText(mListAudio
				.get(position).getTitle());
		if (position % 2 == 0)
			((LinearLayout) view.findViewById(R.id.itemAudioLay))
					.setBackgroundResource(R.drawable.bg_green);
		else
			((LinearLayout) view.findViewById(R.id.itemAudioLay))
					.setBackgroundResource(R.drawable.bg_blue);
		((ImageView) view.findViewById(R.id.play))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						((TextView) mActivity
								.findViewById(R.id.coursIntervenant))
								.setText(mListAudio.get(position)
										.getIntervenant());
						((TextView) mActivity.findViewById(R.id.coursTitle))
								.setText(mListAudio.get(position).getTitle());
//						Toast.makeText(mContext,
//								mListAudio.get(position).getIntervenant(),
//								Toast.LENGTH_SHORT).show();
					}
				});
		return view;
	}
}
