package com.freelanceProject.lavoiedroite.beans;

public class CoursParAuthors {
	
	public String nbre;
	public String text;
	public int play;
	public int msg;
	public int download;
	
	public CoursParAuthors(){
		super();
	}

	public CoursParAuthors(String nbre, String text, int play, int msg,int download) {
		super();
		
		this.nbre = nbre;
		this.text = text;
		this.play=play;
		this.msg=msg;
		this.download=download;
	}

}
