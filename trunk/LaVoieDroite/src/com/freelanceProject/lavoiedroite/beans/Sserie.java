package com.freelanceProject.lavoiedroite.beans;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class Sserie {
	@SerializedName("nid")
	private int nid;
	@SerializedName("dateCreation")
	private String dateCreation;
	@SerializedName("title")
	private String title;
	@SerializedName("categorie")
	private Object categories;
	@SerializedName("intervenant")
	private String intervenant;
	@SerializedName("visites")
	private String visites;
	@SerializedName("audio_count")
	private int audio_count;
	@SerializedName("body")
	private String body;

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategories() {
		String categorie = "";
		if (categories != null)
			if (categories instanceof String)
				categorie = (String) categories;
			else if (categories instanceof ArrayList
					&& ((ArrayList<String>) categories) != null)
				for (String s : ((ArrayList<String>) categories))

					categorie = categorie + s + ", ";
		return categorie;
	}

	public void setCategories(Object categories) {
		this.categories = categories;
	}

	public String getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	public String getVisites() {
		return visites;
	}

	public void setVisites(String visites) {
		this.visites = visites;
	}

	public int getAudio_count() {
		return audio_count;
	}

	public void setAudio_count(int audio_count) {
		this.audio_count = audio_count;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
