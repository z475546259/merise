package net.merise.platform.service.estate.impl;

import java.util.HashMap;
import java.util.Map;

import net.merise.platform.dao.estate.AnnouncementLogDao;
import net.merise.platform.service.estate.AnnouncementLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnnouncementLogServiceImpl implements AnnouncementLogService {
	
	@Autowired
	private AnnouncementLogDao announcementLogDao;

	@Override
	public Map<String, Object> findAnnouncementLogByPage(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", announcementLogDao.findCount(params));
		map.put("rows", announcementLogDao.findAnnouncementLogByPage(params));
		return map;
	}

}
