package com.freelanceProject.lavoiedroite.event;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import com.freelanceProject.lavoiedroite.R;
import com.freelanceProject.lavoiedroite.RechercheCoursAudioParIntervenant;
import com.freelanceProject.lavoiedroite.Adapters.CoursParThemeAdapter;
import com.freelanceProject.lavoiedroite.beans.CoursParTheme;
import com.freelanceProject.lavoiedroite.event.GroupEntity.GroupItemEntity;

public class MainActivity extends Activity {

	private ExpandableListView mExpandableListView;
	private List<GroupEntity> mGroupCollection;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		
		ImageView back=(ImageView) findViewById(R.id.back);

		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent menu = new Intent(MainActivity.this,
						com.freelanceProject.lavoiedroite.Menu.class);
				startActivity(menu);				
			}
		});
		
		prepareResource();
		initPage();
	}

	private void prepareResource() {

		mGroupCollection = new ArrayList<GroupEntity>();

		for (int i =0; i < 6; ++i) {
			GroupEntity ge = new GroupEntity();

			for (int j =0; j <1; j++) {
				GroupItemEntity gi = ge.new GroupItemEntity();
				ge.GroupItemCollection.add(gi);

			}

			mGroupCollection.add(ge);
		}

	}

	private void initPage() {
		mExpandableListView = (ExpandableListView) findViewById(R.id.list_theme);
		ExpandableListAdapter adapter = new ExpandableListAdapter(this,
				mExpandableListView, mGroupCollection);

		mExpandableListView.setAdapter(adapter);
	}
}