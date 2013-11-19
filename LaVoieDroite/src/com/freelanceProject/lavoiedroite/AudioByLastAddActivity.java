package com.freelanceProject.lavoiedroite;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.freelanceProject.lavoiedroite.Adapters.LastAddAdapter;
import com.freelanceProject.lavoiedroite.beans.CoursAudio;
import com.freelanceProject.lavoiedroite.ws.WSHelper;
import com.freelanceProject.lavoiedroite.ws.WSHelperListener;

public class AudioByLastAddActivity extends Activity implements
		WSHelperListener {
	ConnectivityManager cManager;
	ListView lstViewLastCours;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.audiobylastadd);
		lstViewLastCours = (ListView) findViewById(R.id.listViewLastCours);
		cManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		WSHelper.getInstance().addWSHelperListener(this);
		WSHelper.getInstance().getLastAddedCours(cManager, this);
	}

	@Override
	public void onAuthorsLoaded(List<String[]> Auteurs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorLoadingAuthors(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLastAddLoaded(final List<CoursAudio> lastCours) {
		Log.i("Cours :", "" + lastCours.size());
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				lstViewLastCours.setAdapter(new LastAddAdapter(
						AudioByLastAddActivity.this, lastCours));
				lstViewLastCours
						.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> arg0,
									View arg1, int position, long arg3) {
								// TODO Auto-generated method stub
								((TextView) findViewById(R.id.coursIntervenant))
										.setText(lastCours.get(position)
												.getIntervenant());
								((TextView) findViewById(R.id.coursTitle))
										.setText(lastCours.get(position)
												.getTitle());
							}
						});
			}
		});
	}

	@Override
	public void onErrorLoadingCours(String string) {
		// TODO Auto-generated method stub

	}

}
