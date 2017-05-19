package net.merise.platform.service;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.EstateBuildingDetail;

public interface EstateBuildingDetailService {

	int findCount(Map<String, Object> dataMap);

	void insert(EstateBuildingDetail estateBuildingDetail);

	void update(EstateBuildingDetail estateBuildingDetail);

	void delete(String id);

	List<EstateBuildingDetail> findDataByPage(Map<String, Object> dataMap);

	List<EstateBuildingDetail> findAllData(Map<String, Object> dataMap);

	List<EstateBuildingDetail> findEstateBuildingByEstateId(String estateId);

	List<EstateBuildingDetail> findBuildingAll();

}
