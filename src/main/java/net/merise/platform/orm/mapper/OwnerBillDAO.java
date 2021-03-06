package net.merise.platform.orm.mapper;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.OwnerBill;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: OwnerBillDAO 
 * @Description: 业主账单DAO
 * @author SunXiaoYong.Inc
 * @date 2017年3月15日 下午2:32:56
 */
@Repository
public interface OwnerBillDAO {

	void insert(OwnerBill ownerBill);
	
	void delete(String bill_id);
	
	void updateStatus(String bill_id);
	
	void deleteByHouseAndOwnerId(@Param("house_id") String house_id, @Param("owner_id") String owner_id);
	
	void deleteByStandardId(String standard_id);
	
	OwnerBill findDataById(String bill_id); 
	
	List<OwnerBill> findAllData();
	
	List<OwnerBill> findDataByHouseAndOwnerId(@Param("house_id") String house_id, @Param("owner_id") String owner_id);

	List<Map<String, Object>> payList(Map<String, Object> params);
	
	void deleteByBatch(Map<String, Object> map);
	
	void deleteByHouseId(String house_id);
	
	void updateMoneny(@Param("bill_id")String bill_id, @Param("moneny")String moneny);

	void updateOwerBillByPayCost(Map<String, Object> map);
}
