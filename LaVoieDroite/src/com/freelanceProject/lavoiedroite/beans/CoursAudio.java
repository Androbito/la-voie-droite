package com.freelanceProject.lavoiedroite.beans;

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
		if (categories instanceof String)
			categorie = (String) categories;
		else
			for (int i = 0; i < ((String[]) categories).length; i++)
				categorie = categorie + ((String[]) categories)[i];
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

}
