package net.merise.platform.dao.estate.pojo;
import java.util.Date;

/**
 * @ClassName: OwnerBill 
 * @Description: 业主账单实体类
 * @author SunXiaoYong.Inc
 * @date 2017年3月15日 下午2:31:55
 */
public class OwnerBill {

	private String bill_id;
	private String house_id;
	private String owner_id;
	private String standard_id;
	private String standard_name;
	private String batch;
	//末缴金额
	private String money;
	private int status;
	private Date pay_date;
	private String house_number_id;
	private Date expire_date; //到期时间
	//应缴金额
	private String receivable_moneny;
	//实缴金额
	private String paycost_moneny;

	public void setBill_id(String bill_id) {
		this.bill_id = bill_id;
	}
	public String getBill_id() {
		return bill_id;
	}
	public void setHouse_id(String house_id) {
		this.house_id = house_id;
	}
	public String getHouse_id() {
		return house_id;
	}
	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}
	public String getOwner_id() {
		return owner_id;
	}
	public void setStandard_id(String standard_id) {
		this.standard_id = standard_id;
	}
	public String getStandard_id() {
		return standard_id;
	}
	public void setStandard_name(String standard_name) {
		this.standard_name = standard_name;
	}
	public String getStandard_name() {
		return standard_name;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getBatch() {
		return batch;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getMoney() {
		return money;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}
	public Date getPay_date() {
		return pay_date;
	}
	public void setHouse_number_id(String house_number_id) {
		this.house_number_id = house_number_id;
	}
	public String getHouse_number_id() {
		return house_number_id;
	}
	public Date getExpire_date() {
		return expire_date;
	}
	public void setExpire_date(Date expire_date) {
		this.expire_date = expire_date;
	}
	public String getReceivable_moneny() {
		return receivable_moneny;
	}
	public void setReceivable_moneny(String receivable_moneny) {
		this.receivable_moneny = receivable_moneny;
	}
	public String getPaycost_moneny() {
		return paycost_moneny;
	}
	public void setPaycost_moneny(String paycost_moneny) {
		this.paycost_moneny = paycost_moneny;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bill_id == null) ? 0 : bill_id.hashCode());
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
		OwnerBill other = (OwnerBill) obj;
		if (bill_id == null) {
			if (other.bill_id != null)
				return false;
		} else if (!bill_id.equals(other.bill_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OwnerBill [bill_id=" + bill_id + ", house_id=" + house_id
				+ ", owner_id=" + owner_id + ", standard_id=" + standard_id
				+ ", standard_name=" + standard_name + ", batch=" + batch
				+ ", money=" + money + ", status=" + status + ", pay_date="
				+ pay_date + ", house_number_id=" + house_number_id
				+ ", expire_date=" + expire_date + ", receivable_moneny="
				+ receivable_moneny + ", paycost_moneny=" + paycost_moneny
				+ "]";
	}
	
}
