package net.merise.platform.dao.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.EstateOwnerDetail;
import net.merise.platform.dao.estate.pojo.OwnerHouseDetail;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerDAO {

	void delete(String id);

	void update(EstateOwnerDetail estateOwnerDetail);

	void insert(EstateOwnerDetail estateOwnerDetail);

	List<EstateOwnerDetail> findAllDataForPage(Map<String, Object> dataMap);

	int findCount(Map<String, Object> dataMap);

	List<EstateOwnerDetail> findOwnerByDong(Map<String, Object> dataMap);

	int findCountOwnerByDong(Map<String, Object> dataMap);

	void insertOwnerHouseDetail(OwnerHouseDetail ohd);

	EstateOwnerDetail findByIdcard(String owner_idcard);

	EstateOwnerDetail findDataById(String owner_id);

	void updateOwnerHouseDetail(OwnerHouseDetail ownerHouseDetail);

	void deleteOwnerHouseDetail(String id);

	OwnerHouseDetail findLiveUserByHouseId(String house_id);

	void updateLiveOwnerHouseDetailByHouse_id(OwnerHouseDetail oldohd);

	void updateCarNumByHouseId(@Param("house_id") String house_id,@Param("car_num") String car_num,@Param("charge_standard")String charge_standard);

	void updateTransferHouse(Map<String, Object> map);

	void updateStatusByOwnerId(@Param("owner_id") String owner_id,@Param("status") String status);

	List<EstateOwnerDetail> findDataForValidate(EstateOwnerDetail ownerDetail);

	/**=================yxz====================*/
	OwnerHouseDetail findByHouseId(@Param("house_id")String house_id);

	OwnerHouseDetail findByHouseByParams(Map<String, Object> params);

	Map<String, Object> getOwnerDetail1(Map<String, Object> params);

	void updateMoneyById(Map<String, Object> params);

	void deleteByHouseId(@Param("house_id")String house_id);
	
	List<Map<String, Object>> findUserHouseByOwnerId(String owner_id);
	
	List<Map<String, Object>> findEstateByUserId(String user_id);
}
