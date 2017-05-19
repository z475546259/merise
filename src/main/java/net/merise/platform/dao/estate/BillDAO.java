package net.merise.platform.dao.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.Bill;

import org.springframework.stereotype.Repository;

@Repository
public interface BillDAO {

	List<Bill> findDataByPage(Map<String, Object> map);
	int findPageCount(Map<String, Object> map);
	
	void save(Bill bill);
	
	void deleteByBuildingId(String building_id);
}
