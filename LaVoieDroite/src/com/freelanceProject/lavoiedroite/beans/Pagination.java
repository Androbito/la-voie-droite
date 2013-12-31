package com.freelanceProject.lavoiedroite.beans;

import com.google.gson.annotations.SerializedName;

public class Pagination {
	@SerializedName("count")
	private String count;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}
