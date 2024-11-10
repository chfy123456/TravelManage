package com.soft.admin.domain;

import com.soft.common.domain.BaseDomain;
import com.soft.common.util.StringUtil;
import com.soft.common.util.Transcode;

@SuppressWarnings("serial")
public class Pot extends BaseDomain {
	private int pot_id; // 
	private String pot_title; // 
	private String pot_pic; // 
	private double pot_price; // 
	private String pot_content; // 
	private String pot_admin; // 
	private String pot_date; // 

	private String pot_price1; //
	private String pot_price2; //
	private int type; 
	private String ids;
	private String random;
	private double w = 0;//余弦值
	private int buy_count; 
	private int hot_flag; 

	public String getPot_contentShow(){
		if (!StringUtil.isEmptyString(pot_content)) {
			return Transcode.htmlDiscode(pot_content);
		}
		return pot_content;
	}
	
	public void setPot_id(int pot_id){
		this.pot_id=pot_id;
	}

	public int getPot_id(){
		return pot_id;
	}

	public void setPot_title(String pot_title){
		this.pot_title=pot_title;
	}

	public String getPot_title(){
		return pot_title;
	}

	public void setPot_content(String pot_content){
		this.pot_content=pot_content;
	}

	public String getPot_content(){
		return pot_content;
	}

	public void setPot_admin(String pot_admin){
		this.pot_admin=pot_admin;
	}

	public String getPot_admin(){
		return pot_admin;
	}

	public void setPot_date(String pot_date){
		this.pot_date=pot_date;
	}

	public String getPot_date(){
		return pot_date;
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

	public String getPot_pic() {
		return pot_pic;
	}

	public void setPot_pic(String pot_pic) {
		this.pot_pic = pot_pic;
	}

	public double getPot_price() {
		return pot_price;
	}

	public void setPot_price(double pot_price) {
		this.pot_price = pot_price;
	}

	public String getPot_price1() {
		return pot_price1;
	}

	public void setPot_price1(String pot_price1) {
		this.pot_price1 = pot_price1;
	}

	public String getPot_price2() {
		return pot_price2;
	}

	public void setPot_price2(String pot_price2) {
		this.pot_price2 = pot_price2;
	}

	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}

	public int getBuy_count() {
		return buy_count;
	}

	public void setBuy_count(int buy_count) {
		this.buy_count = buy_count;
	}

	public int getHot_flag() {
		return hot_flag;
	}

	public void setHot_flag(int hot_flag) {
		this.hot_flag = hot_flag;
	}

}
