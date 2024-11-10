package com.soft.admin.domain;

import com.soft.common.domain.BaseDomain;

@SuppressWarnings("serial")
public class Collect extends BaseDomain {
	private int collect_id; // 
	private int user_id; // 
	private int line_id; // 
	private String collect_date; // 

	private String line_title; // 
	private String ids;
	private String random;

	public void setCollect_id(int collect_id){
		this.collect_id=collect_id;
	}

	public int getCollect_id(){
		return collect_id;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}


	public void setCollect_date(String collect_date){
		this.collect_date=collect_date;
	}

	public String getCollect_date(){
		return collect_date;
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

	public int getLine_id() {
		return line_id;
	}

	public String getLine_title() {
		return line_title;
	}

	public void setLine_id(int line_id) {
		this.line_id = line_id;
	}

	public void setLine_title(String line_title) {
		this.line_title = line_title;
	}

}
