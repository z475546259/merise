package net.merise.platform.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.EmployeesEvent;
import net.merise.platform.orm.base.TrainInfo;
import net.merise.platform.service.EmployeesEventService;
import net.merise.platform.service.TrainInfoService;
import net.merise.platform.utils.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 培训信息管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/train")
@Transactional
public class TrainInfoController {
	
	@Autowired
	private TrainInfoService trainInfoService;
	
	@Autowired
	private EmployeesEventService employeesEventService;
	
	
	/**
	 * 查询培训信息
	 * @param page
	 * @param rows
	 * @param searchValue
	 */
	@RequestMapping("/index")
	public @ResponseBody Map<String, Object> findDataByPage(int page, int rows,String searchValue){
		searchValue=searchValue==null?"":searchValue;
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("train_name", searchValue);
		dataMap.put("train_info_name", searchValue);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", trainInfoService.findCount(dataMap));
		resultMap.put("rows", trainInfoService.findDataByPage(dataMap));
		
		return resultMap;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public void save(TrainInfo trainInfo,String time){
		trainInfo.setTrain_id(Coder.getSerialCode20());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			trainInfo.setTrain_time(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		trainInfoService.insert(trainInfo);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String id){
		trainInfoService.delete(id);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public void update(TrainInfo trainInfo,String id,String time){
		trainInfo.setTrain_id(id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			trainInfo.setTrain_time(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		trainInfoService.update(trainInfo);
	}
	
	@RequestMapping("/findEmp")
	public @ResponseBody Map<String, Object> findEmp(String estate_id,String building_id,int page, int rows,String searchValue,String train_id){
		searchValue=searchValue==null?"":searchValue;
		int m = (page - 1) * rows;
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("m", m);
		map.put("n", rows);
		map.put("employees_name", searchValue);
		map.put("estate_id", estate_id);
		map.put("building_id", building_id);
		map.put("remark", train_id);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("rows", trainInfoService.findByEstate_idAndBuilding(map));
		resultMap.put("total", trainInfoService.findByEstate_idAndBuildingCount(map));
		
		return resultMap;
	}
	
	@RequestMapping("/removeEmp")
	@ResponseBody
	public void removeEmp(String employees_id,String remark){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("employees_id", employees_id);
		map.put("remark", remark);
		employeesEventService.delete(map);
	}
	
	@RequestMapping("/addEmp")
	@ResponseBody
	public void addEmp(String train_id,String train_name,String train_time,String employees_id){
		EmployeesEvent event = new EmployeesEvent();
		event.setEvent_id(Coder.getSerialCode20());
		event.setEmployees_id(employees_id);
		event.setEvent_name("培训");
		event.setEvent_context("培训名称："+train_name+"。培训时间："+train_time);
		
		event.setEvent_time(new Date());
		event.setRemark(train_id);
		employeesEventService.insert(event);
	}
}
