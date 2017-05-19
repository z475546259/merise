package net.merise.platform.service;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.ChargeStandard;

public interface ChargeStandardService {
	
	void save(ChargeStandard chargeStandard);
	
	void update(ChargeStandard chargeStandard);
	
	void delete(String id);
	
	ChargeStandard findById(String id);
	
	List<ChargeStandard> findDataByPage(Map<String, Object> map);
	
	List<ChargeStandard> findDataByPageCount(Map<String, Object> map);
	
	ChargeStandard findByBuildingIdAndName(Map<String, Object> map);

}
