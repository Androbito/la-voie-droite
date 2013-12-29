package com.freelanceProject.lavoiedroite.beans;

import com.google.gson.annotations.SerializedName;

public class AudioType {
	@SerializedName("value")
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
