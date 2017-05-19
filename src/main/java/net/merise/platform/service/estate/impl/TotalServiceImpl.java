package net.merise.platform.service.estate.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.TotalDAO;
import net.merise.platform.dao.estate.pojo.Employees;
import net.merise.platform.service.estate.TotalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TotalServiceImpl implements TotalService {
	
	@Autowired
	private TotalDAO totalDao;

	@Override
	public int findCount(Map<String, Object> dataMap) {
		return totalDao.findCount(dataMap);
	}

	@Override
	public List<Employees> findDataByPage(Map<String, Object> dataMap) {
		return totalDao.findDataByPage(dataMap);
	}
	
	@Override
	public List<Map<String, Object>> findEventAndEmployees(
			Map<String, Object> map) {
		return totalDao.findEventAndEmployees(map);
	}

	@Override
	public List<Map<String, Object>> findRzMonth(Map<String, Object> dataMap) {
		return totalDao.findRzMonth(dataMap);
	}

	@Override
	public List<Map<String, Object>> findLzMonth(Map<String, Object> dataMap) {
		return totalDao.findLzMonth(dataMap);
	}

}
