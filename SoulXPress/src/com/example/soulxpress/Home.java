package com.example.soulxpress;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Home extends ListActivity {

	ListView lView;
	ArrayAdapter lAdapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_layout);
		lView = (ListView) findViewById(android.R.id.list);
		loadList(lView);

	}

	public void loadList(ListView lView){
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy); 
		ServiceHandler sh = new ServiceHandler();

		// Making a request to url and getting response
		String jsonStr = sh.makeServiceCall("http://soulxpresshiphop.com/get_all_news.php", ServiceHandler.GET);

		Log.d("Response: ", "> " + jsonStr);
		ArrayList<String> titles = new ArrayList<String>();;
		if (jsonStr != null) {
			try {
				JSONObject jsonObj = new JSONObject(jsonStr);
				

				// Getting JSON Array node
				JSONArray entries = jsonObj.getJSONArray("entries"); 
				for (int i = 0; i < entries.length(); i++) {
					JSONObject c = entries.getJSONObject(i);
					String id = c.getString("id"); //not used for anything?
					String title= c.getString("title");
					titles.add(title);
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
