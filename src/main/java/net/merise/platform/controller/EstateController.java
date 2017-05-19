package net.merise.platform.controller;

import java.util.HashMap;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.EstateInfoDetail;
import net.merise.platform.service.estate.EstateInfoDetailService;
import net.merise.platform.utils.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/estate")
@Transactional
public class EstateController {

	@Autowired
	private EstateInfoDetailService estateService;

	@RequestMapping("/index")
	public @ResponseBody Map<String, Object> findAllEstate(int page, int rows,String searchValue){
		searchValue=searchValue==null?"":searchValue;
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("estate_name", searchValue);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", estateService.findCount(dataMap));
		resultMap.put("rows", estateService.findDataByPage(dataMap));
		
		return resultMap;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public JSONObject save(EstateInfoDetail estate){
		JSONObject result = new JSONObject();
		try {
			estate.setEstate_id(Coder.getSerialCode20());
			estateService.insert(estate);
			result.put("status", true);
			result.put("message", "[OK]");
		} catch (Exception e) {
			result.put("status", false);
			result.put("message", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public JSONObject update(EstateInfoDetail estate,String id){
		JSONObject result = new JSONObject();
		try {
			estate.setEstate_id(id);
			estateService.update(estate);
			result.put("status", true);
			result.put("message", "[OK]");
		} catch (Exception e) {
			result.put("status", false);
			result.put("message", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public JSONObject delete(String id){
		JSONObject result = new JSONObject();
		try {
			estateService.delete(id);
			result.put("status", true);
			result.put("message", "[OK]");
		} catch (Exception e) {
			result.put("message", e.getMessage());
			result.put("status", false);
		}
		return result;
	}
}
