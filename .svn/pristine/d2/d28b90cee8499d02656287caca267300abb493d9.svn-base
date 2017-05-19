package net.merise.platform.dao.estate.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 移交记录表
 * 
 * @author Administrator
 * 
 */
public class Turn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7728978274786016479L;

	// 移交编号
	private String turn_id;
	// 移交金额
	private String turn_moneny;
	// 部门编号
	private String dept_no;
	// 移交人
	private String over_people;
	// 接收人
	private String recipient_perople;
	// 移交时间
	private Date turn_time;

	public String getTurn_id() {
		return turn_id;
	}

	public void setTurn_id(String turn_id) {
		this.turn_id = turn_id;
	}

	public String getTurn_moneny() {
		return turn_moneny;
	}

	public void setTurn_moneny(String turn_moneny) {
		this.turn_moneny = turn_moneny;
	}

	public String getDept_no() {
		return dept_no;
	}

	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}

	public String getOver_people() {
		return over_people;
	}

	public void setOver_people(String over_people) {
		this.over_people = over_people;
	}

	public String getRecipient_perople() {
		return recipient_perople;
	}

	public void setRecipient_perople(String recipient_perople) {
		this.recipient_perople = recipient_perople;
	}

	public Date getTurn_time() {
		return turn_time;
	}

	public void setTurn_time(Date turn_time) {
		this.turn_time = turn_time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((turn_id == null) ? 0 : turn_id.hashCode());
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
		Turn other = (Turn) obj;
		if (turn_id == null) {
			if (other.turn_id != null)
				return false;
		} else if (!turn_id.equals(other.turn_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Turn [turn_id=" + turn_id
				+ ", turn_moneny=" + turn_moneny + ", dept_no=" + dept_no
				+ ", over_people=" + over_people + ", recipient_perople="
				+ recipient_perople + ", turn_time=" + turn_time + "]";
	}

	
}
