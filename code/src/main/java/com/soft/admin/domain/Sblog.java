package com.soft.admin.domain;

import com.soft.common.domain.BaseDomain;
import com.soft.common.util.StringUtil;
import com.soft.common.util.Transcode;

@SuppressWarnings("serial")
public class Sblog extends BaseDomain {
	private int sblog_id; // 
	private int user_id; // 
	private String sblog_title; // 
	private String sblog_content; // 
	private String sblog_date; // 
	private int sblog_click; // 
	private int sblog_reply; // 

	private String real_name; // 
	private String user_photo; // 
	private String user_name; // 
	private String ids;
	private String random;

	public String getSblog_contentShow(){
		if (!StringUtil.isEmptyString(sblog_content)) {
			return Transcode.htmlDiscode(sblog_content);
		}
		return sblog_content;
	}
	
	public void setSblog_id(int sblog_id){
		this.sblog_id=sblog_id;
	}

	public int getSblog_id(){
		return sblog_id;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setSblog_title(String sblog_title){
		this.sblog_title=sblog_title;
	}

	public String getSblog_title(){
		return sblog_title;
	}

	public void setSblog_content(String sblog_content){
		this.sblog_content=sblog_content;
	}

	public String getSblog_content(){
		return sblog_content;
	}

	public void setSblog_date(String sblog_date){
		this.sblog_date=sblog_date;
	}

	public String getSblog_date(){
		return sblog_date;
	}

	public void setSblog_click(int sblog_click){
		this.sblog_click=sblog_click;
	}

	public int getSblog_click(){
		return sblog_click;
	}

	public void setSblog_reply(int sblog_reply){
		this.sblog_reply=sblog_reply;
	}

	public int getSblog_reply(){
		return sblog_reply;
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

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getUser_photo() {
		return user_photo;
	}

	public void setUser_photo(String user_photo) {
		this.user_photo = user_photo;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

}
