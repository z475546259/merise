package net.merise.platform.orm.base;

import java.util.Date;

/**
 * 员工事件明细表
 * @author Administrator
 *
 */
public class EmployeesEvent {

	private String event_id;
	private String employees_id;
	private String event_name;
	private String event_context;
	private Date event_time;
	private String status;
	private String remark;
	
	private String employees_name;

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getEmployees_id() {
		return employees_id;
	}

	public void setEmployees_id(String employees_id) {
		this.employees_id = employees_id;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getEvent_context() {
		return event_context;
	}

	public void setEvent_context(String event_context) {
		this.event_context = event_context;
	}

	public Date getEvent_time() {
		return event_time;
	}

	public void setEvent_time(Date event_time) {
		this.event_time = event_time;
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

	public String getEmployees_name() {
		return employees_name;
	}

	public void setEmployees_name(String employees_name) {
		this.employees_name = employees_name;
	}

}
