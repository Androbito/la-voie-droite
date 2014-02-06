package com.freelanceProject.lavoiedroite;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class StreamingListActivity extends Activity implements OnClickListener {
	private File file;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.streaming_lst);
		((LinearLayout) findViewById(R.id.direct1)).setOnClickListener(this);
		((LinearLayout) findViewById(R.id.direct2)).setOnClickListener(this);
		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.direct1:
			file = new File(Environment.getExternalStorageDirectory()
					.toString() + "/LaVoieDroite_mp3/liveaima.ram");
			break;
		case R.id.direct2:
			file = new File(Environment.getExternalStorageDirectory()
					.toString() + "/LaVoieDroite_mp3/wwwlavoi.ram");
			break;
		}
		if (file.exists()) {
			Intent i = new Intent(getApplicationContext(),
					StreamingActivity.class);
			try {
				i.putExtra("url", readFile(file));
				startActivity(i);
			} catch (IOException e) {
				Toast.makeText(getApplicationContext(),
						"Ce direct n'est pas disponible", Toast.LENGTH_SHORT)
						.show();
			}

		}
	}

	private String readFile(File file) throws IOException {

		StringBuilder fileContents = new StringBuilder((int) file.length());
		Scanner scanner = new Scanner(file);
		String lineSeparator = System.getProperty("line.separator");

		try {
			while (scanner.hasNextLine()) {
				fileContents.append(scanner.nextLine() + lineSeparator);
			}
			return fileContents.toString();
		} finally {
			scanner.close();
		}
	}
}
