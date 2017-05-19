package net.merise.platform.service.estate.impl;

import java.util.HashMap;
import java.util.Map;

import net.merise.platform.dao.estate.TurnDao;
import net.merise.platform.dao.estate.pojo.Turn;
import net.merise.platform.service.estate.TurnService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TurnServiceImpl implements TurnService {

	@Autowired
	private TurnDao turnDao;

	@Override
	public Map<String, Object> findTurnByPage(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", turnDao.findCount(params));
		map.put("rows", turnDao.findTurnByPage(params));
		return map;
	}

	@Override
	public void saveTrun(Turn turn) {
		turnDao.saveTrun(turn);
	}

}
