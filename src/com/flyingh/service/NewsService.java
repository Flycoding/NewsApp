package com.flyingh.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import com.flyingh.vo.News;

public interface NewsService {

	List<News> parseXML(InputStream is) throws XmlPullParserException, IOException;

	List<News> parseJSON(InputStream is) throws IOException, JSONException;

}
