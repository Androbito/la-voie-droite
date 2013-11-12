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

public class CoursParThemeAdapter extends ArrayAdapter<CoursParTheme>{

	Context context; 
	int layoutResourceId;    
	CoursParTheme data[] = null;

	public CoursParThemeAdapter(Context context, int layoutResourceId, CoursParTheme[] data) {
		super(context, layoutResourceId, data);

		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		CoursThemeHolder holder = null;


		
		if(row == null)
		{
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);


			holder = new CoursThemeHolder();

			holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
			holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
			holder.img=(ImageView)row.findViewById(R.id.img);

			row.setTag(holder);


		}

		else
		{
			holder = (CoursThemeHolder)row.getTag();
		}



		if (position % 2 == 1) {
			row.setBackgroundResource(R.drawable.bg_liste) ;
		} else {
			row.setBackgroundResource(R.drawable.bg_listee); 
		}


		CoursParTheme coursTheme = data[position];

		holder.imgIcon.setImageResource(coursTheme.icon);
		holder.txtTitle.setText(coursTheme.title);
		holder.img.setImageResource(coursTheme.icon_img);
		
		row.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context,"redirect to concerning page !!!", Toast.LENGTH_SHORT).show();
				
			}
		});

		return row;
	}

	static class CoursThemeHolder
	{
		ImageView imgIcon;
		TextView txtTitle;
		ImageView img;
	}
}