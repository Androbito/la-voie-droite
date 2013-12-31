package com.freelanceProject.lavoiedroite.beans;

import com.google.gson.annotations.SerializedName;

public class VideoCours {
	@SerializedName("title")
	private String title;
	@SerializedName("url")
	private String url;
	@SerializedName("name")
	private String name;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
