package net.merise.platform.orm.mapper;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.EstateHouseDetail;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EstateHouseDetailDAO {
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
	
	/**=================根据楼盘编号、栋、单元、楼层、房号查询该房屋是否存在=======================*/
	EstateHouseDetail getEstateHouseBYParams(Map<String, Object> params);

	List<Map<String, Object>> findOwenHouseCharge(@Param("house_id")String house_id);
	/*根据楼盘id获取房屋*/
	List<EstateHouseDetail> findEstateHouseDetailByBuildingId(@Param("building_id")String building_id);
	
	// 查询欠费的业主
	List<Map<String, Object>> findBillDataByBuildingId(Map<String, Object> map);
	
	int findBillDataByBuildingIdCount(Map<String, Object> map);
	
	List<Map<String, Object>> findHouseDongAndType(String building_id);
	
	List<Map<String, Object>> findHouseBillDataByBuildingId(Map<String, Object> map);
	
	int findHouseBillDataByBuildingIdCount(Map<String, Object> map);
	
	List<Map<String, Object>> findChargeDataByBuildingId(String building_id);
	
	List<EstateHouseDetail> findHouseDataByBuildingIdAndDong(@Param("building_id") String building_id, @Param("house_dong")String house_dong);
	//判断房屋是否存在
	boolean isExists(EstateHouseDetail estateHouse);
}
