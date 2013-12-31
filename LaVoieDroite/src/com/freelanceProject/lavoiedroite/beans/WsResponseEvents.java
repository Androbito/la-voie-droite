package com.freelanceProject.lavoiedroite.beans;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class WsResponseEvents {
	@SerializedName("Results")
	public ArrayList<Evenement> listEvents;
	@SerializedName("nobrePage")
	public Pagination pagination;

}
