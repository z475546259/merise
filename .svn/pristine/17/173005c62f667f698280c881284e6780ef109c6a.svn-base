package net.merise.platform.service.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.Announcement;

public interface AnnouncementService {

	Map<String, Object> findAnnouncementByPage(Map<String, Object> params);

	void save(Announcement ann);

	void delete(String id);

	void edit(Announcement ann);

	List<Announcement> findDataByBuildingId(String building_id);
}
