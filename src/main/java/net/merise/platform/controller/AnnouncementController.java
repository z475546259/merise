package net.merise.platform.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.Announcement;
import net.merise.platform.service.estate.AnnouncementService;
import net.merise.platform.utils.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/announcement")
@Transactional
public class AnnouncementController {
	
	@Autowired
	private AnnouncementService announcementService;
	
	/**
	 * 分页获取公告
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/findAnnouncementByPage")
	@ResponseBody
	public Map<String, Object> findAnnouncementByPage(int page, int rows){
		int m = (page - 1) * rows;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("m", m);
		params.put("n", rows);
		return announcementService.findAnnouncementByPage(params);
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(Announcement ann) throws ParseException{
		ann.setId(Coder.getSerialCode20());
		ann.setPriority(0);
		ann.setStatus(0);
		ann.setCreatetime(new Date());
		if (null == ann.getSend_time()) {
			ann.setSend_time(new Date());
		}
		//ann.setSend_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time));
		announcementService.save(ann);
		return "保存成功!";
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public String edit(String id,Announcement ann){
		ann.setId(id);
		ann.setPriority(0);
		ann.setStatus(0);
		ann.setCreatetime(new Date());
		if (null == ann.getSend_time()) {
			ann.setSend_time(new Date());
		}
		announcementService.edit(ann);
		return "更新成功!";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(String id){
		announcementService.delete(id);
		return "删除成功!";
	}
}
