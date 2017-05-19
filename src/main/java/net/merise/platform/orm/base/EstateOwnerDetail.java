package net.merise.platform.orm.base;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 业主信息表
 * 
 * @author Administrator
 * 
 */
public class EstateOwnerDetail {

	private String owner_id;
	private String owner_name;
	private String owner_mobile;
	private String owner_idcard;
	private String owner_status;
	private String owner_family;
	private String owner_level;
	private String owner_bank_account;
	private String owner_bank_name;
	private String owner_bank_branch;
	private String owner_remark;
	private String owner_email;

	private String house_id;
	private String house_units;
	private String house_floor;
	private String house_room;
	private String house_status;
	private String house_dong;
	private String owner_level_text;
	
	private String status;
	
	private Date contract_time;
	private Date house_time;
	private Date decorate_time;
	private Date live_time;
	private Date termination_time;
	private String termination_reason;
	private String id;
	private String house_sort_text;
	private String house_status_text;
	
	private String clientid;
	
	private BigDecimal money;//余额

	public String getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public String getOwner_mobile() {
		return owner_mobile;
	}

	public void setOwner_mobile(String owner_mobile) {
		this.owner_mobile = owner_mobile;
	}

	public String getOwner_idcard() {
		return owner_idcard;
	}

	public void setOwner_idcard(String owner_idcard) {
		this.owner_idcard = owner_idcard;
	}

	public String getOwner_status() {
		return owner_status;
	}

	public void setOwner_status(String owner_status) {
		this.owner_status = owner_status;
	}

	public String getOwner_family() {
		return owner_family;
	}

	public void setOwner_family(String owner_family) {
		this.owner_family = owner_family;
	}

	public String getOwner_level() {
		return owner_level;
	}

	public void setOwner_level(String owner_level) {
		this.owner_level = owner_level;
	}

	public String getOwner_bank_account() {
		return owner_bank_account;
	}

	public void setOwner_bank_account(String owner_bank_account) {
		this.owner_bank_account = owner_bank_account;
	}

	public String getOwner_bank_name() {
		return owner_bank_name;
	}

	public void setOwner_bank_name(String owner_bank_name) {
		this.owner_bank_name = owner_bank_name;
	}

	public String getOwner_bank_branch() {
		return owner_bank_branch;
	}

	public void setOwner_bank_branch(String owner_bank_branch) {
		this.owner_bank_branch = owner_bank_branch;
	}

	public String getOwner_remark() {
		return owner_remark;
	}

	public void setOwner_remark(String owner_remark) {
		this.owner_remark = owner_remark;
	}

	public String getOwner_email() {
		return owner_email;
	}

	public void setOwner_email(String owner_email) {
		this.owner_email = owner_email;
	}

	public String getHouse_id() {
		return house_id;
	}

	public void setHouse_id(String house_id) {
		this.house_id = house_id;
	}

	public String getHouse_units() {
		return house_units;
	}

	public void setHouse_units(String house_units) {
		this.house_units = house_units;
	}

	public String getHouse_floor() {
		return house_floor;
	}

	public void setHouse_floor(String house_floor) {
		this.house_floor = house_floor;
	}

	public String getHouse_room() {
		return house_room;
	}

	public void setHouse_room(String house_room) {
		this.house_room = house_room;
	}

	public String getHouse_status() {
		return house_status;
	}

	public void setHouse_status(String house_status) {
		this.house_status = house_status;
	}

	public String getHouse_dong() {
		return house_dong;
	}

	public void setHouse_dong(String house_dong) {
		this.house_dong = house_dong;
	}

	public String getOwner_level_text() {
		return owner_level_text;
	}

	public void setOwner_level_text(String owner_level_text) {
		this.owner_level_text = owner_level_text;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHouse_sort_text() {
		return house_sort_text;
	}

	public void setHouse_sort_text(String house_sort_text) {
		this.house_sort_text = house_sort_text;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHouse_status_text() {
		return house_status_text;
	}

	public void setHouse_status_text(String house_status_text) {
		this.house_status_text = house_status_text;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

}
