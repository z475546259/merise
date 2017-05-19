package net.merise.platform.orm.mapper;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.Employees;

import org.springframework.stereotype.Repository;

@Repository
public interface TotalDAO {

	int findCount(Map<String, Object> dataMap);

	List<Employees> findDataByPage(Map<String, Object> dataMap);
	
	List<Map<String, Object>> findEventAndEmployees(Map<String, Object> map);
	
	List<Map<String, Object>> findRzMonth(Map<String, Object> dataMap);

	List<Map<String, Object>> findLzMonth(Map<String, Object> dataMap);

}
