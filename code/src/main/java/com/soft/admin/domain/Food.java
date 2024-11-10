package com.soft.admin.domain;

import com.soft.common.domain.BaseDomain;
import com.soft.common.util.StringUtil;
import com.soft.common.util.Transcode;

@SuppressWarnings("serial")
public class Food extends BaseDomain {
	private int food_id; // 
	private String food_title; // 
	private String food_pic; // 
	private String food_content; // 
	private String food_admin; // 
	private String food_date; // 

	private int type; 
	private String ids;
	private String random;

	public String getFood_contentShow(){
		if (!StringUtil.isEmptyString(food_content)) {
			return Transcode.htmlDiscode(food_content);
		}
		return food_content;
	}
	
	public void setFood_id(int food_id){
		this.food_id=food_id;
	}

	public int getFood_id(){
		return food_id;
	}

	public void setFood_title(String food_title){
		this.food_title=food_title;
	}

	public String getFood_title(){
		return food_title;
	}

	public void setFood_content(String food_content){
		this.food_content=food_content;
	}

	public String getFood_content(){
		return food_content;
	}

	public void setFood_admin(String food_admin){
		this.food_admin=food_admin;
	}

	public String getFood_admin(){
		return food_admin;
	}

	public void setFood_date(String food_date){
		this.food_date=food_date;
	}

	public String getFood_date(){
		return food_date;
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

	public String getFood_pic() {
		return food_pic;
	}

	public void setFood_pic(String food_pic) {
		this.food_pic = food_pic;
	}

}
