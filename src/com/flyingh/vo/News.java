package com.flyingh.vo;

public class News {
	private int id;
	private String title;
	private int viewCount;

	public News() {
		super();
	}

	public News(int id, String title, int viewCount) {
		super();
		this.id = id;
		this.title = title;
		this.viewCount = viewCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
}
