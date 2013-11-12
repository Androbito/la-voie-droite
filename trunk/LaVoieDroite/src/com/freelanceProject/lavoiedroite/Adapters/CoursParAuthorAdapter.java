package com.freelanceProject.lavoiedroite.Adapters;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.freelanceProject.lavoiedroite.R;
import com.freelanceProject.lavoiedroite.beans.CoursParAuthors;

public class CoursParAuthorAdapter extends ArrayAdapter<CoursParAuthors>{

	Context context; 
	int layoutResourceId;    
	CoursParAuthors data[] = null;
	static final String AUDIO_PATH ="http://casaboulot.com/lavoiedroite.net/system/files/joumoua_lesson_caverne250408_1.mp3";
	private MediaPlayer mediaPlayer;
	private boolean playPause;
	CoursAuthorsHolder holder = null;
	ProgressDialog uploadingDialog;

	public CoursParAuthorAdapter(Context context, int layoutResourceId, CoursParAuthors[] data) {
		super(context, layoutResourceId, data);

		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;



		if(row == null)
		{
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);


			holder = new CoursAuthorsHolder();

			holder.nbre = (TextView)row.findViewById(R.id.nbre);
			holder.text = (TextView)row.findViewById(R.id.text);
			holder.play=(ImageView)row.findViewById(R.id.play);
			holder.msg=(ImageView)row.findViewById(R.id.msg);
			holder.download=(ImageView)row.findViewById(R.id.download);

			row.setTag(holder);


		}

		else
		{
			holder = (CoursAuthorsHolder)row.getTag();
		}



		if (position % 2 == 1) {
			row.setBackgroundResource(R.drawable.bg_blue);
		} else {
			row.setBackgroundResource(R.drawable.bg_green); 
		}




		final CoursParAuthors coursAuthors = data[position];

		holder.nbre.setText(coursAuthors.nbre);
		holder.text.setText(coursAuthors.text);
		holder.play.setImageResource(coursAuthors.play);
		holder.msg.setImageResource(coursAuthors.msg);
		holder.download.setImageResource(coursAuthors.download);


		holder.play.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {


				if (!playPause) {
					//Player();
					holder.play.setImageResource(R.drawable.pause);
					playPause = true;  
					try {
						playAudio(AUDIO_PATH);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {

					if (mediaPlayer.isPlaying())
						mediaPlayer.pause();

					holder.play.setImageResource(R.drawable.player);
					playPause = false;

				}
			}
		});

		return row;

	}

	class CoursAuthorsHolder
	{
		TextView nbre;
		TextView text;
		ImageView play;
		ImageView msg;
		ImageView download;
	}



	private void playAudio(String url) throws Exception
	{
		killMediaPlayer();

		mediaPlayer = new MediaPlayer();
		mediaPlayer.setDataSource(url);
		mediaPlayer.prepare();
		mediaPlayer.start();
	}
	private void killMediaPlayer() {
		if(mediaPlayer!=null) {
			try {
				mediaPlayer.release();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void Player() {
		ProgressDialog progress = ProgressDialog.show(context, "",
                "Please wait...", true); {
		};
	}
}