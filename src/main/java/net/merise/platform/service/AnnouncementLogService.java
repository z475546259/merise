package net.merise.platform.service;

import java.util.Map;


public interface AnnouncementLogService {

	Map<String, Object> findAnnouncementLogByPage(Map<String, Object> params);

}
