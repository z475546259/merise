package net.merise.platform.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.NewsPaperRepairs;
import net.merise.platform.orm.base.NewspaperReport;
import net.merise.platform.service.NewsPaperReportService;
import net.merise.platform.utils.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * 报事报修事件管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/newsPaperReport")
@Transactional
public class NewsPaperReportController {
	
	@Autowired
	private NewsPaperReportService newsPaperReportService;
	
	@RequestMapping("/save")
	@ResponseBody
	public void save(NewspaperReport newsPaperReport,String owner_id,String house_id){
		newsPaperReport.setNewspaper_id(Coder.getSerialCode20());
		newsPaperReport.setOwner_id(owner_id);
		newsPaperReport.setNewspaper_time(new Date());
		newsPaperReport.setNewspaper_status("未处理");
		newsPaperReport.setHouse_id(house_id);
		
		newsPaperReportService.insert(newsPaperReport);
	}
	
	@RequestMapping("/loadReportType")
	public @ResponseBody List<Map<String, Object>> loadReportType(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("text", "报事");
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("text", "报修");
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("text", "其他");
		list.add(map);
		
		return list;
	}
	
	@RequestMapping("/index")
	public @ResponseBody Map<String, Object> index(int page, int rows,String searchValue,String building_id,String house_dong,String newspaper_status,String newspaper_type){
		searchValue=searchValue==null?"":searchValue;
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("owner_name", searchValue);
		dataMap.put("newspaper_name", searchValue);
		dataMap.put("newspaper_type", newspaper_type);
		dataMap.put("newspaper_status", newspaper_status);
		dataMap.put("building_id", building_id);
		dataMap.put("house_dong", house_dong);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("rows",newsPaperReportService.findDataByPage(dataMap));
		resultMap.put("total",newsPaperReportService.findDataByPageCount(dataMap));
				
		return resultMap;
	}
	
	@RequestMapping("/findRepairByReportId")
	public @ResponseBody Map<String, Object> findRepairByReportId(int page, int rows,String searchValue,String report_id){
		searchValue=searchValue==null?"":searchValue;
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("report_id", report_id);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("rows", newsPaperReportService.findRepairsByReportId(dataMap));
		resultMap.put("total", newsPaperReportService.findRepairsByReportIdCount(dataMap));
		
		return resultMap;
	}
	
	@RequestMapping("/saveRepairs")
	@ResponseBody
	public void saveRepairs(NewsPaperRepairs newsPaperRepairs,String newspaper_id,String satisfaction){
		newsPaperRepairs.setRepairs_id(Coder.getSerialCode20());
		newsPaperRepairs.setRepairs_time(new Date());
		if(newsPaperRepairs.getRepairs_type().equals("回访")){
			newsPaperRepairs.setRepairs_remark(newsPaperRepairs.getRepairs_remark() + ",满意度：" + satisfaction);
		}
		newsPaperReportService.saveRepaies(newsPaperRepairs);
		
		NewspaperReport report = new NewspaperReport();
		report.setNewspaper_id(newspaper_id);
		report.setNewspaper_status(newsPaperRepairs.getRepairs_type());
		
		newsPaperReportService.update(report);
	}
	
	@RequestMapping("/endReport")
	@ResponseBody
	public void endReport(String report_id){
		NewspaperReport report = new NewspaperReport();
		report.setNewspaper_id(report_id);
		report.setNewspaper_status("已归档");
		report.setEnd_time(new Date());
		newsPaperReportService.update(report);
		// 增加事件经过
		List<Map<String, Object>> list = newsPaperReportService.findRepairsByReportIdNoLimit(report_id);
		if (null != list && list.size() > 0) {
			NewsPaperRepairs newsPaperRepairs = new NewsPaperRepairs();
			newsPaperRepairs.setRepairs_id(Coder.getSerialCode20());
			newsPaperRepairs.setNewspaper_id(report_id);
			newsPaperRepairs.setEmployees_id(list.get(0).get("employees_id").toString());
			newsPaperRepairs.setRepairs_type("已归档");
			newsPaperRepairs.setRepairs_time(new Date());
			newsPaperRepairs.setRepairs_status("");
			newsPaperRepairs.setRepairs_remark("已归档");
			newsPaperReportService.saveRepaies(newsPaperRepairs);
		}
	}
}
