package net.merise.platform.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.merise.platform.service.estate.PayCostItemService;
import net.merise.platform.utils.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/paycostItem")
public class PayCostItemController {
	
	@Autowired
	private PayCostItemService payCostItemService;
	
	
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
	public Map<String, Object> findPayCostItemByPage(int page, int rows,String building_id,String dong, String searchValue){
		int m = (page - 1) * rows;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("m", m);
		params.put("n", rows);
		params.put("building_id", building_id);
		params.put("house_dong", dong);
		params.put("paycost_time", Coder.NullToBlank(searchValue));
		// 返回数据
		return payCostItemService.findPayCostItemByPage(params);
	}

}
