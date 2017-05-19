package net.merise.platform.service.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.SysUser;
import net.merise.platform.orm.mapper.SysUserDAO;
import net.merise.platform.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserDAO dao;

	@Override
	public void insert(SysUser sysUser) {
		dao.insert(sysUser);
	}

	@Override
	public void update(SysUser sysUser) {
		dao.update(sysUser);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public SysUser findDataById(String id) {
		return dao.findDataById(id);
	}

	@Override
	public SysUser findDataByMobile(String mobile) {
		return dao.findDataByMobile(mobile);
	}

	@Override
	public List<SysUser> findAllData() {
		return dao.findAllData();
	}

	@Override
	public int findCount(Map<String, Object> map) {
		return dao.findCount(map);
	}

	@Override
	public List<SysUser> findDataByPage(Map<String, Object> map) {
		return dao.findDataByPage(map);
	}

}
