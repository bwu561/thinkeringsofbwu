package com.example.soulxpress;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TabHost tabHost = getTabHost();
        
        // Tab for Photos
        TabSpec home = tabHost.newTabSpec("Home");
        // setting Title and Icon for the Tab
        home.setIndicator("Home");
        Intent homeIntent = new Intent(this, Home.class);
        home.setContent(homeIntent);
         
        // Tab for Songs
        TabSpec timetable = tabHost.newTabSpec("TimeTable");        
        timetable.setIndicator("Timetable");
        Intent timetableIntent = new Intent(this, TimeTable.class);
        timetable.setContent(timetableIntent);
         
        // Tab for Videos
        TabSpec aboutus = tabHost.newTabSpec("About Us");
        aboutus.setIndicator("About Us");
        Intent aboutIntent = new Intent(this, Aboutus.class);
        aboutus.setContent(aboutIntent);
         
        // Adding all TabSpec to TabHost
        tabHost.addTab(home); // Adding photos tab
        tabHost.addTab(timetable); // Adding songs tab
        tabHost.addTab(aboutus); // Adding videos tab
        
        
		
    }
}



