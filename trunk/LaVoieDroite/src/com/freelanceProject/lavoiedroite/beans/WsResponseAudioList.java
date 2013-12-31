package com.freelanceProject.lavoiedroite.beans;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class WsResponseAudioList {
	@SerializedName("Results")
	private List<CoursAudio> listCoursAudio;
	@SerializedName("interInfoBar")
	private InfoBar interInfoBar;

	public InfoBar getInterInfoBar() {
		return interInfoBar;
	}

	public void setInterInfoBar(InfoBar interInfoBar) {
		this.interInfoBar = interInfoBar;
	}

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
