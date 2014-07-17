package com.example.soulxpress;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Home extends ListActivity {

	ListView lView;
	TextView tView;
	ArrayAdapter lAdapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_layout);
		lView = (ListView) findViewById(android.R.id.list);
		//tView = (TextView) findViewById(android.R.id.);
		loadList(lView);
		
	
		
	}

	public void loadList(ListView lView){
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy); 
		ServiceHandler sh = new ServiceHandler();

		// Making a request to url and getting response
		String jsonStr = sh.makeServiceCall("http://soulxpresshiphop.com/get_all_news.php", ServiceHandler.GET);
		
		Log.d("Response: ", "> " + jsonStr);
		ArrayList<String> titles = new ArrayList<String>();
		ArrayList<String> body = new ArrayList<String>();
		
		if (jsonStr != null) {
			try {
				JSONObject jsonObj = new JSONObject(jsonStr);
				

				// Getting JSON Array node
				JSONArray entries = jsonObj.getJSONArray("entries"); 
				for (int i = 0; i < entries.length(); i++) {
					JSONObject c = entries.getJSONObject(i);
					String text = c.getString("introtext"); 
					if(text == "null"){
						text = "No text here" + "\n" + "\n";
						
					}
					else {
						text = android.text.Html.fromHtml(text).toString();
					}
					String title= c.getString("title");
					String full = title + "\n" + "\n" + text;
					titles.add(full);
					//body.add(text);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Log.e("ServiceHandler", "Couldn't get any data from the url");
		}
		
		lAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, titles);
		lView.setAdapter(lAdapter);
	}

	
	
	
}
	

