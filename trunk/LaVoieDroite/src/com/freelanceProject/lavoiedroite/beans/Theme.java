package com.freelanceProject.lavoiedroite.beans;

import com.google.gson.annotations.SerializedName;

public class Theme {
	@SerializedName("id")
	private int idTheme;
	@SerializedName("name")
	private String nameTheme;
	@SerializedName("descreption")
	private String descriptionTheme;

	public int getIdTheme() {
		return idTheme;
	}

	public void setIdTheme(int idTheme) {
		this.idTheme = idTheme;
	}

	public String getNameTheme() {
		return nameTheme;
	}

	public void setNameTheme(String nameTheme) {
		this.nameTheme = nameTheme;
	}

	public String getDescriptionTheme() {
		return descriptionTheme;
	}

	public void setDescriptionTheme(String descriptionTheme) {
		this.descriptionTheme = descriptionTheme;
	}

}
