package com.freelanceProject.lavoiedroite.beans;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CoursAudio {
	@SerializedName("nid")
	private int nid;
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
	@SerializedName("dateCreation")
	private String dateCreation;

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
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

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	public int getVisites() {
		if (visites == null)
			return 0;
		else
			return Integer.parseInt(visites);
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

}
