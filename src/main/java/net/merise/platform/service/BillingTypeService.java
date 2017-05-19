package net.merise.platform.service;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.BillingType;

public interface BillingTypeService {

	int findCount(Map<String, Object> dataMap);

	List<BillingType> findDataByPage(Map<String, Object> dataMap);

	void insert(BillingType billingType);

	void update(BillingType billingType);

	void delete(String id);

	List<BillingType> findAllByBuilding(Map<String, Object> map);

}
