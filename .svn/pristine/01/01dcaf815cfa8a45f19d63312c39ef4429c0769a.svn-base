package net.merise.platform.service;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.EstateCodeDetail;

public interface EstateCodeEetailService {

	int findCount(Map<String, Object> dataMap);

	List<EstateCodeDetail> findDataByPage(Map<String, Object> dataMap);

	List<EstateCodeDetail> findAllData(Map<String, Object> dataMap);

	void insert(EstateCodeDetail estateCodeDetail);

	void update(EstateCodeDetail estateCodeDetail);

	void delete(String id);

	List<EstateCodeDetail> findByCodeNameAndBuilding(Map<String, Object> dataMap);
	
	List<EstateCodeDetail> findByCodeNameAndBuildings(Map<String, Object> dataMap);

	EstateCodeDetail findDataById(String code_id);

	EstateCodeDetail findByCodeNameAndBuildingAndContent(Map<String, Object> dataMap);

	List<EstateCodeDetail> findByCodeNameAndEstateId(Map<String, Object> map);

	int findDeptAllCount(Map<String, Object> dataMap);

	List<EstateCodeDetail> findDeptAllByPage(Map<String, Object> dataMap);

	EstateCodeDetail findByCodeAndBuddi(String building_id, String string);
}
