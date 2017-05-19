package net.merise.platform.service.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.EmployeesEvent;
import net.merise.platform.orm.mapper.EmployeesEventDAO;
import net.merise.platform.service.EmployeesEventService;

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
