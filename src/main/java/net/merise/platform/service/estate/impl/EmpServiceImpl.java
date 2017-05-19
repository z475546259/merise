package net.merise.platform.service.estate.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.EmpDAO;
import net.merise.platform.dao.estate.pojo.Emp;
import net.merise.platform.service.estate.EmpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	private EmpDAO empDao;

	@Override
	public int findCount(Map<String, Object> dataMap) {
		return empDao.findCount(dataMap);
	}

	@Override
	public List<Emp> findDataByPage(Map<String, Object> dataMap) {
		return empDao.findDataByPage(dataMap);
	}

	@Override
	public void insert(Emp emp) {
		empDao.insert(emp);
	}

	@Override
	public void update(Emp emp) {
		empDao.update(emp);
	}

	@Override
	public void delete(String id) {
		empDao.delete(id);
	}

	@Override
	public Emp findDataById(String emp_id) {
		return empDao.findDataById(emp_id);
	}

	@Override
	public void updateStatus(String id, String newStatus) {
		empDao.updateStatus(id,newStatus);
	}
	

}
