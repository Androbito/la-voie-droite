package com.freelanceProject.lavoiedroite.ws;

import java.util.List;

import com.freelanceProject.lavoiedroite.beans.Authors;

public interface WSHelperListener {

	void onAuthorsLoaded(List<String[]> Auteurs);

	void onErrorLoadingAuthors(String string);
}
