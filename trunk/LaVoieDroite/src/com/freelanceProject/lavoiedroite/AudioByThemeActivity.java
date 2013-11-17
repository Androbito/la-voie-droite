package com.freelanceProject.lavoiedroite;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.freelanceProject.lavoiedroite.ws.WSHelper;
import com.freelanceProject.lavoiedroite.ws.WSHelperListener;

public class AudioByThemeActivity extends Activity implements WSHelperListener {
	ConnectivityManager cManager;
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.audiobytheme);
		textView = (TextView) findViewById(R.id.textView1);
		cManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		WSHelper.getInstance().addWSHelperListener(this);
		WSHelper.getInstance().getAuteurs(cManager, this);
	}

	@Override
	public void onAuthorsLoaded(final List<String[]> auteurs) {
		// TODO Auto-generated method stub
		Log.i("Taille de la liste des auteurs", "" + auteurs.size());
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				textView.setText("" + auteurs.size());
				
			}
		});
	}

	@Override
	public void onErrorLoadingAuthors(String string) {
		// TODO Auto-generated method stub

	}

}
