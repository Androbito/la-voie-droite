package com.freelanceProject.lavoiedroite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import com.freelanceProject.lavoiedroite.beans.Categorie;
import com.freelanceProject.lavoiedroite.beans.CoursAudio;
import com.freelanceProject.lavoiedroite.beans.Theme;
import com.freelanceProject.lavoiedroite.beans.WsResponseTheme;
import com.freelanceProject.lavoiedroite.ws.URLs;
import com.freelanceProject.lavoiedroite.ws.WSHelper;
import com.freelanceProject.lavoiedroite.ws.WSHelperListener;

public class ThemeActivity extends Activity implements WSHelperListener {
	ConnectivityManager cManager;
	ExpandableListView expandableList;
	private List<HashMap<String, String>> listCategories = new ArrayList<HashMap<String, String>>(
			0);
	private List<List<HashMap<String, String>>> listThemes = new ArrayList<List<HashMap<String, String>>>(
			0);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intervenants);
		expandableList = (ExpandableListView) findViewById(R.id.list_theme);
		SimpleExpandableListAdapter expListAdapter = new SimpleExpandableListAdapter(
				this, listCategories, R.layout.categorie_group,
				new String[] { "categorieName" },
				new int[] { R.id.txt_categorie }, listThemes,
				R.layout.theme_row, new String[] { "themeName", },
				new int[] { R.id.txt_theme });
		expandableList.setAdapter(expListAdapter);
		cManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		WSHelper.getInstance().addWSHelperListener(this);
		WSHelper.getInstance().getThemes(cManager, this);

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
	public void onAudioListLoaded(List<CoursAudio> Cours) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorLoadingCours(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onThemesLoaded(WsResponseTheme wsResponseTheme) {
		Log.i("Nbre de categories", "" + wsResponseTheme.getCategories().size());
		for (int i = 0; i < wsResponseTheme.getCategories().size(); i++)
			Log.i(wsResponseTheme.getCategories().get(i).getDetailCategoie(),
					""
							+ wsResponseTheme.getCategories().get(i)
									.getThemes().size());
		listCategories.addAll(createGroupList(wsResponseTheme.getCategories()));
		listThemes.addAll(createChildList(wsResponseTheme.getCategories()));
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				((SimpleExpandableListAdapter) expandableList
						.getExpandableListAdapter()).notifyDataSetInvalidated();
				expandableList
						.setOnChildClickListener(new OnChildClickListener() {

							@Override
							public boolean onChildClick(
									ExpandableListView parent, View v,
									int groupPosition, int childPosition,
									long id) {
								// TODO Auto-generated method stub
								Intent bythemes = new Intent(
										getApplicationContext(),
										AudioByFilterActivity.class);
								bythemes.putExtra(
										"url",
										URLs.bythemes
												+ listThemes.get(groupPosition)
														.get(childPosition)
														.get("themeId")
												+ "&tid="
												+ getIntent().getStringExtra(
														"tid")
												+ "&page=0&npage=25");
								// Toast.makeText(
								// ThemeActivity.this,
								// listCategories.get(groupPosition).get(
								// "categorieName")
								// + listThemes.get(groupPosition)
								// .get(childPosition)
								// .get("themeId"),
								// Toast.LENGTH_SHORT).show();
								startActivity(bythemes);
								return true;
							}
						});
			}
		});
	}

	private List<HashMap<String, String>> createGroupList(
			List<Categorie> categories) {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>(
				0);
		for (Categorie c : categories) {
			HashMap<String, String> m = new HashMap<String, String>();
			m.put("categorieName", c.getDetailCategoie());
			result.add(m);
		}
		return result;
	}

	private List<ArrayList<HashMap<String, String>>> createChildList(
			List<Categorie> categories) {
		ArrayList<ArrayList<HashMap<String, String>>> result = new ArrayList<ArrayList<HashMap<String, String>>>();
		for (int i = 0; i < categories.size(); ++i) {
			Categorie c = categories.get(i);
			ArrayList<HashMap<String, String>> secList = new ArrayList<HashMap<String, String>>();
			for (int j = 0; j < c.getThemes().size(); j++) {
				Theme t = c.getThemes().get(j);
				HashMap<String, String> child = new HashMap<String, String>();
				child.put("themeName", t.getNameTheme());
				child.put("themeId", "" + t.getIdTheme());
				secList.add(child);
			}
			result.add(secList);
		}
		return result;
	}

	@Override
	public void onErrorLoadingThemes(String string) {
		// TODO Auto-generated method stub

	}

}
