package com.freelanceProject.lavoiedroite.Adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freelanceProject.lavoiedroite.R;
import com.freelanceProject.lavoiedroite.beans.FatArt;

public class LastFatArtAdapter extends BaseAdapter {
	LayoutInflater mLayoutInflater;
	Context mContext;
	List<FatArt> mListFatArt;

	public LastFatArtAdapter(Context mContext, List<FatArt> lstFatArt) {
		super();
		this.mContext = mContext;
		this.mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mListFatArt = new ArrayList<FatArt>();
		if (lstFatArt.size() > 10)
			this.mListFatArt.addAll(lstFatArt.subList(0, 10));
		else
			this.mListFatArt.addAll(lstFatArt);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListFatArt.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mListFatArt.get(position);
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
		final View view = mLayoutInflater.inflate(R.layout.fatart_item, parent,
				false);
		if (position % 2 == 00)
			((LinearLayout) view.findViewById(R.id.itemAudioLay))
					.setBackgroundColor(Color.parseColor("#100000FF"));
		((TextView) view.findViewById(R.id.coursTitleitem)).setText(mListFatArt
				.get(position).getTitle());
		((TextView) view.findViewById(R.id.coursCategorieitem))
				.setText(mListFatArt.get(position).getSource());
		((TextView) view.findViewById(R.id.coursIntervitem))
				.setText(mListFatArt.get(position).getAuteur());
		((TextView) view.findViewById(R.id.coursNbreVisiteuritem)).setText(""
				+ mListFatArt.get(position).getVisites());
		((TextView) view.findViewById(R.id.date)).setText(""
				+ mListFatArt.get(position).getDateCreation());
		return view;
	}
}
