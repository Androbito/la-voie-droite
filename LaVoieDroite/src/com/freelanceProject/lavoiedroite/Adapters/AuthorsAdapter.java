package com.freelanceProject.lavoiedroite.Adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.freelanceProject.lavoiedroite.R;

public class AuthorsAdapter extends BaseAdapter {
	LayoutInflater mLayoutInflater;
	Context mContext;
	List<String[]> mListAuteurs;

	public AuthorsAdapter(Context mContext, List<String[]> auteurs) {
		super();
		this.mContext = mContext;
		this.mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mListAuteurs = new ArrayList<String[]>(0);
		this.mListAuteurs.addAll(auteurs);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.mListAuteurs.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.mListAuteurs.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = mLayoutInflater
				.inflate(R.layout.auteur_item, parent, false);
		((TextView) view.findViewById(R.id.intervenant)).setText(mListAuteurs
				.get(position)[0]);
		if (position % 2 == 0)
			((TextView) view.findViewById(R.id.intervenant))
					.setBackgroundColor(Color.parseColor("#100000FF"));
		return view;
	}
}