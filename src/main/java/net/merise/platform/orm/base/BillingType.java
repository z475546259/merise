package net.merise.platform.orm.base;

/**
 * 计费类别表
 * @author Administrator
 *
 */
public class BillingType {

	private String billing_type_id;
	private String building_id;
	private String type_name;
	private String type_remark;
	private String status;

	public String getBilling_type_id() {
		return billing_type_id;
	}

	public void setBilling_type_id(String billing_type_id) {
		this.billing_type_id = billing_type_id;
	}

	public String getBuilding_id() {
		return building_id;
	}

	public void setBuilding_id(String building_id) {
		this.building_id = building_id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getType_remark() {
		return type_remark;
	}

	public void setType_remark(String type_remark) {
		this.type_remark = type_remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
