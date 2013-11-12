package com.freelanceProject.lavoiedroite.beans;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class WsResponseAuthor {

	@SerializedName("authors")
	public ArrayList<String[]> authors;
}
