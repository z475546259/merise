package net.merise.platform.service.impl;

import java.util.List;

import net.merise.platform.orm.base.TUserOpinion;
import net.merise.platform.orm.mapper.UserOpinionDAO;
import net.merise.platform.service.UserOpinionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserOpinionServiceImpl implements UserOpinionService {
	
	@Autowired
	private UserOpinionDAO dao;

	@Override
	public void insert(TUserOpinion userOpinion) {
		dao.insert(userOpinion);
	}

	@Override
	public void update(TUserOpinion userOpinion) {
		dao.update(userOpinion);
	}

	@Override
	public void delete(String opinion_id) {
		dao.delete(opinion_id);
	}

	@Override
	public TUserOpinion findDataById(String opinion_id) {
		return dao.findDataById(opinion_id);
	}

	@Override
	public List<TUserOpinion> findAllData() {
		return dao.findAllData();
	}

	@Override
	public int findCount() {
		return dao.findCount();
	}

}
