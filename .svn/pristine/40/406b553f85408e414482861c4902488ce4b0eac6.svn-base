package net.merise.platform.service.estate.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.AppointServiceDAO;
import net.merise.platform.dao.estate.pojo.AppointService;
import net.merise.platform.service.estate.AppointServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AppointServiceServiceImpl implements AppointServiceService {

	@Autowired
	private AppointServiceDAO appointServiceDao;

	@Override
	public void insert(AppointService appointService) {
		appointServiceDao.insert(appointService);
	}

	@Override
	public int findCount(Map<String, Object> dataMap) {
		return appointServiceDao.findCount(dataMap);
	}

	@Override
	public List<AppointService> findDataByPage(Map<String, Object> dataMap) {
		return appointServiceDao.findDataByPage(dataMap);
	}

	@Override
	public AppointService findDataById(String id) {
		return appointServiceDao.findDataById(id);
	}

	@Override
	public void update(AppointService oldservice) {
		appointServiceDao.update(oldservice);
	}

	@Override
	public void delete(String service_id) {
		appointServiceDao.delete(service_id);
	}

	@Override
	public List<AppointService> findDataByBuildingId(String building_id) {
		return appointServiceDao.findDataByBuildingId(building_id);
	}
	
	
}
