package net.merise.platform.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.merise.platform.service.TotalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * hr报表统计
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/total")
@Transactional
public class TotalController {
	
	@Autowired
	private TotalService totalService;
	
	/**
	 * 入职人数，离职人数统计
	 * @param building_id
	 * @param request
	 * @return
	 */
	@RequestMapping("/createTu")
	public @ResponseBody List<Object> createTu(String building_id,String begin_time,String after_time){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("start_time", begin_time);
		dataMap.put("end_time", after_time);
		dataMap.put("building_id", building_id);
		List<Map<String, Object>> findRzMonth = totalService.findRzMonth(dataMap);
		List<Map<String, Object>> findLzMonth = totalService.findLzMonth(dataMap);
		List<Object> resultMap = new ArrayList<Object>();
		resultMap.add(findRzMonth);
		resultMap.add(findLzMonth);
		
		return resultMap;
	}
	
	@RequestMapping("/findBirthdayByDate")
	public @ResponseBody Map<String, Object> findBirthdayByDate(int page, int rows,String building_id) {
		// 处理分页查询开始记录
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("building_id", building_id);
		// 返回数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", totalService.findCount(dataMap));
		resultMap.put("rows", totalService.findDataByPage(dataMap));
		return resultMap;
	}

	@RequestMapping("/findCount")
	public @ResponseBody List<Map<String, Object>> findCount(String begin_time,String after_time,String building_id){
		Map<String, Object> map = new HashMap<String,Object>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		map.put("remark", "岗位变动");
		try {
			map.put("begin_time", sdf.parse(begin_time+" 00:00:00"));
			map.put("after_time", sdf.parse(after_time+" 23:59:59"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		map.put("building_id", building_id);
		
		List<Map<String, Object>> mapdata = totalService.findEventAndEmployees(map);;
		
		int count = 0;
		int upCount = 0;
		for (Map<String, Object> map2 : mapdata) {
			count += Integer.parseInt(map2.get("num").toString());
			if(map2.get("remark").equals("升职")){
				upCount = Integer.parseInt(map2.get("num").toString());
			}
		}
		
		mapdata = new ArrayList<Map<String, Object>>();
		map = new HashMap<String,Object>();
		map.put("remark", "异动人数");
		map.put("num", count);
		mapdata.add(map);
		map = new HashMap<String,Object>();
		map.put("remark", "升职");
		map.put("num", upCount);
		mapdata.add(map);
		
		return mapdata;
	}
}
