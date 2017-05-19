package net.merise.platform.dao.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.PayCost;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PayCostDao {
	
	void insert(PayCost payCost);

	List<Map<String, Object>> findPayCostByPage(Map<String, Object> params);

	List<Map<String, Object>> findPayCostByPageOfDay(Map<String, Object> params);

	int findCount(Map<String, Object> params);

	int findCountOfDay(Map<String, Object> params);

	List<Map<String, Object>> findPayCostByHouseAndOwnerId(@Param("owner_id")String owner_id, @Param("house_id")String house_id);

	int findPayCostItemCountByPage(Map<String, Object> params);

	List<Map<String, Object>> findPayCostItemByPage(Map<String, Object> params);

	int payCostCountLackRecord(Map<String, Object> params);

	List<Map<String, Object>> payCostLackRecord(Map<String, Object> params);
}
