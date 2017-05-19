package net.merise.platform.dao.estate.pojo;

import java.io.Serializable;

/**
 * 缴费记录表
 * 
 * @author Administrator
 * 
 */
public class PayCost implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3704585561434885025L;

	private String paycost_id;
	// 缴费项目
	private String paycost_project;
	// 缴费方式
	private String paycost_way;
	// 实收金额
	private String paycost_moneny;
	// 用户余额
	private String paycost_balance;
	// 缴费时间
	private String paycost_time;
	// 账单编号
	private String bill_id;
	
	private String owner_id;
	
	private String house_id;
	//应缴金额
	private String receivable_moneny;

	public String getPaycost_id() {
		return paycost_id;
	}

	public void setPaycost_id(String paycost_id) {
		this.paycost_id = paycost_id;
	}

	public String getPaycost_project() {
		return paycost_project;
	}

	public void setPaycost_project(String paycost_project) {
		this.paycost_project = paycost_project;
	}

	public String getPaycost_way() {
		return paycost_way;
	}

	public void setPaycost_way(String paycost_way) {
		this.paycost_way = paycost_way;
	}

	public String getPaycost_moneny() {
		return paycost_moneny;
	}

	public void setPaycost_moneny(String paycost_moneny) {
		this.paycost_moneny = paycost_moneny;
	}

	public String getPaycost_balance() {
		return paycost_balance;
	}

	public void setPaycost_balance(String paycost_balance) {
		this.paycost_balance = paycost_balance;
	}

	public String getPaycost_time() {
		return paycost_time;
	}

	public void setPaycost_time(String paycost_time) {
		this.paycost_time = paycost_time;
	}

	public String getBill_id() {
		return bill_id;
	}

	public void setBill_id(String bill_id) {
		this.bill_id = bill_id;
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
	
	public String getReceivable_moneny() {
		return receivable_moneny;
	}

	public void setReceivable_moneny(String receivable_moneny) {
		this.receivable_moneny = receivable_moneny;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((paycost_id == null) ? 0 : paycost_id.hashCode());
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
		PayCost other = (PayCost) obj;
		if (paycost_id == null) {
			if (other.paycost_id != null)
				return false;
		} else if (!paycost_id.equals(other.paycost_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PayCost [paycost_id=" + paycost_id + ", paycost_project="
				+ paycost_project + ", paycost_way=" + paycost_way
				+ ", paycost_moneny=" + paycost_moneny + ", paycost_balance="
				+ paycost_balance + ", paycost_time=" + paycost_time
				+ ", bill_id=" + bill_id + ", owner_id=" + owner_id
				+ ", house_id=" + house_id + ", receivable_moneny="
				+ receivable_moneny + "]";
	}

	
	
}
