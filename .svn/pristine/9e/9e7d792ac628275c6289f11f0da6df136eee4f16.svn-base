package net.merise.platform.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.BillingType;
import net.merise.platform.service.BillingTypeService;
import net.merise.platform.utils.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/billingType")
@Controller
@Transactional
public class BillingTypeController {

	@Autowired
	private BillingTypeService billingTypeService;
	
	@RequestMapping("/findbillingTypeByBuildingId")
	public @ResponseBody Map<String, Object> findbillingTypeByBuildingId(int page, int rows,String searchValue,String building_id) {
		searchValue=searchValue==null?"":searchValue;
		// 处理分页查询开始记录
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("type_name", searchValue);
		dataMap.put("building_id", building_id);
		// 返回数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", billingTypeService.findCount(dataMap));
		resultMap.put("rows", billingTypeService.findDataByPage(dataMap));
		return resultMap;
	}
	
	@RequestMapping("/findAllByBuilding")
	public @ResponseBody List<BillingType> findAllByBuilding(String building_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("building_id", building_id);
		List<BillingType> billTypeList = billingTypeService.findAllByBuilding(map);
		return billTypeList;
	}
	
	
	
	@RequestMapping("/save")
	@ResponseBody
	public void save(BillingType billingType) {
		billingType.setBilling_type_id(Coder.getSerialCode20());
		billingTypeService.insert(billingType);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(BillingType billingType,String id) {
		billingType.setBilling_type_id(id);
		billingTypeService.update(billingType);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String id) {
		billingTypeService.delete(id);
	}
	
}
