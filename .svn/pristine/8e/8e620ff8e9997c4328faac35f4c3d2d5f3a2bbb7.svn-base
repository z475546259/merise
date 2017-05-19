package net.merise.platform.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.PublicFacilities;
import net.merise.platform.service.PublicFacilitiesService;
import net.merise.platform.utils.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 物业公共设施管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/publicFacilities")
@Transactional
public class PublicFacilitiesController {

	@Autowired
	private PublicFacilitiesService publicFacilitiesService;
	
	@RequestMapping("/index")
	public @ResponseBody Map<String, Object> index(int page, int rows,String searchValue,String building_id,String dong){
		searchValue=searchValue==null?"":searchValue;
		// 处理分页查询开始记录
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("facilities_name", searchValue);
		dataMap.put("facilities_type", searchValue);
		dataMap.put("building_id", building_id);
		dataMap.put("house_dong", dong);
		
		List<PublicFacilities> list = publicFacilitiesService.findByBuildingAndDong(dataMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(list != null && list.size() > 0){
			resultMap.put("total", list.size());
		}else{
			resultMap.put("total", "0");
		}
		resultMap.put("rows", list);
		return resultMap;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public void save(PublicFacilities publicFacilities){
		publicFacilities.setPublic_facilities_id(Coder.getSerialCode20());
		publicFacilitiesService.save(publicFacilities);
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public void edit(PublicFacilities publicFacilities){
		publicFacilitiesService.edit(publicFacilities);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String id){
		publicFacilitiesService.delete(id);
	}
	
	@RequestMapping("/findAllByBbuildingId")
	@ResponseBody
	public List<PublicFacilities> findAllByBbuildingId(String building_id){
		return publicFacilitiesService.findAllByBbuildingId(building_id);
	}
}
