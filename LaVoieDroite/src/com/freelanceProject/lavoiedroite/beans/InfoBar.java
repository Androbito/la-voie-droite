package com.freelanceProject.lavoiedroite.beans;

import com.google.gson.annotations.SerializedName;

public class InfoBar {
	@SerializedName("cours_audio")
	private int cours_audio;
	@SerializedName("conferences")
	private int conferences;
	@SerializedName("preches")
	private int preches;
	@SerializedName("articles")
	private int articles;

	public InfoBar(int cours_audio, int conferences, int preches, int articles) {
		super();
		this.cours_audio = cours_audio;
		this.conferences = conferences;
		this.preches = preches;
		this.articles = articles;
	}

	public int getCours_audio() {
		return cours_audio;
	}

	public void setCours_audio(int cours_audio) {
		this.cours_audio = cours_audio;
	}

	public int getConferences() {
		return conferences;
	}

	public void setConferences(int conferences) {
		this.conferences = conferences;
	}

	public int getPreches() {
		return preches;
	}

	public void setPreches(int preches) {
		this.preches = preches;
	}

	public int getArticles() {
		return articles;
	}

	public void setArticles(int articles) {
		this.articles = articles;
	}

}
