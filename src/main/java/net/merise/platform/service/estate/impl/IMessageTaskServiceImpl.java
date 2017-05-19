package net.merise.platform.service.estate.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.IMessageTaskDAO;
import net.merise.platform.dao.estate.pojo.TMessageTask;
import net.merise.platform.service.estate.IMessageTaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IMessageTaskServiceImpl implements IMessageTaskService {
	
	@Autowired
	private IMessageTaskDAO dao;

	@Override
	public void insert(TMessageTask messageTask) {
		dao.insert(messageTask);
	}

	@Override
	public void update(TMessageTask messageTask) {
		dao.update(messageTask);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public TMessageTask findDataById(String id) {
		return dao.findDataById(id);
	}

	@Override
	public List<TMessageTask> findAllData() {
		return dao.findAllData();
	}

	@Override
	public int findCount() {
		return dao.findCount();
	}

	@Override
	public List<TMessageTask> findDataByPage() {
		return dao.findDataByPage();
	}

	@Override
	public List<TMessageTask> findDataByParams(Map<String, Object> map) {
		return dao.findDataByParams(map);
	}

	@Override
	public List<TMessageTask> findDataByHour(String message_hour) {
		return dao.findDataByHour(message_hour);
	}

}
