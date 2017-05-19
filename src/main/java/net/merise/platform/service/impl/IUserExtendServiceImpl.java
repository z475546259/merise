package net.merise.platform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.merise.platform.orm.base.TUserExtend;
import net.merise.platform.orm.mapper.IUserExtendDAO;
import net.merise.platform.service.IUserExtendService;

@Transactional
@Service
public class IUserExtendServiceImpl implements IUserExtendService {
	
	@Autowired
	private IUserExtendDAO dao;

	@Override
	public void insert(TUserExtend userExtend) {
		dao.insert(userExtend);
	}

	@Override
	public void update(TUserExtend userExtend) {
		dao.update(userExtend);
	}

	@Override
	public void delete(String extend_id) {
		dao.delete(extend_id);
	}

	@Override
	public void deleteByUserId(String user_id) {
		dao.deleteByUserId(user_id);
	}

	@Override
	public TUserExtend findDataById(String extend_id) {
		return dao.findDataById(extend_id);
	}

	@Override
	public TUserExtend findDataByUserId(String user_id) {
		return dao.findDataByUserId(user_id);
	}

	@Override
	public List<TUserExtend> findAllData(Map<String, Object> map) {
		return dao.findAllData(map);
	}

	@Override
	public int findCount(Map<String, Object> map) {
		return dao.findCount(map);
	}

	@Override
	public List<TUserExtend> findDataByPage(Map<String, Object> map) {
		return dao.findDataByPage(map);
	}

	@Override
	public TUserExtend findDataByOwnerId(String owner_id) {
		return dao.findDataByOwnerId(owner_id);
	}

}
