package net.merise.platform.dao.estate.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeStandard {

	private String standard_id;
	private String billing_type_id;
	private String billing_type_name;
	private String project_name;
	private String denominated_unit;
	private String denominated_unit_text;
	private BigDecimal standard_price;
	private String coefficient;
	private String charge_unit;
	private String charge_num;
	private String house_dong;
	private String house_units;
	private String start_floor;
	private String end_floor;
	private Double premium;
	private String status;
	private String remark;
	private Date create_time;

	private String relation_id;
	private String house_id;

	public String getStandard_id() {
		return standard_id;
	}

	public void setStandard_id(String standard_id) {
		this.standard_id = standard_id;
	}

	public String getBilling_type_id() {
		return billing_type_id;
	}

	public void setBilling_type_id(String billing_type_id) {
		this.billing_type_id = billing_type_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getDenominated_unit() {
		return denominated_unit;
	}

	public void setDenominated_unit(String denominated_unit) {
		this.denominated_unit = denominated_unit;
	}

	public String getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(String coefficient) {
		this.coefficient = coefficient;
	}

	public String getCharge_unit() {
		return charge_unit;
	}

	public void setCharge_unit(String charge_unit) {
		this.charge_unit = charge_unit;
	}

	public String getHouse_dong() {
		return house_dong;
	}

	public void setHouse_dong(String house_dong) {
		this.house_dong = house_dong;
	}

	public String getHouse_units() {
		return house_units;
	}

	public void setHouse_units(String house_units) {
		this.house_units = house_units;
	}

	public String getStart_floor() {
		return start_floor;
	}

	public void setStart_floor(String start_floor) {
		this.start_floor = start_floor;
	}

	public String getEnd_floor() {
		return end_floor;
	}

	public void setEnd_floor(String end_floor) {
		this.end_floor = end_floor;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getBilling_type_name() {
		return billing_type_name;
	}

	public void setBilling_type_name(String billing_type_name) {
		this.billing_type_name = billing_type_name;
	}

	public String getCharge_num() {
		return charge_num;
	}

	public void setCharge_num(String charge_num) {
		this.charge_num = charge_num;
	}

	public String getDenominated_unit_text() {
		return denominated_unit_text;
	}

	public void setDenominated_unit_text(String denominated_unit_text) {
		this.denominated_unit_text = denominated_unit_text;
	}

	public String getRelation_id() {
		return relation_id;
	}

	public void setRelation_id(String relation_id) {
		this.relation_id = relation_id;
	}

	public String getHouse_id() {
		return house_id;
	}

	public void setHouse_id(String house_id) {
		this.house_id = house_id;
	}

	public BigDecimal getStandard_price() {
		return standard_price;
	}

	public void setStandard_price(BigDecimal standard_price) {
		this.standard_price = standard_price;
	}

}
