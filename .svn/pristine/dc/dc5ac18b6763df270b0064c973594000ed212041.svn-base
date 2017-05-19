package net.merise.platform.web.controller;

import java.util.HashMap;
import java.util.Map;

import net.merise.platform.orm.base.EstateConfig;
import net.merise.platform.service.EstateConfigService;
import net.merise.platform.utils.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/estateConfig")
@Transactional
public class EstateConfigController {
	
	@Autowired
	private EstateConfigService estateConfigService;
	
	@RequestMapping("/find")
	@ResponseBody
	public Map<String, Object> find(int page, int rows,String owner_id,String owner_table){
		// 处理分页查询开始记录
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("owner_id", owner_id);
		dataMap.put("owner_table", owner_table);
		// 返回数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", estateConfigService.findCount(owner_id,owner_table));
		resultMap.put("rows", estateConfigService.findDataByPage(dataMap));
		return resultMap;
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public void edit(String config_id,String col_name,String col_context){
		EstateConfig oldec=estateConfigService.findDataById(config_id);
		oldec.setCol_name(col_name);
		oldec.setCol_context(col_context);
		estateConfigService.update(oldec);
	}
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String config_id){
		estateConfigService.delete(config_id);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public void save(String col_name,String col_context,String owner_id,String owner_table){
		EstateConfig estateConfig = new EstateConfig();
		estateConfig.setConfig_id(Coder.getSerialCode20());
		estateConfig.setOwner_id(owner_id);
		estateConfig.setOwner_table(owner_table);
		estateConfig.setCol_name(col_name);
		estateConfig.setCol_context(col_context);
		
		estateConfigService.insert(estateConfig);
	}
}
