package net.merise.platform.service.estate.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.EmployeesDAO;
import net.merise.platform.dao.estate.pojo.Employees;
import net.merise.platform.service.estate.EmployeesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmployeesServiceImpl implements EmployeesService {

	@Autowired
	private EmployeesDAO employeesDao;

	@Override
	public int findCount(Map<String, Object> dataMap) {
		return employeesDao.findCount(dataMap);
	}

	@Override
	public List<Employees> findDataByPage(Map<String, Object> dataMap) {
		return employeesDao.findDataByPage(dataMap);
	}

	@Override
	public void insert(Employees employees) {
		employeesDao.insert(employees);
	}

	@Override
	public void update(Employees employees) {
		employeesDao.update(employees);
	}

	@Override
	public void delete(String id) {
		employeesDao.delete(id);
	}

	@Override
	public Employees findDataById(String employees_id) {
		return employeesDao.findDataById(employees_id);
	}

	@Override
	public List<Employees> findByDeptId(String id) {
		return employeesDao.findByDeptId(id);
	}
	
	
}
