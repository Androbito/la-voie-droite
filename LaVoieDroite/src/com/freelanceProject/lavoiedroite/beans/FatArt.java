package com.freelanceProject.lavoiedroite.beans;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class FatArt {
	@SerializedName("nid")
	private int nid;
	@SerializedName("type")
	private String type;
	@SerializedName("dateCreation")
	private String dateCreation;
	@SerializedName("title")
	private String title;
	@SerializedName("html")
	private String html;
	@SerializedName("ContentType")
	private String contentType;
	@SerializedName("visites")
	private String visites;
	@SerializedName("translateur_type")
	private String translateur_type;
	@SerializedName("pdf")
	private String pdf;
	@SerializedName("auteur")
	private String auteur;
	@SerializedName("source")
	private String source;
	@SerializedName("categorie")
	private Object categories;

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getVisites() {
		return visites;
	}

	public void setVisites(String visites) {
		this.visites = visites;
	}

	public String getTranslateur_type() {
		return translateur_type;
	}

	public void setTranslateur_type(String translateur_type) {
		this.translateur_type = translateur_type;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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

}
