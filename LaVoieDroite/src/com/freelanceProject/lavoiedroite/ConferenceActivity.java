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

@SuppressWarnings("deprecation")
public class ConferenceActivity extends TabActivity {
	TabHost tabHost;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conferences);
		tabHost = getTabHost();
		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		setupTab(new TextView(this), "Par\nTheme", ThemeActivity.class);
		setupTab(new TextView(this), "Par\nIntervenants",
				IntervenantActivity.class);
		setupTab(new TextView(this), "Derniers\nAjouts", LastAddActivity.class);
		tabHost.setCurrentTab(0);
	}

	private void setupTab(final View view, final String tag, final Class<?> cls) {
		View tabview = createTabView(tabHost.getContext(), tag);
		Intent tabIntent = new Intent(this, cls);
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
