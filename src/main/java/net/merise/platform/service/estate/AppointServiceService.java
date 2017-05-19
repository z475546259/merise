package net.merise.platform.service.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.AppointService;

public interface AppointServiceService {

	void insert(AppointService appointService);

	int findCount(Map<String, Object> dataMap);

	List<AppointService> findDataByPage(Map<String, Object> dataMap);

	AppointService findDataById(String id);

	void update(AppointService oldservice);

	void delete(String service_id);

	List<AppointService> findDataByBuildingId(String building_id);
}
