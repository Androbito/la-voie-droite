package com.freelanceProject.lavoiedroite.web;

import java.util.Map;


/**
 * interface webListener 
 *
 */
public interface WebListener {

	/**
	 * what to do at the end of WebThread
	 * @param resultat
	 *          the result of the web request
	 * @param url
	 * 			the URL to identify each web request
	 */         
	public abstract void onFinish(String url ,String resultat);
	public abstract void onFinishWithParams(String url ,Map<String, String> params,String resultat);
	/**
	 * what to do if there was an exception
	 * @param error
	 */
	public abstract void onError(WebException error);
}
