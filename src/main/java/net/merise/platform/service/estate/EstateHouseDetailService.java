package net.merise.platform.service.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.EstateHouseDetail;

public interface EstateHouseDetailService {
	int findCount(Map<String, Object> dataMap);

	List<EstateHouseDetail> findDataByPage(Map<String, Object> dataMap);

	List<EstateHouseDetail> findAllData(Map<String, Object> dataMap);

	void insert(EstateHouseDetail estateHouseDetail);

	void update(EstateHouseDetail estateHouseDetail);

	void delete(String id);
	
	List<String> findDong(String id);

	EstateHouseDetail findDataById(String house_id);
	
	List<Map<String, Object>> findByBuildingAndDong(Map<String, Object> map);
	
	void deleteByBuildingAndDongAndUnits(Map<String, Object> map);
	
	List<Map<String, Object>> findUtil(Map<String, Object> map);
	
	List<Map<String, Object>> findFloors(Map<String, Object> map);
	
	List<EstateHouseDetail> findHouseByBuildingDongUnitsFloor(Map<String, Object> map);
	
	List<Map<String, Object>> findHouseByOwnerId(String owner_id);
	
	List<Map<String, Object>> findBillDataByBuildingId(Map<String, Object> map);
	
	int findBillDataByBuildingIdCount(Map<String, Object> map);
	
	List<Map<String, Object>> findHouseDongAndType(String building_id);
	
	List<Map<String, Object>> findHouseBillDataByBuildingId(Map<String, Object> map);
	
	int findHouseBillDataByBuildingIdCount(Map<String, Object> map);
	
	List<Map<String, Object>> findChargeDataByBuildingId(String building_id);
	
	List<EstateHouseDetail> findHouseDataByBuildingIdAndDong(String building_id, String house_dong);
	
	List<EstateHouseDetail> findEstateHouseDetailByBuildingId(String building_id);

	boolean isExists(EstateHouseDetail estateHouse);
}
