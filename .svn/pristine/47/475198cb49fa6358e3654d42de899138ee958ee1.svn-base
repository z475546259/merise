package net.merise.platform.service.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.ChargeStandard;
import net.merise.platform.dao.estate.pojo.EstateHouseDetail;
import net.merise.platform.dao.estate.pojo.HouseChargeRelation;

public interface HouseChargeRelationService {

	int findHouseDataCount(Map<String, Object> dataMap);

	List<EstateHouseDetail> findHouseDataByPage(Map<String, Object> dataMap);

	int findchargeRelationCount(Map<String, Object> dataMap);

	List<ChargeStandard> findchargeRelationByHouseId(Map<String, Object> dataMap);

	void updateStatus(String id);
	
	void save(HouseChargeRelation houseChargeRelation);
	
	void delete(String id);
	
	HouseChargeRelation findDataById(String relation_id);
}
