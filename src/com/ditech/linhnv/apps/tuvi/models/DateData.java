package com.ditech.linhnv.apps.tuvi.models;

/**
 * 
 * @author linhnv
 *
 * this data to hold information of dates for dislaying : date and description- famous or nice words
 */
public class DateData {
	private int date;//
	private String description ;
	public DateData() {
		super();
	}
	
	public DateData(int date, String description) {
		super();
		this.date = date;
		this.description = description;
	}

	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
