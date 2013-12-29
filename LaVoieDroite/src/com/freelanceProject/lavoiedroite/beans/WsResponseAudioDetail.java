package com.freelanceProject.lavoiedroite.beans;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class WsResponseAudioDetail {

	@SerializedName("Results")
	private List<AudioElement> listAudio;

	public List<AudioElement> getListAudio() {
		if (listAudio == null)
			return new ArrayList<AudioElement>();
		else
			return listAudio;
	}

	public void setListAudio(List<AudioElement> listAudio) {
		this.listAudio = listAudio;
	}

}
