package net.merise.platform.orm.mapper;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.AppointService;

import org.springframework.stereotype.Repository;

@Repository
public interface AppointServiceDAO {

	void insert(AppointService appointService);

	int findCount(Map<String, Object> dataMap);

	List<AppointService> findDataByPage(Map<String, Object> dataMap);

	AppointService findDataById(String service_id);

	void update(AppointService oldservice);

	void delete(String service_id);
	
	List<AppointService> findDataByBuildingId(String building_id);
	
	void deleteByBuildingId(String building_id);
}
