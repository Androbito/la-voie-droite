package com.freelanceProject.lavoiedroite.Videos;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
import com.freelanceProject.lavoiedroite.R;

public class CoursVideo extends  Activity{
	static Context parentActivity;
	VideoView videoHolder;

@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video);
		
		
		ImageView back=(ImageView) findViewById(R.id.back);


		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent menu = new Intent(CoursVideo.this,
						ImagesVideo.class);
				startActivity(menu);				
			}
		});

		
		videoHolder = (VideoView) findViewById(R.id.video);
		
		parentActivity = this;
		
		MediaController controller = new MediaController(parentActivity)
         {
             @Override
             public void hide()
             {
                 this.show(0);
             }

             @Override
             public void setMediaPlayer(MediaPlayerControl player)
             {
                 super.setMediaPlayer(player);
                 this.show();
             }
         };

         controller.setAnchorView(videoHolder);
         videoHolder.setMediaController(controller);
		
		//if you want the controls to appear
		//videoHolder.setMediaController(new MediaController(this));
		
		String videoPath="android.resource://" + getPackageName() + File.separator +R.raw.afrikiavoix;
		System.out.println("videoPath"+videoPath);
		Uri video = Uri.parse(videoPath); 
		videoHolder.setVideoURI(video);
		videoHolder.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
 				
				Intent home = new Intent(parentActivity, CoursVideo.class);	
				((Activity) parentActivity).startActivity(home);
				finish();
			}
		});

		videoHolder.start();
}
		   }

	


