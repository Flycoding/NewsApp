package com.flyingh.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import com.flyingh.vo.News;

public interface NewsService {

	List<News> parse(InputStream is) throws XmlPullParserException, IOException;

}
