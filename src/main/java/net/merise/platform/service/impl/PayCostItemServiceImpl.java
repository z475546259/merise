package net.merise.platform.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.merise.platform.orm.mapper.PayCostItemDao;
import net.merise.platform.service.PayCostItemService;
import net.merise.platform.utils.Coder;

@Service
public class PayCostItemServiceImpl implements PayCostItemService {
	
	@Autowired
	private PayCostItemDao payCostItemDao;

	@Override
	public Map<String, Object> findPayCostItemByPage(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String, Object>> list = payCostItemDao.findPayCostItemByPage(params);
		List<Map<String, Object>> result = new ArrayList<>();
		String paycost_time = params.get("paycost_time").toString();
		if ("".equals(paycost_time)) {
			result = list;
		} else {
			for (Map<String, Object> map2 : list) {
				String time = dateFormat.format(map2.get("paycost_time"));
				if (time.equals(paycost_time)) {
					result.add(map2);
				}
			}
		}
		map.put("total", result.size());
		map.put("rows", result);
		return map;
	}

}
