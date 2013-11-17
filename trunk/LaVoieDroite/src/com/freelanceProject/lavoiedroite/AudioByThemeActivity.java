package com.freelanceProject.lavoiedroite;

import java.util.List;

import com.freelanceProject.lavoiedroite.ws.WSHelper;
import com.freelanceProject.lavoiedroite.ws.WSHelperListener;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.TextView;

public class AudioByThemeActivity extends Activity implements WSHelperListener {
	ConnectivityManager cManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.audiobytheme);
		cManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		WSHelper.getInstance().addWSHelperListener(this);
		WSHelper.getInstance().getAuteurs(cManager, this);
	}

	@Override
	public void onAuthorsLoaded(List<String[]> auteurs) {
		// TODO Auto-generated method stub
		((TextView) findViewById(R.id.textView1)).setText("" + auteurs.size());
	}

	@Override
	public void onErrorLoadingAuthors(String string) {
		// TODO Auto-generated method stub

	}

}
