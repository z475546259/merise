package net.merise.platform.service.estate.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.EmployeesEventDAO;
import net.merise.platform.dao.estate.pojo.EmployeesEvent;
import net.merise.platform.service.estate.EmployeesEventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmployeesEventServiceImpl implements EmployeesEventService {

	@Autowired
	private EmployeesEventDAO employeesEventDao;

	@Override
	public void insert(EmployeesEvent event) {
		employeesEventDao.insert(event);
	}

	@Override
	public List<EmployeesEvent> findEventPageByEmployeesId(Map<String, Object> dataMap) {
		return employeesEventDao.findEventPageByEmployeesId(dataMap);
	}

	@Override
	public int findCountByEmployeesId(Map<String, Object> dataMap) {
		return employeesEventDao.findCountByEmployeesId(dataMap);
	}

	@Override
	public void delete(Map<String, Object> map) {
		employeesEventDao.delete(map);
	}
	
}
