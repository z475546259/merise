package net.merise.platform.dao.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.ChargeStandard;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeStandardDAO {
	
	void save(ChargeStandard chargeStandard);
	
	void update(ChargeStandard chargeStandard);
	
	void delete(String id);
	
	ChargeStandard findById(String id);
	
	List<ChargeStandard> findDataByPage(Map<String, Object> map);
	
	List<ChargeStandard> findDataByPageCount(Map<String, Object> map);
	
	ChargeStandard findByBuildingIdAndName(Map<String, Object> map);

	List<ChargeStandard> findAllByBillingTypeId(@Param("billing_type_id")String billing_type_id);
	
	void deleteByBillingTypeId(String billing_type_id);
}
