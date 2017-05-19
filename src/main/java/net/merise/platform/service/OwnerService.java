package net.merise.platform.service;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.EstateOwnerDetail;
import net.merise.platform.orm.base.OwnerHouseDetail;

public interface OwnerService {

	int findCount(Map<String, Object> dataMap);

	List<EstateOwnerDetail> findAllDataForPage(Map<String, Object> dataMap);

	void insert(EstateOwnerDetail estateOwnerDetail);

	void update(EstateOwnerDetail estateOwnerDetail);

	void delete(String id);

	List<EstateOwnerDetail> findOwnerByDong(Map<String, Object> dataMap);

	int findCountOwnerByDong(Map<String, Object> dataMap);

	void insertOwnerHouseDetail(OwnerHouseDetail ohd);

	EstateOwnerDetail findByIdcard(String owner_idcard);

	EstateOwnerDetail findDataById(String id);

	void updateOwnerHouseDetail(OwnerHouseDetail ownerHouseDetail);

	void deleteOwnerHouseDetail(String id);

	OwnerHouseDetail findLiveUserByHouseId(String house_id);

	void updateLiveOwnerHouseDetailByHouse_id(OwnerHouseDetail oldohd);

	void updateCarNumByHouseId(String house_id, String car_num,String charge_standard);

	void updateTransferHouse(Map<String, Object> map);

	void updateStatusByOwnerId(String id_value_owner, String status);

	List<EstateOwnerDetail> findDataForValidate(EstateOwnerDetail ownerDetail);
	
	List<Map<String, Object>> findUserHouseByOwnerId(String owner_id);
	
	List<Map<String, Object>> findEstateByUserId(String user_id);
}
