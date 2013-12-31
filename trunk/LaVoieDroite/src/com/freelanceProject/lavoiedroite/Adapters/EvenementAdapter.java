package com.freelanceProject.lavoiedroite.Adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freelanceProject.lavoiedroite.R;
import com.freelanceProject.lavoiedroite.beans.Evenement;

public class EvenementAdapter extends BaseAdapter {
	LayoutInflater mLayoutInflater;
	Context mContext;
	ArrayList<Evenement> listEvents;

	public EvenementAdapter(Context mContext, List<Evenement> mListEvents) {
		super();
		this.mContext = mContext;
		this.mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.listEvents = new ArrayList<Evenement>();
		this.listEvents.addAll(mListEvents);
		Log.i("listEvents", "" + listEvents.size());
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listEvents.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return listEvents.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(final int position, final View convertView,
			final ViewGroup parent) {
		// TODO Auto-generated method stub
		final View view = mLayoutInflater.inflate(R.layout.evenement_item, parent,
				false);
		((TextView) view.findViewById(R.id.titleEvent)).setText(listEvents.get(
				position).getTitle());
		((TextView) view.findViewById(R.id.dateEvent)).setText(listEvents.get(
				position).getDate());
		((TextView) view.findViewById(R.id.locationEvent)).setText(listEvents
				.get(position).getVille());
		((TextView) view.findViewById(R.id.intervenantEvent))
				.setText(listEvents.get(position).getIntervenant());
		final LinearLayout detail = ((LinearLayout) view
				.findViewById(R.id.detailsEventLay));
		((LinearLayout) view.findViewById(R.id.layEvent))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (detail.getVisibility() == View.VISIBLE)
							detail.setVisibility(View.GONE);
						else {
							detail.setVisibility(View.VISIBLE);
							((WebView) view.findViewById(R.id.webEvent))
									.loadDataWithBaseURL(null,
											listEvents.get(position)
													.getDetails(), "text/html",
											"UTF-8", null);
						}
					}
				});
		return view;
	}
}