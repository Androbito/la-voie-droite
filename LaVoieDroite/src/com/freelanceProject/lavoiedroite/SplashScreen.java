package com.freelanceProject.lavoiedroite;


import com.freelanceProject.lavoiedroite.SplashLoading.LoadingTaskFinishedListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.ProgressBar;

public class SplashScreen extends Activity implements LoadingTaskFinishedListener {
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Show the splash screen
        setContentView(R.layout.activity_splash_screen);
        
        // Find the progress bar
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);
        
        // Start your loading
        new SplashLoading(progressBar, this).execute("splashscreen"); // Pass in whatever you need a url is just an example we don't use it in this tutorial
    }
     @Override
    public void onTaskFinished() {
        completeSplash();
    }
 
    private void completeSplash(){
        startApp();
        finish(); // Don't forget to finish this Splash Activity so the user can't return to it!
    }
 
    private void startApp() {
        Intent intent = new Intent(SplashScreen.this,Menu.class);
        startActivity(intent);
    }
}