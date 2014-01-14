package com.freelanceProject.lavoiedroite;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class FatawaArticlesActivity extends TabActivity {
	TabHost tabHost;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fatawa_articles);
		tabHost = getTabHost();
		if (getIntent().getStringExtra("type").equals("fatwa"))
			((TextView) findViewById(R.id.title)).setText("Fatwas");
		else
			((TextView) findViewById(R.id.title)).setText("Articles");
		setupTab(new TextView(this), "Par\nTheme", FAThemeActivity.class);
		setupTab(new TextView(this), "Derniers\nAjout", FALastAddActivity.class);
		tabHost.setCurrentTab(0);
		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void setupTab(final View view, final String tag, final Class<?> cls) {
		View tabview = createTabView(tabHost.getContext(), tag);
		Intent tabIntent = new Intent(this, cls);
		tabIntent.putExtra("type", getIntent().getStringExtra("type"));
		tabIntent.putExtra("tid", getIntent().getStringExtra("tid"));
		TabSpec setContent = tabHost.newTabSpec(tag).setIndicator(tabview)
				.setContent(tabIntent);
		tabHost.addTab(setContent);

	}

	private static View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context)
				.inflate(R.layout.tabs_bg, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		return view;
	}
}
