package net.merise.platform.orm.base;

import java.io.Serializable;

/**
 * 缴费详情表
 * @author Administrator
 *
 */
public class PayCostItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2204941181924191835L;
	
	//缴费详情编号
	private String id;
	//应缴金额
	private String should_amount;
	//实缴金额
	private String paid_amount;
	//未缴金额
	private String unpaid_amount;
	//缴费项目
	private String paycost_project;
	//缴费时间
	private String paycost_time;
	//缴费记录编号
	private String paycost_id;
	//期数
	private String batch;
	//状态
	private Integer status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShould_amount() {
		return should_amount;
	}
	public void setShould_amount(String should_amount) {
		this.should_amount = should_amount;
	}
	public String getPaid_amount() {
		return paid_amount;
	}
	public void setPaid_amount(String paid_amount) {
		this.paid_amount = paid_amount;
	}
	public String getUnpaid_amount() {
		return unpaid_amount;
	}
	public void setUnpaid_amount(String unpaid_amount) {
		this.unpaid_amount = unpaid_amount;
	}
	public String getPaycost_project() {
		return paycost_project;
	}
	public void setPaycost_project(String paycost_project) {
		this.paycost_project = paycost_project;
	}
	public String getPaycost_time() {
		return paycost_time;
	}
	public void setPaycost_time(String paycost_time) {
		this.paycost_time = paycost_time;
	}
	public String getPaycost_id() {
		return paycost_id;
	}
	public void setPaycost_id(String paycost_id) {
		this.paycost_id = paycost_id;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		PayCostItem other = (PayCostItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PayCostItem [id=" + id + ", should_amount=" + should_amount
				+ ", paid_amount=" + paid_amount + ", unpaid_amount="
				+ unpaid_amount + ", paycost_project=" + paycost_project
				+ ", paycost_time=" + paycost_time + ", paycost_id="
				+ paycost_id + ", batch=" + batch + ", status=" + status + "]";
	}
	
	

}
