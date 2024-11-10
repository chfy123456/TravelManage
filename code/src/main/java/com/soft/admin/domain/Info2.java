package com.soft.admin.domain;

import com.soft.common.domain.BaseDomain;
import com.soft.common.util.StringUtil;
import com.soft.common.util.Transcode;

@SuppressWarnings("serial")
public class Info2 extends BaseDomain {
	private int info2_id; // 
	private String info2_title; // 
	private String info2_content; // 
	private String info2_admin; // 
	private String info2_date; // 

	private int type; 
	private String ids;
	private String random;

	public String getInfo2_contentShow(){
		if (!StringUtil.isEmptyString(info2_content)) {
			return Transcode.htmlDiscode(info2_content);
		}
		return info2_content;
	}
	
	public void setInfo2_id(int info2_id){
		this.info2_id=info2_id;
	}

	public int getInfo2_id(){
		return info2_id;
	}

	public void setInfo2_title(String info2_title){
		this.info2_title=info2_title;
	}

	public String getInfo2_title(){
		return info2_title;
	}

	public void setInfo2_content(String info2_content){
		this.info2_content=info2_content;
	}

	public String getInfo2_content(){
		return info2_content;
	}

	public void setInfo2_admin(String info2_admin){
		this.info2_admin=info2_admin;
	}

	public String getInfo2_admin(){
		return info2_admin;
	}

	public void setInfo2_date(String info2_date){
		this.info2_date=info2_date;
	}

	public String getInfo2_date(){
		return info2_date;
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
