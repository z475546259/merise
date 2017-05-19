package net.merise.platform.orm.base;

import java.util.Date;

/**
 * 票据信息
 * @author Administrator
 *
 */
public class Bill {
	
	private String bill_id;
	private String bill_name;
	private String bill_type;
	private Date bill_time;
	private String bill_number;
	private String bill_starNum;
	private String bill_endNum;
	private String bill_operator;
	private String building_id;
	private String employees_id;
	private String employees_name;
	public String getBill_id() {
		return bill_id;
	}
	public void setBill_id(String bill_id) {
		this.bill_id = bill_id;
	}
	public String getBill_name() {
		return bill_name;
	}
	public void setBill_name(String bill_name) {
		this.bill_name = bill_name;
	}
	public String getBill_type() {
		return bill_type;
	}
	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
	}
	public Date getBill_time() {
		return bill_time;
	}
	public void setBill_time(Date bill_time) {
		this.bill_time = bill_time;
	}
	public String getBill_number() {
		return bill_number;
	}
	public void setBill_number(String bill_number) {
		this.bill_number = bill_number;
	}
	public String getEmployees_id() {
		return employees_id;
	}
	public void setEmployees_id(String employees_id) {
		this.employees_id = employees_id;
	}
	public String getBill_starNum() {
		return bill_starNum;
	}
	public void setBill_starNum(String bill_starNum) {
		this.bill_starNum = bill_starNum;
	}
	public String getBill_endNum() {
		return bill_endNum;
	}
	public void setBill_endNum(String bill_endNum) {
		this.bill_endNum = bill_endNum;
	}
	public String getBill_operator() {
		return bill_operator;
	}
	public void setBill_operator(String bill_operator) {
		this.bill_operator = bill_operator;
	}
	public String getBuilding_id() {
		return building_id;
	}
	public void setBuilding_id(String building_id) {
		this.building_id = building_id;
	}
	public String getEmployees_name() {
		return employees_name;
	}
	public void setEmployees_name(String employees_name) {
		this.employees_name = employees_name;
	}
}
