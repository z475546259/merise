package net.merise.platform.dao.estate;

import org.springframework.stereotype.Repository;

import net.merise.platform.dao.estate.pojo.TUserEmployees;

@Repository
public interface UserEmployeesDAO {

	void insert(TUserEmployees tUserEmployees);
	
	void deleteByEmployeesId(String employees_id);
	
	void deleteByUserId(String user_id);
	
	TUserEmployees findDataByEmployeesId(String employees_id);
	
	TUserEmployees findDataByUserId(String user_id);
}
