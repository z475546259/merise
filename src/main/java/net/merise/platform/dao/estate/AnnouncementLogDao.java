package net.merise.platform.dao.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.AnnouncementLog;

import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementLogDao {

	List<AnnouncementLog> findAnnouncementLogByPage(Map<String, Object> params);

	int findCount(Map<String, Object> params);

}
