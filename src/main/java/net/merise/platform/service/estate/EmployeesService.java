package net.merise.platform.service.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.Employees;

public interface EmployeesService {

	int findCount(Map<String, Object> dataMap);

	List<Employees> findDataByPage(Map<String, Object> dataMap);

	void insert(Employees employees);

	void update(Employees employees);

	void delete(String id);

	Employees findDataById(String employees_id);

	List<Employees> findByDeptId(String id);
}
