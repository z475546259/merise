package net.merise.platform.dao.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.PayCostItem;

import org.springframework.stereotype.Repository;

@Repository
public interface PayCostItemDao {
	
	public void insert(PayCostItem payCostItem);

	public int findPayCostItemCountByPage(Map<String, Object> params);

	public List<Map<String, Object>> findPayCostItemByPage(Map<String, Object> params);

}
