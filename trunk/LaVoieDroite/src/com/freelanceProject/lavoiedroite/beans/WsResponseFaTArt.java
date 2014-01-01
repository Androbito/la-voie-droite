package com.freelanceProject.lavoiedroite.beans;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class WsResponseFaTArt {
	@SerializedName("Results")
	private ArrayList<FatArt> listFatArts;
	@SerializedName("nobrePage")
	private Pagination pagination;

	public ArrayList<FatArt> getListFatArts() {
		if (listFatArts == null)
			return new ArrayList<FatArt>(0);
		else
			return listFatArts;
	}

	public void setListFatArts(ArrayList<FatArt> listFatwas) {
		this.listFatArts = listFatwas;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

}
