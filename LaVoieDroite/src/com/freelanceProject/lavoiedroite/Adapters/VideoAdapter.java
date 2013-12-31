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
import com.freelanceProject.lavoiedroite.beans.VideoCours;

public class VideoAdapter extends BaseAdapter {
	LayoutInflater mLayoutInflater;
	Context mContext;
	List<VideoCours> listVideoCours;

	public VideoAdapter(Context mContext, List<VideoCours> mListVideoCours) {
		super();
		this.mContext = mContext;
		this.mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.listVideoCours = new ArrayList<VideoCours>();
		this.listVideoCours.addAll(mListVideoCours);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listVideoCours.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listVideoCours.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, final View convertView,
			final ViewGroup parent) {
		// TODO Auto-generated method stub
		final View view = mLayoutInflater.inflate(R.layout.canvavideo, parent,
				false);
		if (position % 2 == 00)
			((LinearLayout) view.findViewById(R.id.layVideo))
					.setBackgroundColor(Color.parseColor("#100000FF"));
		((TextView) view.findViewById(R.id.textVideoTitre))
				.setText(listVideoCours.get(position).getName());
		((TextView) view.findViewById(R.id.textVideoDetails))
				.setText(listVideoCours.get(position).getTitle());
		return view;
	}

}
