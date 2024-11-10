package com.soft.admin.domain;

import com.soft.common.domain.BaseDomain;

@SuppressWarnings("serial")
public class House extends BaseDomain {
	private int house_id; // 
	private int hotel_id; // 
	private String house_title; // 
	private double house_price; // 

	private String hotel_title; // 
	private double house_price1; // 
	private double house_price2; // 
	private int house_count; // 
	private String ids;
	private String random;
	public int getHouse_id() {
		return house_id;
	}
	public void setHouse_id(int house_id) {
		this.house_id = house_id;
	}
	public int getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getHouse_title() {
		return house_title;
	}
	public void setHouse_title(String house_title) {
		this.house_title = house_title;
	}
	public double getHouse_price() {
		return house_price;
	}
	public void setHouse_price(double house_price) {
		this.house_price = house_price;
	}
	public String getHotel_title() {
		return hotel_title;
	}
	public void setHotel_title(String hotel_title) {
		this.hotel_title = hotel_title;
	}
	public double getHouse_price1() {
		return house_price1;
	}
	public void setHouse_price1(double house_price1) {
		this.house_price1 = house_price1;
	}
	public double getHouse_price2() {
		return house_price2;
	}
	public void setHouse_price2(double house_price2) {
		this.house_price2 = house_price2;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getRandom() {
		return random;
	}
	public void setRandom(String random) {
		this.random = random;
	}
	public int getHouse_count() {
		return house_count;
	}
	public void setHouse_count(int house_count) {
		this.house_count = house_count;
	}


}
