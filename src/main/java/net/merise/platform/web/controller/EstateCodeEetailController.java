package net.merise.platform.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.EstateBuildingDetail;
import net.merise.platform.orm.base.EstateCodeDetail;
import net.merise.platform.service.EstateBuildingDetailService;
import net.merise.platform.service.EstateCodeEetailService;
import net.merise.platform.utils.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 配置信息管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/estateCodeEetail")
@Transactional
public class EstateCodeEetailController {

	@Autowired
	private EstateCodeEetailService estateCodeEetailService;
	
	@Autowired
	private EstateBuildingDetailService estateBuildingDetailService;
	
	@RequestMapping("/findDeptByBuildingId")
	public @ResponseBody Map<String, Object> findDeptByBuildingId(int page, int rows,String searchValue,String building_id) {
		searchValue=searchValue==null?"":searchValue;
		// 处理分页查询开始记录
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("code_content", searchValue);
		dataMap.put("building_id", building_id);
		// 返回数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", estateCodeEetailService.findDeptAllCount(dataMap));
		resultMap.put("rows", estateCodeEetailService.findDeptAllByPage(dataMap));
		return resultMap;
	}
	
	@RequestMapping("/index")
	public @ResponseBody Map<String, Object> findAll(int page, int rows,String searchValue) {
		searchValue=searchValue==null?"":searchValue;
		// 处理分页查询开始记录
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("code_name", searchValue);
		// 返回数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", estateCodeEetailService.findCount(dataMap));
		resultMap.put("rows", estateCodeEetailService.findDataByPage(dataMap));
		return resultMap;
	}
	
	@RequestMapping("/findEstateBuildingDetailAll")
	public @ResponseBody List<EstateBuildingDetail> findEstateBuildingDetailAll(){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("building_name","");
		List<EstateBuildingDetail> list=estateBuildingDetailService.findAllData(dataMap);//查询所有的楼盘
		return list;
	}

	@RequestMapping("/save")
	@ResponseBody
	public void save(EstateCodeDetail estateCodeDetail) {
		estateCodeDetail.setCode_id(Coder.getSerialCode20());
		estateCodeEetailService.insert(estateCodeDetail);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(EstateCodeDetail estateCodeDetail,String id) {
		estateCodeDetail.setCode_id(id);
		estateCodeEetailService.update(estateCodeDetail);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String id) {
		estateCodeEetailService.delete(id);
	}
	
	@RequestMapping("/findByCodeNameAndBuildingId")
	public @ResponseBody List<EstateCodeDetail> findByCodeNameAndBuildingId(String code_name,String building_id){
		
 		Map<String, Object> map = new HashMap<String,Object>();
		map.put("code_name", code_name);
		map.put("building_id", building_id);
		
		List<EstateCodeDetail> list = estateCodeEetailService.findByCodeNameAndBuilding(map);
		
		return list;
	}
	
}
