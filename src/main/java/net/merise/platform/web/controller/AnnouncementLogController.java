package net.merise.platform.web.controller;

import java.util.HashMap;
import java.util.Map;

import net.merise.platform.service.AnnouncementLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/announcementLog")
@Transactional
public class AnnouncementLogController {
	
	@Autowired
	private AnnouncementLogService announcementLogService;

	/**
	 * 分页公告记录
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/findAnnouncementLogByPage")
	@ResponseBody
	public Map<String, Object> findAnnouncementLogByPage(int page, int rows){
		int m = (page - 1) * rows;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("m", m);
		params.put("n", rows);
		return announcementLogService.findAnnouncementLogByPage(params);
	}
}
