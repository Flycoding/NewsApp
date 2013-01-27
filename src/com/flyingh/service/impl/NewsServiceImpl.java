package com.flyingh.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.flyingh.service.NewsService;
import com.flyingh.vo.News;

public class NewsServiceImpl implements NewsService {

	@Override
	public List<News> parseXML(InputStream is) throws XmlPullParserException, IOException {
		List<News> news = null;
		News item = null;
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "utf-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				news = new ArrayList<News>();
				break;
			case XmlPullParser.START_TAG:
				if ("new".equals(parser.getName())) {
					item = new News();
					item.setId(Integer.valueOf(parser.getAttributeValue(0)));
				} else if ("title".equals(parser.getName())) {
					item.setTitle(parser.nextText());
				} else if ("viewCount".equals(parser.getName())) {
					item.setViewCount(Integer.valueOf(parser.nextText()));
				}
				break;
			case XmlPullParser.END_TAG:
				if ("new".equals(parser.getName())) {
					news.add(item);
				}
				break;
			default:
				break;
			}
			eventType = parser.next();
		}
		return news;
	}

	@Override
	public List<News> parseJSON(InputStream is) throws IOException, JSONException {
		List<News> news = new ArrayList<News>();
		String json = toString(is);
		JSONArray jsonArray = new JSONArray(json);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			int id = jsonObject.getInt("id");
			String title = jsonObject.getString("title");
			int viewCount = jsonObject.getInt("viewCount");
			news.add(new News(id, title, viewCount));
		}
		return news;
	}

	private String toString(InputStream is) throws IOException {
		byte[] buf = new byte[1024];
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		int len = -1;
		while ((len = is.read(buf)) != -1) {
			os.write(buf, 0, len);
		}
		return new String(os.toByteArray());
	}
}
