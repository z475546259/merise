package net.merise.platform.orm.base;

public class TUserEmployees {

	private int id;
	private String user_id;
	private String employees_id;

	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setEmployees_id(String employees_id) {
		this.employees_id = employees_id;
	}
	public String getEmployees_id() {
		return employees_id;
	}
}
