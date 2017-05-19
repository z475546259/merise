package net.merise.platform.dao.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.EmployeesEvent;

import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesEventDAO {

	void insert(EmployeesEvent event);

	List<EmployeesEvent> findEventPageByEmployeesId(Map<String, Object> dataMap);

	int findCountByEmployeesId(Map<String, Object> dataMap);

	void delete(Map<String, Object> map);
}
