package com.soft.admin.domain;

import com.soft.common.domain.BaseDomain;
import com.soft.common.util.StringUtil;
import com.soft.common.util.Transcode;

@SuppressWarnings("serial")
public class Line extends BaseDomain {
	private int line_id; // 
	private String line_title; // 
	private String line_pic; // 
	private String line_content; // 
	private String line_admin; // 
	private String line_date; // 

	private int type; 
	private String ids;
	private String random;

	public String getLine_contentShow(){
		if (!StringUtil.isEmptyString(line_content)) {
			return Transcode.htmlDiscode(line_content);
		}
		return line_content;
	}
	
	public void setLine_id(int line_id){
		this.line_id=line_id;
	}

	public int getLine_id(){
		return line_id;
	}

	public void setLine_title(String line_title){
		this.line_title=line_title;
	}

	public String getLine_title(){
		return line_title;
	}

	public void setLine_content(String line_content){
		this.line_content=line_content;
	}

	public String getLine_content(){
		return line_content;
	}

	public void setLine_admin(String line_admin){
		this.line_admin=line_admin;
	}

	public String getLine_admin(){
		return line_admin;
	}

	public void setLine_date(String line_date){
		this.line_date=line_date;
	}

	public String getLine_date(){
		return line_date;
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

	public String getLine_pic() {
		return line_pic;
	}

	public void setLine_pic(String line_pic) {
		this.line_pic = line_pic;
	}

}
