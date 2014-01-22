package com.freelanceProject.lavoiedroite.beans;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Categorie {
	@SerializedName("idParent")
	private int idCategorie;
	@SerializedName("detail")
	private String detailCategoie;
	@SerializedName("icone")
	private String icone;
	@SerializedName("themes")
	private List<Theme> themes;

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public List<Theme> getThemes() {
		if (themes == null)
			return new ArrayList<Theme>();
		else
			return themes;
	}

	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getDetailCategoie() {
		return detailCategoie;
	}

	public void setDetailCategoie(String detailCategoie) {
		this.detailCategoie = detailCategoie;
	}

}
