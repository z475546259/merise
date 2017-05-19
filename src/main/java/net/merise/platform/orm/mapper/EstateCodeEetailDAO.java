package net.merise.platform.orm.mapper;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.EstateCodeDetail;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstateCodeEetailDAO {

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

	EstateCodeDetail findByCodeAndBuddi(@Param("building_id")String building_id, @Param("code_content")String code_content);
	
	void deleteByBuildingId(String building_id);
}
