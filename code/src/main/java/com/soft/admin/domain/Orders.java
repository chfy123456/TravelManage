package com.soft.admin.domain;

import com.soft.common.domain.BaseDomain;

@SuppressWarnings("serial")
public class Orders extends BaseDomain {
	private int orders_id; // 
	private String orders_no; // 
	private int user_id; // 
	private String real_name; // 
	private String user_phone; // 
	private int pot_id; // 
	private String pot_title; // 
	private double pot_price; // 
	private int pot_count; // 
	private double orders_money; //
	private String orders_date; // 
	private int orders_flag; // 1:待使用 2:已使用

	private int admin_id; // 
	private String ids;
	private String random;
	
	public String getOrders_flagDesc(){
		switch (orders_flag) {
		case 1:
			return "待使用";
		case 2:
			return "已使用";
		default:
			return "";
		}
	}

	public void setOrders_id(int orders_id){
		this.orders_id=orders_id;
	}

	public int getOrders_id(){
		return orders_id;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}


	public void setOrders_date(String orders_date){
		this.orders_date=orders_date;
	}

	public String getOrders_date(){
		return orders_date;
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

	public int getPot_id() {
		return pot_id;
	}

	public String getPot_title() {
		return pot_title;
	}

	public void setPot_id(int pot_id) {
		this.pot_id = pot_id;
	}

	public void setPot_title(String pot_title) {
		this.pot_title = pot_title;
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

	public double getPot_price() {
		return pot_price;
	}

	public void setPot_price(double pot_price) {
		this.pot_price = pot_price;
	}

	public int getPot_count() {
		return pot_count;
	}

	public void setPot_count(int pot_count) {
		this.pot_count = pot_count;
	}

	public double getOrders_money() {
		return orders_money;
	}

	public void setOrders_money(double orders_money) {
		this.orders_money = orders_money;
	}

	public int getOrders_flag() {
		return orders_flag;
	}

	public void setOrders_flag(int orders_flag) {
		this.orders_flag = orders_flag;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public String getOrders_no() {
		return orders_no;
	}

	public void setOrders_no(String orders_no) {
		this.orders_no = orders_no;
	}

}
