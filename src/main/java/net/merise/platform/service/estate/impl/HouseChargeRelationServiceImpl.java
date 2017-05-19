package net.merise.platform.service.estate.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.HouseChargeRelationDAO;
import net.merise.platform.dao.estate.pojo.ChargeStandard;
import net.merise.platform.dao.estate.pojo.EstateHouseDetail;
import net.merise.platform.dao.estate.pojo.HouseChargeRelation;
import net.merise.platform.service.estate.HouseChargeRelationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HouseChargeRelationServiceImpl implements HouseChargeRelationService {

	@Autowired
	private HouseChargeRelationDAO houseChargeRelationDao;

	@Override
	public int findHouseDataCount(Map<String, Object> dataMap) {
		return houseChargeRelationDao.findHouseDataCount(dataMap);
	}

	@Override
	public List<EstateHouseDetail> findHouseDataByPage(Map<String, Object> dataMap) {
		return houseChargeRelationDao.findHouseDataByPage(dataMap);
	}

	@Override
	public int findchargeRelationCount(Map<String, Object> dataMap) {
		return houseChargeRelationDao.findchargeRelationCount(dataMap);
	}

	@Override
	public List<ChargeStandard> findchargeRelationByHouseId(Map<String, Object> dataMap) {
		return houseChargeRelationDao.findchargeRelationByHouseId(dataMap);
	}

	@Override
	public void delete(String id) {
		houseChargeRelationDao.delete(id);
	}

	@Override
	public void updateStatus(String id) {
		houseChargeRelationDao.updateStatus(id);
	}

	@Override
	public void save(HouseChargeRelation houseChargeRelation) {
		houseChargeRelationDao.save(houseChargeRelation);
	}

	@Override
	public HouseChargeRelation findDataById(String relation_id) {
		return houseChargeRelationDao.findDataById(relation_id);
	}

}
