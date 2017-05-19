package net.merise.platform.service.estate.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.EstateHouseDetailDAO;
import net.merise.platform.dao.estate.OwnerDAO;
import net.merise.platform.dao.estate.pojo.EstateHouseDetail;
import net.merise.platform.service.estate.EstateHouseDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EstateHouseDetailServiceImpl implements EstateHouseDetailService {
	
	@Autowired
	private EstateHouseDetailDAO dao;
	
	@Autowired
	private OwnerDAO ownerDAO;

	@Override
	public int findCount(Map<String, Object> dataMap) {
		return dao.findCount(dataMap);
	}

	@Override
	public List<EstateHouseDetail> findDataByPage(Map<String, Object> dataMap) {
		return dao.findDataByPage(dataMap);
	}

	@Override
	public List<EstateHouseDetail> findAllData(Map<String, Object> dataMap) {
		return dao.findAllData(dataMap);
	}

	@Override
	public void insert(EstateHouseDetail estateHouseDetail) {
		dao.insert(estateHouseDetail);
	}

	@Override
	public void update(EstateHouseDetail estateHouseDetail) {
		dao.update(estateHouseDetail);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
		//删除业主房屋信息
		ownerDAO.deleteByHouseId(id);
	}

	@Override
	public List<String> findDong(String id) {
		return dao.findDong(id);
	}

	@Override
	public EstateHouseDetail findDataById(String house_id) {
		return dao.findDataById(house_id);
	}

	@Override
	public List<Map<String, Object>> findByBuildingAndDong(Map<String, Object> map) {
		return dao.findByBuildingAndDong(map);
	}

	@Override
	public void deleteByBuildingAndDongAndUnits(Map<String, Object> map) {
		dao.deleteByBuildingAndDongAndUnits(map);
	}

	@Override
	public List<Map<String, Object>> findUtil(Map<String, Object> map) {
		return dao.findUtil(map);
	}

	@Override
	public List<Map<String, Object>> findFloors(Map<String, Object> map) {
		return dao.findFloors(map);
	}

	@Override
	public List<EstateHouseDetail> findHouseByBuildingDongUnitsFloor(
			Map<String, Object> map) {
		return dao.findHouseByBuildingDongUnitsFloor(map);
	}

	@Override
	public List<Map<String, Object>> findHouseByOwnerId(String owner_id) {
		return dao.findHouseByOwnerId(owner_id);
	}

	@Override
	public List<Map<String, Object>> findBillDataByBuildingId(Map<String, Object> map) {
		return dao.findBillDataByBuildingId(map);
	}

	@Override
	public int findBillDataByBuildingIdCount(Map<String, Object> map) {
		return dao.findBillDataByBuildingIdCount(map);
	}

	@Override
	public List<Map<String, Object>> findHouseDongAndType(String building_id) {
		return dao.findHouseDongAndType(building_id);
	}

	@Override
	public List<Map<String, Object>> findHouseBillDataByBuildingId(Map<String, Object> map) {
		return dao.findHouseBillDataByBuildingId(map);
	}

	@Override
	public List<Map<String, Object>> findChargeDataByBuildingId(String building_id) {
		return dao.findChargeDataByBuildingId(building_id);
	}

	@Override
	public List<EstateHouseDetail> findHouseDataByBuildingIdAndDong(
			String building_id, String house_dong) {
		return dao.findHouseDataByBuildingIdAndDong(building_id, house_dong);
	}

	@Override
	public int findHouseBillDataByBuildingIdCount(Map<String, Object> map) {
		return dao.findHouseBillDataByBuildingIdCount(map);
	}

	@Override
	public List<EstateHouseDetail> findEstateHouseDetailByBuildingId(
			String building_id) {
		return dao.findEstateHouseDetailByBuildingId(building_id);
	}

	@Override
	public boolean isExists(EstateHouseDetail estateHouse) {
		return dao.isExists(estateHouse);
	}
	
}
