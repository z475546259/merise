package net.merise.platform.service;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.PayCost;

public interface PayCostService {
	
	void insert(PayCost payCost);

	Map<String, Object> findPayCostByPage(Map<String, Object> params);

	Map<String, Object> findPayCostByPageOfDay(Map<String, Object> params);

	Map<String, Object> payList(Map<String,Object> parmas);

	String payCost(String building_id,String dong,String house_id, double owe_moneny, double paycost_balance,
			double now_moneny, double paycost_moneny);

	List<Map<String, Object>> findPayCostByHouseAndOwnerId(String owner_id, String house_id);

	Map<String, Object> findPayCostItemByPage(Map<String, Object> params);

	Map<String, Object> payCostLackRecord(Map<String, Object> params);
}
