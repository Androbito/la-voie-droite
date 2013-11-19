package com.freelanceProject.lavoiedroite.ws;

import java.util.List;

import com.freelanceProject.lavoiedroite.beans.CoursAudio;

public interface WSHelperListener {

	void onAuthorsLoaded(List<String[]> Auteurs);

	void onErrorLoadingAuthors(String string);

	void onLastAddLoaded(List<CoursAudio> Cours);

	void onErrorLoadingCours(String string);
}
