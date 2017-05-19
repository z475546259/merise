package net.merise.platform.orm.base;

/**
 * 房屋户号表
 * 
 * @author Administrator
 * 
 */
public class HouseNumber {

	// 房屋户号编号
	private String house_number_id;
	// 房屋编号
	private String house_id;
	// 电户号
	private String electricity_number;
	// 水户号
	private String water_number;
	// 气户号
	private String gas_number;
	// 状态
	private String status;
	// 备注
	private String remark;
	//期数
	private String nper;

	public String getHouse_number_id() {
		return house_number_id;
	}

	public void setHouse_number_id(String house_number_id) {
		this.house_number_id = house_number_id;
	}

	public String getHouse_id() {
		return house_id;
	}

	public void setHouse_id(String house_id) {
		this.house_id = house_id;
	}

	public String getElectricity_number() {
		return electricity_number;
	}

	public void setElectricity_number(String electricity_number) {
		this.electricity_number = electricity_number;
	}

	public String getWater_number() {
		return water_number;
	}

	public void setWater_number(String water_number) {
		this.water_number = water_number;
	}

	public String getGas_number() {
		return gas_number;
	}

	public void setGas_number(String gas_number) {
		this.gas_number = gas_number;
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
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((house_id == null) ? 0 : house_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HouseNumber other = (HouseNumber) obj;
		if (house_id == null) {
			if (other.house_id != null)
				return false;
		} else if (!house_id.equals(other.house_id))
			return false;
		return true;
	}

	public String getNper() {
		return nper;
	}

	public void setNper(String nper) {
		this.nper = nper;
	}

	@Override
	public String toString() {
		return "HouseNumber [house_number_id=" + house_number_id
				+ ", house_id=" + house_id + ", electricity_number="
				+ electricity_number + ", water_number=" + water_number
				+ ", gas_number=" + gas_number + ", status=" + status
				+ ", remark=" + remark + ", nper=" + nper + "]";
	}
	
	

}
