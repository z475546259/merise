package net.merise.platform.service.estate.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.EstateCodeEetailDAO;
import net.merise.platform.dao.estate.pojo.EstateCodeDetail;
import net.merise.platform.service.estate.EstateCodeEetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EstateCodeEetailServiceImpl implements EstateCodeEetailService {

	@Autowired
	private EstateCodeEetailDAO estateCodeEetailDao;

	@Override
	public int findCount(Map<String, Object> dataMap) {
		return estateCodeEetailDao.findCount(dataMap);
	}

	@Override
	public List<EstateCodeDetail> findDataByPage(Map<String, Object> dataMap) {
		return estateCodeEetailDao.findDataByPage(dataMap);
	}

	@Override
	public List<EstateCodeDetail> findAllData(Map<String, Object> dataMap) {
		return estateCodeEetailDao.findAllData(dataMap);
	}

	@Override
	public void insert(EstateCodeDetail estateCodeDetail) {
		estateCodeEetailDao.insert(estateCodeDetail);
	}

	@Override
	public void update(EstateCodeDetail estateCodeDetail) {
		estateCodeEetailDao.update(estateCodeDetail);
	}

	@Override
	public void delete(String id) {
		estateCodeEetailDao.delete(id);
	} 
	
	@Override
	public List<EstateCodeDetail> findByCodeNameAndBuilding(
			Map<String, Object> dataMap) {
		return estateCodeEetailDao.findByCodeNameAndBuilding(dataMap);
	}

	@Override
	public EstateCodeDetail findDataById(String code_id) {
		return estateCodeEetailDao.findDataById(code_id);
	}

	@Override
	public EstateCodeDetail findByCodeNameAndBuildingAndContent(
			Map<String, Object> dataMap) {
		return estateCodeEetailDao.findByCodeNameAndBuildingAndContent(dataMap);
	}

	@Override
	public List<EstateCodeDetail> findByCodeNameAndEstateId(Map<String, Object> map) {
		return estateCodeEetailDao.findByCodeNameAndEstateId(map);
	}

	@Override
	public int findDeptAllCount(Map<String, Object> dataMap) {
		return estateCodeEetailDao.findDeptAllCount(dataMap);
	}

	@Override
	public List<EstateCodeDetail> findDeptAllByPage(Map<String, Object> dataMap) {
		return estateCodeEetailDao.findDeptAllByPage(dataMap);
	}

	@Override
	public List<EstateCodeDetail> findByCodeNameAndBuildings(
			Map<String, Object> dataMap) {
		return estateCodeEetailDao.findByCodeNameAndBuildings(dataMap);
	}

	@Override
	public EstateCodeDetail findByCodeAndBuddi(String building_id, String code) {
		return estateCodeEetailDao.findByCodeAndBuddi(building_id,code);
	}
}
