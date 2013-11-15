package com.freelanceProject.lavoiedroite.ws;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xmlpull.v1.XmlPullParserException;

import com.freelanceProject.lavoiedroite.beans.WsResponseAuthor;
import com.freelanceProject.lavoiedroite.web.WebException;
import com.freelanceProject.lavoiedroite.web.WebListener;
import com.freelanceProject.lavoiedroite.web.WebThread;
import com.google.gson.Gson;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.ConnectivityManager;
import android.util.Log;

@SuppressLint("SimpleDateFormat")
public class WSHelper {
	private static WSHelper instance;
	private Gson gson = new Gson();

	public static WSHelper getInstance() {
		if (instance == null) {
			instance = new WSHelper();
		}
		return instance;
	}

	private Set<WSHelperListener> wsHelperListeners;

	private WSHelper() {
		wsHelperListeners = new HashSet<WSHelperListener>();
	}

	public void getChannels(ConnectivityManager manager, final Activity context) {

		WebThread wt = new WebThread("", WebThread.METHOD_GET, manager,
				WebThread.ENCODING_UTF_8, false);
		wt.setListener(new WebListener() {
			public void onFinish(String url, String resultat) {
				onFinishGetAuteurs(resultat, context);
			}

			private void onFinishGetAuteurs(String resultat, Activity context) {
				// TODO Auto-generated method stub
				WsResponseAuthor Auteurs = gson.fromJson(resultat,
						WsResponseAuthor.class);
				for (WSHelperListener wsHelperListener : wsHelperListeners)
					wsHelperListener.onAuthorsLoaded(Auteurs.authors);

			}

			public void onError(WebException error) {
				onErrorGetAuteurs(error, context);
			}

			private void onErrorGetAuteurs(WebException error, Activity context) {
				// TODO Auto-generated method stub
				for (WSHelperListener wsHelperListener : wsHelperListeners)
					wsHelperListener.onErrorLoadingAuthors(error.toString());
			}

			@Override
			public void onFinishWithParams(String url,
					Map<String, String> params, String resultat) {
				// TODO Auto-generated method stub

			}
		});
		wt.start();
	}
}
