package net.merise.platform.service;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.Emp;

public interface EmpService {

	int findCount(Map<String, Object> dataMap);

	List<Emp> findDataByPage(Map<String, Object> dataMap);

	void insert(Emp emp);

	void update(Emp emp);

	void delete(String id);

	Emp findDataById(String emp_id);

	void updateStatus(String id, String newStatus);

}
