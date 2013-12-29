package com.freelanceProject.lavoiedroite.beans;

import com.google.gson.annotations.SerializedName;

public class AudioElement {
	@SerializedName("description")
	private String description;
	@SerializedName("url")
	private String url;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
