package net.merise.platform.service.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.EstateOwnerDetail;
import net.merise.platform.orm.base.OwnerHouseDetail;
import net.merise.platform.orm.mapper.OwnerDAO;
import net.merise.platform.service.OwnerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerDAO ownerDao;

	@Override
	public int findCount(Map<String, Object> dataMap) {
		return ownerDao.findCount(dataMap);
	}

	@Override
	public List<EstateOwnerDetail> findAllDataForPage(Map<String, Object> dataMap) {
		return ownerDao.findAllDataForPage(dataMap);
	}

	@Override
	public void insert(EstateOwnerDetail estateOwnerDetail) {
		ownerDao.insert(estateOwnerDetail);
	}

	@Override
	public void update(EstateOwnerDetail estateOwnerDetail) {
		ownerDao.update(estateOwnerDetail);
	}

	@Override
	public void delete(String id) {
		ownerDao.delete(id);
	}

	@Override
	public List<EstateOwnerDetail> findOwnerByDong(Map<String, Object> dataMap) {
		return ownerDao.findOwnerByDong(dataMap);
	}

	@Override
	public int findCountOwnerByDong(Map<String, Object> dataMap) {
		return ownerDao.findCountOwnerByDong(dataMap);
	}

	@Override
	public void insertOwnerHouseDetail(OwnerHouseDetail ohd) {
		ownerDao.insertOwnerHouseDetail(ohd);
	}

	@Override
	public EstateOwnerDetail findByIdcard(String owner_idcard) {
		return ownerDao.findByIdcard(owner_idcard);
	}

	@Override
	public EstateOwnerDetail findDataById(String id) {
		return ownerDao.findDataById(id);
	}

	@Override
	public void updateOwnerHouseDetail(OwnerHouseDetail ownerHouseDetail) {
		ownerDao.updateOwnerHouseDetail(ownerHouseDetail);
	}

	@Override
	public void deleteOwnerHouseDetail(String id) {
		ownerDao.deleteOwnerHouseDetail(id);
	}

	@Override
	public OwnerHouseDetail findLiveUserByHouseId(String house_id) {
		return ownerDao.findLiveUserByHouseId(house_id);
	}

	@Override
	public void updateLiveOwnerHouseDetailByHouse_id(OwnerHouseDetail oldohd) {
		ownerDao.updateLiveOwnerHouseDetailByHouse_id(oldohd);
	}

	@Override
	public void updateCarNumByHouseId(String house_id, String car_num,String charge_standard) {
		ownerDao.updateCarNumByHouseId(house_id,car_num,charge_standard);
	}

	@Override
	public void updateTransferHouse(Map<String, Object> map) {
		ownerDao.updateTransferHouse(map);
	}

	@Override
	public void updateStatusByOwnerId(String id_value_owner, String status) {
		ownerDao.updateStatusByOwnerId(id_value_owner,status);
	}

	@Override
	public List<EstateOwnerDetail> findDataForValidate(
			EstateOwnerDetail ownerDetail) {
		return ownerDao.findDataForValidate(ownerDetail);
	}

	@Override
	public List<Map<String, Object>> findUserHouseByOwnerId(String owner_id) {
		return ownerDao.findUserHouseByOwnerId(owner_id);
	}

	@Override
	public List<Map<String, Object>> findEstateByUserId(String user_id) {
		return ownerDao.findEstateByUserId(user_id);
	}

}
