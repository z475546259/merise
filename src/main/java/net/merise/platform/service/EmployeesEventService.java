package net.merise.platform.service;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.EmployeesEvent;

public interface EmployeesEventService {

	void insert(EmployeesEvent event);

	List<EmployeesEvent> findEventPageByEmployeesId(Map<String, Object> dataMap);

	int findCountByEmployeesId(Map<String, Object> dataMap);

	void delete(Map<String, Object> map);
}
