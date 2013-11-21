package com.freelanceProject.lavoiedroite.beans;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class WsResponseLastAdd {
	@SerializedName("Results")
	private List<CoursAudio> listCoursAudio;

	public List<CoursAudio> getListCoursAudio() {
		if (listCoursAudio == null)
			return new ArrayList<CoursAudio>();
		else
			return listCoursAudio;
	}

	public void setListCoursAudio(List<CoursAudio> listCoursAudio) {
		this.listCoursAudio = listCoursAudio;
	}

}
