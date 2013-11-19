package com.freelanceProject.lavoiedroite;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.freelanceProject.lavoiedroite.Adapters.AuthorsAdapter;
import com.freelanceProject.lavoiedroite.ws.WSHelper;
import com.freelanceProject.lavoiedroite.ws.WSHelperListener;

public class AudioByIntervenantActivity extends Activity implements
		WSHelperListener {
	ConnectivityManager cManager;
	ListView lstViewAuteurs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.audiobytheme);
		lstViewAuteurs = (ListView) findViewById(R.id.listViewAuteurs);
		cManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		WSHelper.getInstance().addWSHelperListener(this);
		WSHelper.getInstance().getAuteurs(cManager, this);
	}

	@Override
	public void onAuthorsLoaded(final List<String[]> auteurs) {
		Log.i("Taille de la liste des auteurs", "" + auteurs.size());
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				lstViewAuteurs.setAdapter(new AuthorsAdapter(
						AudioByIntervenantActivity.this, auteurs));
				lstViewAuteurs
						.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> arg0,
									View arg1, int position, long arg3) {
								Toast.makeText(AudioByIntervenantActivity.this,
										auteurs.get(position)[1],
										Toast.LENGTH_SHORT).show();
							}
		});
			}
		});
	}

	@Override
	public void onErrorLoadingAuthors(String string) {
		// TODO Auto-generated method stub

	}

}
