package com.example.soulxpress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity{

	private static String TAG = Splash.class.getName();
	private static long SLEEP_Time = 5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.splash);
		
		IntentLauncher launch = new IntentLauncher();
		launch.start();
	}
	
	private class IntentLauncher extends Thread {
		public void run(){
			try {
				Thread.sleep(SLEEP_Time*1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Intent intent = new Intent(Splash.this, MainActivity.class);
			Splash.this.startActivity(intent);
			Splash.this.finish();
		}
	}
}
