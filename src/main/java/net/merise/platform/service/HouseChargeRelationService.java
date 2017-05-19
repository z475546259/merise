package net.merise.platform.service;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.ChargeStandard;
import net.merise.platform.orm.base.EstateHouseDetail;
import net.merise.platform.orm.base.HouseChargeRelation;

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
