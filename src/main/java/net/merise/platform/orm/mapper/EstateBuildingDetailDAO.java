package net.merise.platform.orm.mapper;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.EstateBuildingDetail;

import org.springframework.stereotype.Repository;

@Repository
public interface EstateBuildingDetailDAO {

	void delete(String id);

	void update(EstateBuildingDetail estateBuildingDetail);

	void insert(EstateBuildingDetail estateBuildingDetail);

	int findCount(Map<String, Object> dataMap);

	List<EstateBuildingDetail> findDataByPage(Map<String, Object> dataMap);

	List<EstateBuildingDetail> findAllData(Map<String, Object> dataMap);

	List<EstateBuildingDetail> findEstateBuildingByEstateId(String estateId);

	
	List<EstateBuildingDetail> findEstateBuildingByName(String building_name);

	List<EstateBuildingDetail> findBuildingAll();


}
