package net.merise.platform.service;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.Bill;

public interface BillService {
	List<Bill> findDataByPage(Map<String, Object> map);
	int findPageCount(Map<String, Object> map);
	
	void save(Bill bill);
}
