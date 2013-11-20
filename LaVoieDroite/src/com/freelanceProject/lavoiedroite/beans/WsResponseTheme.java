package com.freelanceProject.lavoiedroite.beans;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class WsResponseTheme {
	@SerializedName("themes")
	private Themes themes;
	@SerializedName("parents")
	private List<Categorie> categories;

	public Themes getThemes() {
		return themes;
	}

	public void setThemes(Themes themes) {
		this.themes = themes;
	}

	public List<Categorie> getCategories() {
		if (categories == null)
			return new ArrayList<Categorie>();
		else
			return categories;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}

}
