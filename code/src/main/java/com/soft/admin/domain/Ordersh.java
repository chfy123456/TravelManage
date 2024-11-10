package com.soft.admin.domain;

import com.soft.common.domain.BaseDomain;

@SuppressWarnings("serial")
public class Ordersh extends BaseDomain {
	private int ordersh_id; // 
	private String ordersh_no; // 
	private int user_id; // 
	private String real_name; // 
	private String user_phone; // 
	private int hotel_id; // 
	private String hotel_title; // 
	private int house_id; // 
	private String house_title; // 
	private double house_price; // 
	private int house_count; // 
	private double ordersh_money; //
	private String come_date; //
	private String ordersh_date; // 
	private int ordersh_flag; // 1:待入住 2:已入住 3:已过期

	private int admin_id; // 
	private String ids;
	private String random;
	
	public String getOrdersh_flagDesc(){
		switch (ordersh_flag) {
		case 1:
			return "待入住";
		case 2:
			return "已入住";
		case 3:
			return "已过期";
		default:
			return "";
		}
	}

	public void setOrdersh_id(int ordersh_id){
		this.ordersh_id=ordersh_id;
	}

	public int getOrdersh_id(){
		return ordersh_id;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}


	public void setOrdersh_date(String ordersh_date){
		this.ordersh_date=ordersh_date;
	}

	public String getOrdersh_date(){
		return ordersh_date;
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

	public int getHouse_id() {
		return house_id;
	}

	public String getHouse_title() {
		return house_title;
	}

	public void setHouse_id(int house_id) {
		this.house_id = house_id;
	}

	public void setHouse_title(String house_title) {
		this.house_title = house_title;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public double getHouse_price() {
		return house_price;
	}

	public void setHouse_price(double house_price) {
		this.house_price = house_price;
	}

	public int getHouse_count() {
		return house_count;
	}

	public void setHouse_count(int house_count) {
		this.house_count = house_count;
	}

	public double getOrdersh_money() {
		return ordersh_money;
	}

	public void setOrdersh_money(double ordersh_money) {
		this.ordersh_money = ordersh_money;
	}

	public int getOrdersh_flag() {
		return ordersh_flag;
	}

	public void setOrdersh_flag(int ordersh_flag) {
		this.ordersh_flag = ordersh_flag;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public String getOrdersh_no() {
		return ordersh_no;
	}

	public void setOrdersh_no(String ordersh_no) {
		this.ordersh_no = ordersh_no;
	}

	public int getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}

	public String getHotel_title() {
		return hotel_title;
	}

	public void setHotel_title(String hotel_title) {
		this.hotel_title = hotel_title;
	}

	public String getCome_date() {
		return come_date;
	}

	public void setCome_date(String come_date) {
		this.come_date = come_date;
	}

}
