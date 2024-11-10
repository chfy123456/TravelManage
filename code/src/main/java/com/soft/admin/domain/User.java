package com.soft.admin.domain;

import com.soft.common.domain.BaseDomain;

@SuppressWarnings("serial")
public class User extends BaseDomain {
	private int user_id; // 
	private String user_name; // 
	private String user_pass; // 
	private String real_name; // 
	private int user_sex; // 1：男 0：女
	private String user_photo; // 
	private String user_mail; // 
	private String user_phone; // 
	private String reg_date; // 
	private int user_type; // 1:注册用户 2:网站管理员 3:酒店管理员 4:景点管理员 5:超级管理员

	private String user_types; 
	private String user_passOld; // 
	private String ids;
	private String random;

	public String getUser_typeDesc() {
		switch (user_type) {
		case 1:
			return "注册用户";
		case 2:
			return "网站管理员";
		case 3:
			return "酒店管理员";
		case 4:
			return "景点管理员";
		case 5:
			return "超级管理员";
		default:
			return "";
		}
	}
	
	public String getUser_sexDesc(){
		switch (user_sex) {
		case 1:
			return "男";
		case 2:
			return "女";
		default:
			return "";
		}
	}
	
	public int getUser_id() {
		return user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getUser_pass() {
		return user_pass;
	}

	public String getReal_name() {
		return real_name;
	}

	public int getUser_sex() {
		return user_sex;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public String getReg_date() {
		return reg_date;
	}

	public int getUser_type() {
		return user_type;
	}

	public String getUser_types() {
		return user_types;
	}

	public String getIds() {
		return ids;
	}

	public String getRandom() {
		return random;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public void setUser_sex(int user_sex) {
		this.user_sex = user_sex;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}

	public void setUser_types(String user_types) {
		this.user_types = user_types;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getUser_photo() {
		return user_photo;
	}

	public void setUser_photo(String user_photo) {
		this.user_photo = user_photo;
	}

	public String getUser_passOld() {
		return user_passOld;
	}

	public void setUser_passOld(String user_passOld) {
		this.user_passOld = user_passOld;
	}


}