package com.freelanceProject.lavoiedroite.ws;

import java.util.List;

import com.freelanceProject.lavoiedroite.beans.CoursAudio;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioDetail;
import com.freelanceProject.lavoiedroite.beans.WsResponseTheme;

public interface WSHelperListener {

	void onAuthorsLoaded(List<String[]> Auteurs);

	void onErrorLoadingAuthors(String string);

	void onAudioListLoaded(List<CoursAudio> Cours);

	void onErrorLoadingCours(String string);

	void onThemesLoaded(WsResponseTheme wsResponseTheme);

	void onErrorLoadingThemes(String string);

	void onDetailItemLoaded(WsResponseAudioDetail wsResponseAudioDetail);

	void onErrorLoadingItemDetail(String string);
}
