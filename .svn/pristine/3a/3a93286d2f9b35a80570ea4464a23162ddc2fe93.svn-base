package net.merise.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.merise.platform.orm.base.TUserEmployees;
import net.merise.platform.orm.mapper.UserEmployeesDAO;
import net.merise.platform.service.UserEmployeeService;

@Service
@Transactional
public class UserEmployeeServiceImpl implements UserEmployeeService {
	
	@Autowired
	private UserEmployeesDAO dao;

	@Override
	public void insert(TUserEmployees tUserEmployees) {
		dao.insert(tUserEmployees);
	}

	@Override
	public void deleteByEmployeesId(String employees_id) {
		dao.deleteByEmployeesId(employees_id);
	}

	@Override
	public TUserEmployees findDataByEmployeesId(String employees_id) {
		return dao.findDataByEmployeesId(employees_id);
	}

	@Override
	public TUserEmployees findDataByUserId(String user_id) {
		return dao.findDataByUserId(user_id);
	}

	@Override
	public void deleteByUserId(String user_id) {
		dao.deleteByUserId(user_id);
	}

}
