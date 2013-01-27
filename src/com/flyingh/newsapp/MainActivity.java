package com.flyingh.newsapp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.flyingh.service.NewsService;
import com.flyingh.service.impl.NewsServiceImpl;
import com.flyingh.vo.News;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	private NewsService newsService = new NewsServiceImpl();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().build());
		}
		ListView listView = (ListView) findViewById(R.id.listView);
		try {
			listView.setAdapter(getAdapter());
		} catch (Exception e) {
			Log.e(TAG, e.toString());
			Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
		}
	}

	private SimpleAdapter getAdapter() throws MalformedURLException, IOException, XmlPullParserException, JSONException {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		List<News> news = getLastestJSONNews();
		for (News item : news) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", item.getId());
			map.put("title", item.getTitle());
			map.put("viewCount", item.getViewCount());
			data.add(map);
		}
		return new SimpleAdapter(getApplicationContext(), data, R.layout.item, new String[] { "id", "title", "viewCount" }, new int[] { R.id.id,
				R.id.title, R.id.viewCount });
	}

	private List<News> getLastestJSONNews() throws MalformedURLException, IOException, JSONException {
		return newsService.parseJSON(new URL("http://10.1.79.29:8080/News/NewsServlet?format=json").openStream());
	}

	@SuppressWarnings("unused")
	private List<News> getLastestNews() throws MalformedURLException, IOException, XmlPullParserException {
		return newsService.parseXML(new URL("http://10.1.79.29:8080/News/NewsServlet").openStream());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
