package com.freelanceProject.lavoiedroite.ws;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.ConnectivityManager;
import android.util.Log;

import com.freelanceProject.lavoiedroite.beans.WsResponseAudioDetail;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioList;
import com.freelanceProject.lavoiedroite.beans.WsResponseAuthor;
import com.freelanceProject.lavoiedroite.beans.WsResponseTheme;
import com.freelanceProject.lavoiedroite.web.WebException;
import com.freelanceProject.lavoiedroite.web.WebListener;
import com.freelanceProject.lavoiedroite.web.WebThread;
import com.google.gson.Gson;

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

	public void getAuteurs(ConnectivityManager manager, final Activity context) {

		WebThread wt = new WebThread(URLs.authors, WebThread.METHOD_GET,
				manager, WebThread.ENCODING_UTF_8, false);
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

	public void getItemByFilter(String url, ConnectivityManager manager,
			final Activity context) {
		WebThread wt = new WebThread(url, WebThread.METHOD_GET, manager,
				WebThread.ENCODING_UTF_8, false);
		wt.setListener(new WebListener() {

			@Override
			public void onFinishWithParams(String url,
					Map<String, String> params, String resultat) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFinish(String url, String resultat) {
				WsResponseAudioList lastAdd = gson.fromJson(resultat,
						WsResponseAudioList.class);
				Log.i("url", url);
				Log.i("getAudioCours", resultat);
				for (WSHelperListener wsHelperListener : wsHelperListeners)
					wsHelperListener.onAudioListLoaded(lastAdd
							.getListCoursAudio());
			}

			@Override
			public void onError(WebException error) {
				for (WSHelperListener wsHelperListener : wsHelperListeners)
					wsHelperListener.onErrorLoadingCours(error.toString());
			}
		});
		wt.start();
	}

	public void getThemes(ConnectivityManager manager, final Activity context) {
		WebThread wt = new WebThread(URLs.themes, WebThread.METHOD_GET,
				manager, WebThread.ENCODING_UTF_8, false);
		wt.setListener(new WebListener() {

			@Override
			public void onFinishWithParams(String url,
					Map<String, String> params, String resultat) {

			}

			@Override
			public void onFinish(String url, String resultat) {
				WsResponseTheme wsResponseTheme = gson.fromJson(resultat,
						WsResponseTheme.class);
				for (WSHelperListener wsHelperListener : wsHelperListeners)
					wsHelperListener.onThemesLoaded(wsResponseTheme);
			}

			@Override
			public void onError(WebException error) {
				for (WSHelperListener wsHelperListener : wsHelperListeners)
					wsHelperListener.onErrorLoadingThemes(error.toString());
			}
		});
		wt.start();
	}

	public void getItemDetails(ConnectivityManager manager,
			final Activity context, String idAudio) {
		WebThread wt = new WebThread(URLs.audio + idAudio,
				WebThread.METHOD_GET, manager, WebThread.ENCODING_UTF_8, false);
		wt.setListener(new WebListener() {

			@Override
			public void onFinishWithParams(String url,
					Map<String, String> params, String resultat) {

			}

			@Override
			public void onFinish(String url, String resultat) {
				WsResponseAudioDetail wsResponseAudioDetail = gson.fromJson(
						resultat, WsResponseAudioDetail.class);
				for (WSHelperListener wsHelperListener : wsHelperListeners)
					wsHelperListener.onDetailItemLoaded(wsResponseAudioDetail);
			}

			@Override
			public void onError(WebException error) {
				for (WSHelperListener wsHelperListener : wsHelperListeners)
					wsHelperListener.onErrorLoadingItemDetail(error.toString());
			}
		});
		wt.start();
	}

	public void addWSHelperListener(WSHelperListener listener) {
		wsHelperListeners.add(listener);
	}

	public void removeWSHelperListener(WSHelperListener listener) {
		wsHelperListeners.remove(listener);
	}
}
