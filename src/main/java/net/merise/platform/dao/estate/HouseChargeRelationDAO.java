package net.merise.platform.dao.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.ChargeStandard;
import net.merise.platform.dao.estate.pojo.EstateHouseDetail;
import net.merise.platform.dao.estate.pojo.HouseChargeRelation;

import org.springframework.stereotype.Repository;

@Repository
public interface HouseChargeRelationDAO {

	List<EstateHouseDetail> findHouseDataByPage(Map<String, Object> dataMap);

	int findHouseDataCount(Map<String, Object> dataMap);

	List<ChargeStandard> findchargeRelationByHouseId(Map<String, Object> dataMap);

	int findchargeRelationCount(Map<String, Object> dataMap);

	void updateStatus(String relation_id);

	void save(HouseChargeRelation houseChargeRelation);
	
	void delete(String id);
	
	HouseChargeRelation findDataById(String relation_id);
}
