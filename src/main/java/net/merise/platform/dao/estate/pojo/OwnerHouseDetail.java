package net.merise.platform.dao.estate.pojo;

import java.util.Date;

/**
 * 房屋业主关联表
 * 
 * @author Administrator
 * 
 */
public class OwnerHouseDetail {

	private String id;
	private String owner_id;
	private String house_id;
	private Date garage_start_date;
	private Date garage_finish_date;
	private String live_name;
	private String live_mobile;
	private String live_idcard;
	private String owner_relation;
	private String living_email;
	private String car_num;
	private String charge_standard;

	private Date contract_time;
	private Date house_time;
	private Date decorate_time;
	private Date live_time;
	private Date termination_time;
	private String termination_reason;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}

	public String getHouse_id() {
		return house_id;
	}

	public void setHouse_id(String house_id) {
		this.house_id = house_id;
	}

	public Date getGarage_start_date() {
		return garage_start_date;
	}

	public void setGarage_start_date(Date garage_start_date) {
		this.garage_start_date = garage_start_date;
	}

	public Date getGarage_finish_date() {
		return garage_finish_date;
	}

	public void setGarage_finish_date(Date garage_finish_date) {
		this.garage_finish_date = garage_finish_date;
	}

	public String getLive_name() {
		return live_name;
	}

	public void setLive_name(String live_name) {
		this.live_name = live_name;
	}

	public String getLive_mobile() {
		return live_mobile;
	}

	public void setLive_mobile(String live_mobile) {
		this.live_mobile = live_mobile;
	}

	public String getLive_idcard() {
		return live_idcard;
	}

	public void setLive_idcard(String live_idcard) {
		this.live_idcard = live_idcard;
	}

	public String getOwner_relation() {
		return owner_relation;
	}

	public void setOwner_relation(String owner_relation) {
		this.owner_relation = owner_relation;
	}

	public String getLiving_email() {
		return living_email;
	}

	public void setLiving_email(String living_email) {
		this.living_email = living_email;
	}

	public String getCar_num() {
		return car_num;
	}

	public void setCar_num(String car_num) {
		this.car_num = car_num;
	}

	public String getCharge_standard() {
		return charge_standard;
	}

	public void setCharge_standard(String charge_standard) {
		this.charge_standard = charge_standard;
	}

	public Date getContract_time() {
		return contract_time;
	}

	public void setContract_time(Date contract_time) {
		this.contract_time = contract_time;
	}

	public Date getHouse_time() {
		return house_time;
	}

	public void setHouse_time(Date house_time) {
		this.house_time = house_time;
	}

	public Date getDecorate_time() {
		return decorate_time;
	}

	public void setDecorate_time(Date decorate_time) {
		this.decorate_time = decorate_time;
	}

	public Date getLive_time() {
		return live_time;
	}

	public void setLive_time(Date live_time) {
		this.live_time = live_time;
	}

	public Date getTermination_time() {
		return termination_time;
	}

	public void setTermination_time(Date termination_time) {
		this.termination_time = termination_time;
	}

	public String getTermination_reason() {
		return termination_reason;
	}

	public void setTermination_reason(String termination_reason) {
		this.termination_reason = termination_reason;
	}

}
