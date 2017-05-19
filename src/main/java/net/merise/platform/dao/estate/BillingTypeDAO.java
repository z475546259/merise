package net.merise.platform.dao.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.BillingType;

public interface BillingTypeDAO {

	int findCount(Map<String, Object> dataMap);

	List<BillingType> findDataByPage(Map<String, Object> dataMap);

	void delete(String id);

	void update(BillingType billingType);

	void insert(BillingType billingType);

	List<BillingType> findAllByBuilding(Map<String, Object> map);

	void deleteByBuildingId(String building_id);
}
