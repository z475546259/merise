package net.merise.platform.service.estate.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.TrainInfoDAO;
import net.merise.platform.dao.estate.pojo.TrainInfo;
import net.merise.platform.service.estate.TrainInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrainInfoServiceImpl implements TrainInfoService {

	@Autowired
	private TrainInfoDAO dao;
	
	@Override
	public void insert(TrainInfo trainInfo) {
		dao.insert(trainInfo);
	}

	@Override
	public void update(TrainInfo trainInfo) {
		dao.update(trainInfo);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public int findCount(Map<String, Object> dataMap) {
		return dao.findCount(dataMap);
	}

	@Override
	public List<TrainInfo> findDataByPage(Map<String, Object> dataMap) {
		return dao.findDataByPage(dataMap);
	}

	@Override
	public TrainInfo findDataById(String id) {
		return dao.findDataById(id);
	}

	@Override
	public List<Map<String, Object>> findByEstate_idAndBuilding(
			Map<String, Object> map) {
		return dao.findByEstate_idAndBuilding(map);
	}

	@Override
	public int findByEstate_idAndBuildingCount(Map<String, Object> map) {
		return dao.findByEstate_idAndBuildingCount(map);
	}

}
