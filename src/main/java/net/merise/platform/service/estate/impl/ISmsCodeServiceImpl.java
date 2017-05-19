package net.merise.platform.service.estate.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.ISmsCodeDAO;
import net.merise.platform.dao.estate.pojo.TSmsCode;
import net.merise.platform.service.estate.ISmsCodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ISmsCodeServiceImpl implements ISmsCodeService {
	
	@Autowired
	private ISmsCodeDAO dao;

	@Override
	public void insert(TSmsCode smsCode) {
		dao.insert(smsCode);
	}

	@Override
	public void update(TSmsCode smsCode) {
		dao.update(smsCode);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public void deleteByMobile(String mobile) {
		dao.deleteByMobile(mobile);
	}

	@Override
	public TSmsCode findDataById(String id) {
		return dao.findDataById(id);
	}

	@Override
	public TSmsCode findDataByMobile(String mobile) {
		return dao.findDataByMobile(mobile);
	}

	@Override
	public List<TSmsCode> findAllData(Map<String, Object> map) {
		return dao.findAllData(map);
	}

	@Override
	public int findCount(Map<String, Object> map) {
		return dao.findCount(map);
	}

	@Override
	public List<TSmsCode> findDataByPage(Map<String, Object> map) {
		return dao.findDataByPage(map);
	}

}
