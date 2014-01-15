package com.freelanceProject.lavoiedroite.Adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freelanceProject.lavoiedroite.R;
import com.freelanceProject.lavoiedroite.beans.Sserie;

public class SeriesAdapter extends BaseAdapter {
	LayoutInflater mLayoutInflater;
	Context mContext;
	Activity mActivity;
	List<Sserie> mListSserie;

	public SeriesAdapter(Context mContext, Activity mActivity,
			List<Sserie> lstSserie) {
		super();
		this.mContext = mContext;
		this.mActivity = mActivity;
		this.mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mListSserie = new ArrayList<Sserie>();
		this.mListSserie.addAll(lstSserie);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListSserie.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mListSserie.get(position);
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
		if (position % 2 == 00)
			((LinearLayout) view.findViewById(R.id.itemAudioLay))
					.setBackgroundColor(Color.parseColor("#100000FF"));
		((TextView) view.findViewById(R.id.coursTitleitem)).setText(mListSserie
				.get(position).getTitle());
		((TextView) view.findViewById(R.id.coursIntervitem))
				.setText(mListSserie.get(position).getIntervenant());
		((TextView) view.findViewById(R.id.coursAudioPartitem)).setText(""
				+ mListSserie.get(position).getAudio_count());
		((TextView) view.findViewById(R.id.coursNbreVisiteuritem)).setText(""
				+ mListSserie.get(position).getVisites());
		((TextView) view.findViewById(R.id.date)).setText(""
				+ mListSserie.get(position).getDateCreation());
		return view;
	}
}
