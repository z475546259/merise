package net.merise.platform.service.estate.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.ChargeStandardDAO;
import net.merise.platform.dao.estate.pojo.ChargeStandard;
import net.merise.platform.service.estate.ChargeStandardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ChargeStandardServiceImpl implements ChargeStandardService {

	@Autowired
	private ChargeStandardDAO chargeStandardDao;

	@Override
	public void save(ChargeStandard chargeStandard) {
		chargeStandardDao.save(chargeStandard);
	}

	@Override
	public void update(ChargeStandard chargeStandard) {
		chargeStandardDao.update(chargeStandard);
	}

	@Override
	public void delete(String id) {
		chargeStandardDao.delete(id);
	}

	@Override
	public ChargeStandard findById(String id) {
		return chargeStandardDao.findById(id);
	}

	@Override
	public List<ChargeStandard> findDataByPage(Map<String, Object> map) {
		return chargeStandardDao.findDataByPage(map);
	}

	@Override
	public List<ChargeStandard> findDataByPageCount(Map<String, Object> map) {
		return chargeStandardDao.findDataByPageCount(map);
	}

	@Override
	public ChargeStandard findByBuildingIdAndName(Map<String, Object> map) {
		return chargeStandardDao.findByBuildingIdAndName(map);
	}
	
	
}
