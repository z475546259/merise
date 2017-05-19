package net.merise.platform.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.merise.platform.service.estate.PayCostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/payCost")
@Transactional
public class PayCostController {
	
	@Autowired
	private PayCostService payCostService;
	
	
	
	/**
	 * 费用查询
	 * @param building_id
	 * @param dong
	 * @param house_id
	 * @return
	 */
	@RequestMapping("/payList")
	@ResponseBody
	public Map<String, Object> payList(String building_id,String dong,String house_location){
		Map<String,Object> params = new HashMap<String,Object>();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		params.put("house_location", house_location);
		params.put("building_id", building_id);
		params.put("house_dong", dong);
		params.put("batch", df.format(new Date()));
		
		Map<String, Object> valueMap = payCostService.payList(params);
		return valueMap;
	}
	
	@RequestMapping("/payCost")
	@ResponseBody
	public String payCost(String building_id,String dong,String house_location,double owe_moneny,double paycost_balance,double now_moneny,double paycost_moneny){
		return payCostService.payCost(building_id,dong,house_location,owe_moneny,paycost_balance,now_moneny,paycost_moneny);
	}
	
	
	/**
	 * 缴费记录
	 * @param page
	 * @param rows
	 * @param building_id
	 * @param dong
	 * @return
	 */
	@RequestMapping("/findPayCostByPage")
	@ResponseBody
	public Map<String, Object> findPayCostByPage(int page, int rows,String building_id,String dong){
		// 处理分页查询开始记录
		int m = (page - 1) * rows;
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("m", m);
		params.put("n", rows);
		params.put("building_id", building_id);
		params.put("house_dong", dong);
		
		// 返回数据
		return payCostService.findPayCostByPage(params);
	}
	
	/**
	 * 每日财务明细
	 * @param page
	 * @param rows
	 * @param building_id
	 * @param dong
	 * @return
	 */
	@RequestMapping("/findPayCostByPageOfDay")
	@ResponseBody
	public Map<String, Object> findPayCostByPageOfDay(int page, int rows,String building_id,String dong,String d){
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// 处理分页查询开始记录
			int m = (page - 1) * rows;
			
			params.put("m", m);
			params.put("n", rows);
			params.put("building_id", building_id);
			params.put("house_dong", dong);
			Date date = new Date();
			if (!"".equals(d)&& null != d) {
				date = df.parse(d);
			}
			params.put("d", date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 返回数据
		return payCostService.findPayCostByPageOfDay(params);
	}
	
	/**
	 * 费用详情
	 * @param page
	 * @param rows
	 * @param building_id
	 * @param dong
	 * @return
	 */
	@RequestMapping("/findPayCostItemByPage")
	@ResponseBody
	public Map<String, Object>findPayCostItemByPage(int page, int rows,String building_id,String dong){
		int m = (page - 1) * rows;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("m", m);
		params.put("n", rows);
		params.put("building_id", building_id);
		params.put("house_dong", dong);
		// 返回数据
		return payCostService.findPayCostItemByPage(params);
	}
	
	/**
	 * 欠费记录
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/payCostLackRecord")
	@ResponseBody
	public Map<String, Object> payCostLackRecord(int page, int rows, String building_id, String house_dong){
		Map<String, Object> params = new HashMap<String, Object>();
		int r = (page - 1) * rows;
		params.put("building_id", building_id);
		params.put("house_dong", house_dong);
		params.put("m", r);
		params.put("n", rows);
		return payCostService.payCostLackRecord(params);
	}
}
