package com.freelanceProject.lavoiedroite.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.freelanceProject.lavoiedroite.R;
import com.freelanceProject.lavoiedroite.beans.CoursParTheme;
import com.freelanceProject.lavoiedroite.beans.Events;

public class EventAdapter extends ArrayAdapter<Events>{

	Context context; 
	int layoutResourceId;    
	Events data[] = null;

	public EventAdapter(Context context, int layoutResourceId, Events[] data) {
		super(context, layoutResourceId, data);

		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		EventsHolder holder = null;


		
		if(row == null)
		{
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);


			holder = new EventsHolder();

			holder.img = (ImageView)row.findViewById(R.id.img);
			holder.icon_position = (ImageView)row.findViewById(R.id.img_position);
			holder.icon_sms = (ImageView)row.findViewById(R.id.img_sms);
			holder.icon_time=(ImageView)row.findViewById(R.id.img_time);
			holder.sms=(TextView) row.findViewById(R.id.sms);
			holder.position=(TextView) row.findViewById(R.id.txt_position);
			holder.time=(TextView) row.findViewById(R.id.txt_time);
			holder.title_event=(TextView) row.findViewById(R.id.txt_event);

			row.setTag(holder);


		}

		else
		{
			holder = (EventsHolder)row.getTag();
		}



		if (position % 2 == 1) {
			row.setBackgroundResource(R.drawable.bg_liste) ;
		} else {
			row.setBackgroundResource(R.drawable.bg_listee); 
		}


		Events event = data[position];

		holder.title_event.setText(event.title_event);
		holder.time.setText(event.time);
		holder.sms.setText(event.sms);
		holder.position.setText(event.position);
		holder.icon_sms.setImageResource(event.icon_sms);
		holder.icon_position.setImageResource(event.icon_position);
		holder.icon_time.setImageResource(event.icon_time);
		holder.img.setImageResource(event.img);
		
		row.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context,"redirect to concerning page !!!", Toast.LENGTH_SHORT).show();
				
			}
		});

		return row;
	}

	static class EventsHolder
	{
		ImageView img;
		ImageView icon_sms;
		ImageView icon_time;
		ImageView icon_position;
		TextView title_event;
		TextView time;
		TextView sms;
		TextView position;
		
	
		
		
	}
}