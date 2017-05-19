package net.merise.platform.orm.mapper;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.EmployeesEvent;

import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesEventDAO {

	void insert(EmployeesEvent event);

	List<EmployeesEvent> findEventPageByEmployeesId(Map<String, Object> dataMap);

	int findCountByEmployeesId(Map<String, Object> dataMap);

	void delete(Map<String, Object> map);
}
