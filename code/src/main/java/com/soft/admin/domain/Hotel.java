package com.soft.admin.domain;

import com.soft.common.domain.BaseDomain;
import com.soft.common.util.StringUtil;
import com.soft.common.util.Transcode;

@SuppressWarnings("serial")
public class Hotel extends BaseDomain {
	private int hotel_id; // 
	private String hotel_title; // 
	private String hotel_pic; // 
	private String hotel_content; // 
	private String hotel_address; // 
	private String hotel_phone; // 
	private String hotel_date; // 

	private double hotel_price1; // 
	private double hotel_price2; // 
	private int type; 
	private String ids;
	private String random;

	public String getHotel_contentShow(){
		if (!StringUtil.isEmptyString(hotel_content)) {
			return Transcode.htmlDiscode(hotel_content);
		}
		return hotel_content;
	}
	
	public void setHotel_id(int hotel_id){
		this.hotel_id=hotel_id;
	}

	public int getHotel_id(){
		return hotel_id;
	}

	public void setHotel_title(String hotel_title){
		this.hotel_title=hotel_title;
	}

	public String getHotel_title(){
		return hotel_title;
	}

	public void setHotel_content(String hotel_content){
		this.hotel_content=hotel_content;
	}

	public String getHotel_content(){
		return hotel_content;
	}

	public void setHotel_address(String hotel_address){
		this.hotel_address=hotel_address;
	}

	public String getHotel_address(){
		return hotel_address;
	}

	public void setHotel_date(String hotel_date){
		this.hotel_date=hotel_date;
	}

	public String getHotel_date(){
		return hotel_date;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getRandom() {
		return random;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getHotel_pic() {
		return hotel_pic;
	}

	public void setHotel_pic(String hotel_pic) {
		this.hotel_pic = hotel_pic;
	}

	public double getHotel_price1() {
		return hotel_price1;
	}

	public void setHotel_price1(double hotel_price1) {
		this.hotel_price1 = hotel_price1;
	}

	public double getHotel_price2() {
		return hotel_price2;
	}

	public void setHotel_price2(double hotel_price2) {
		this.hotel_price2 = hotel_price2;
	}

	public String getHotel_phone() {
		return hotel_phone;
	}

	public void setHotel_phone(String hotel_phone) {
		this.hotel_phone = hotel_phone;
	}

}
