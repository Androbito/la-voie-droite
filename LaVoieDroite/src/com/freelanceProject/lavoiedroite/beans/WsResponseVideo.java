package com.freelanceProject.lavoiedroite.beans;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class WsResponseVideo {
	@SerializedName("Results")
	private List<VideoCours> listVideos;
	@SerializedName("nobrePage")
	private Pagination pagination;

	public List<VideoCours> getListVideos() {
		if (listVideos == null)
			return new ArrayList<VideoCours>();
		else
			return listVideos;
	}

	public void setListVideos(List<VideoCours> listVideos) {
		this.listVideos = listVideos;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

}
