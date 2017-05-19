package net.merise.platform.service;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.OwnerBill;

public interface OwnerBillService {

	void insert(OwnerBill ownerBill);
	
	void delete(String bill_id);
	
	void updateStatus(String bill_id);
	
	void deleteByHouseAndOwnerId(String house_id, String owner_id);
	
	void deleteByStandardId(String standard_id);
	
	OwnerBill findDataById(String bill_id); 
	
	List<OwnerBill> findAllData();
	
	List<OwnerBill> findDataByHouseAndOwnerId(String house_id, String owner_id);
	
	void deleteByBatch(Map<String, Object> map);
}
