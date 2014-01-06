package com.freelanceProject.lavoiedroite.beans;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class WsResponseSouSeries {
	
	@SerializedName("Results")
	private ArrayList<Sserie> listSseries;
	@SerializedName("nobrePage")
	private Pagination pagination;

	public ArrayList<Sserie> getListSseries() {
		return listSseries;
	}

	public void setListSseries(ArrayList<Sserie> listSseries) {
		this.listSseries = listSseries;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
}
