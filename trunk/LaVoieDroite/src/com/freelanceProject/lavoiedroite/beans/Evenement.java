package com.freelanceProject.lavoiedroite.beans;

import com.google.gson.annotations.SerializedName;

public class Evenement {
	@SerializedName("title")
	private String title;
	@SerializedName("date")
	private String date;
	@SerializedName("intervenant")
	private String intervenant;
	@SerializedName("ville")
	private String ville;
	@SerializedName("details")
	private String details;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
