package com.freelanceProject.lavoiedroite.ws;

import java.util.List;

import com.freelanceProject.lavoiedroite.beans.WsResponseAudioDetail;
import com.freelanceProject.lavoiedroite.beans.WsResponseAudioList;
import com.freelanceProject.lavoiedroite.beans.WsResponseEvents;
import com.freelanceProject.lavoiedroite.beans.WsResponseTheme;
import com.freelanceProject.lavoiedroite.beans.WsResponseVideo;

public interface WSHelperListener {

	void onAuthorsLoaded(List<String[]> Auteurs);

	void onErrorLoadingAuthors(String string);

	void onAudioListLoaded(WsResponseAudioList wsResponseAudioList);

	void onErrorLoadingCours(String string);

	void onThemesLoaded(WsResponseTheme wsResponseTheme);

	void onErrorLoadingThemes(String string);

	void onDetailItemLoaded(WsResponseAudioDetail wsResponseAudioDetail);

	void onErrorLoadingItemDetail(String string);

	void onVideoLoaded(WsResponseVideo wsResponseVideo);

	void onErrorLoadingVideo(String string);

	void onEventsLoaded(WsResponseEvents wsResponseEvents);

	void onErrorLoadingEvents(String string);
}
