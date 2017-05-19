package net.merise.platform.service.estate.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.BillingTypeDAO;
import net.merise.platform.dao.estate.pojo.BillingType;
import net.merise.platform.service.estate.BillingTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BillingTypeServiceImpl implements BillingTypeService {

	
	@Autowired
	private BillingTypeDAO billingTypeDao;

	@Override
	public int findCount(Map<String, Object> dataMap) {
		return billingTypeDao.findCount(dataMap);
	}

	@Override
	public List<BillingType> findDataByPage(Map<String, Object> dataMap) {
		return billingTypeDao.findDataByPage(dataMap);
	}

	@Override
	public void insert(BillingType billingType) {
		billingTypeDao.insert(billingType);
	}

	@Override
	public void update(BillingType billingType) {
		billingTypeDao.update(billingType);
	}

	@Override
	public void delete(String id) {
		billingTypeDao.delete(id);
	}

	@Override
	public List<BillingType> findAllByBuilding(Map<String, Object> map) {
		return billingTypeDao.findAllByBuilding(map);
	}

}
