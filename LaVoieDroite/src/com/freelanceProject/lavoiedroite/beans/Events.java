package com.freelanceProject.lavoiedroite.beans;

public class Events {
	
	public String title_event;
	public int icon_sms;
	public int icon_position;
	public int icon_time;
	public int img;
	public String time;
	public String sms;
	public String position;

	public Events(){
		super();
	}

	public Events(int icon_sms,int img, String title_event, int icon_position,String time,String sms,String position,int icon_time) {
		super();
		
		this.title_event = title_event;
		this.icon_sms = icon_sms;
		this.icon_position=icon_position;
		this.icon_time=icon_time;
		this.time=time;
		this.sms=sms;
		this.position=position;
		this.img=img;
	}

}
