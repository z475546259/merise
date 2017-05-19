package net.merise.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.Announcement;
import net.merise.platform.orm.mapper.AnnouncementDao;
import net.merise.platform.service.AnnouncementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnnouncementServiceImpl implements AnnouncementService {

	@Autowired
	private AnnouncementDao announcementDao;
	
	@Override
	public Map<String, Object> findAnnouncementByPage(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", announcementDao.findCount(params));
		map.put("rows", announcementDao.findAnnouncementByPage(params));
		return map;
	}

	@Override
	public void save(Announcement ann) {
		announcementDao.save(ann);
	}

	@Override
	public void delete(String id) {
		announcementDao.delete(id);
	}

	@Override
	public void edit(Announcement ann) {
		announcementDao.edit(ann);
	}

	@Override
	public List<Announcement> findDataByBuildingId(String building_id) {
		return announcementDao.findDataByBuildingId(building_id);
	}

}
