package net.merise.platform.service;

import net.merise.platform.orm.base.TUserEmployees;

public interface UserEmployeeService {

	void insert(TUserEmployees tUserEmployees);
	
	void deleteByEmployeesId(String employees_id);
	
	void deleteByUserId(String user_id);
	
	TUserEmployees findDataByEmployeesId(String employees_id);
	
	TUserEmployees findDataByUserId(String user_id);
	
}
