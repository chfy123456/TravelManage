package com.soft.admin.domain;

import com.soft.common.domain.BaseDomain;
import com.soft.common.util.StringUtil;
import com.soft.common.util.Transcode;

@SuppressWarnings("serial")
public class Info extends BaseDomain {
	private int info_id; // 
	private String info_title; // 
	private String info_content; // 
	private String info_admin; // 
	private String info_date; // 

	private int type; 
	private String ids;
	private String random;

	public String getInfo_contentShow(){
		if (!StringUtil.isEmptyString(info_content)) {
			return Transcode.htmlDiscode(info_content);
		}
		return info_content;
	}
	
	public void setInfo_id(int info_id){
		this.info_id=info_id;
	}

	public int getInfo_id(){
		return info_id;
	}

	public void setInfo_title(String info_title){
		this.info_title=info_title;
	}

	public String getInfo_title(){
		return info_title;
	}

	public void setInfo_content(String info_content){
		this.info_content=info_content;
	}

	public String getInfo_content(){
		return info_content;
	}

	public void setInfo_admin(String info_admin){
		this.info_admin=info_admin;
	}

	public String getInfo_admin(){
		return info_admin;
	}

	public void setInfo_date(String info_date){
		this.info_date=info_date;
	}

	public String getInfo_date(){
		return info_date;
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

}
