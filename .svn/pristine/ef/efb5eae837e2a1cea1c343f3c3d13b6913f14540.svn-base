package net.merise.platform.service.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.ChargeStandard;
import net.merise.platform.orm.base.EstateHouseDetail;
import net.merise.platform.orm.base.HouseChargeRelation;
import net.merise.platform.orm.mapper.HouseChargeRelationDAO;
import net.merise.platform.service.HouseChargeRelationService;

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
