package net.merise.platform.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.merise.platform.orm.base.EstateBuildingDetail;
import net.merise.platform.orm.base.EstateCodeDetail;
import net.merise.platform.orm.base.EstateHouseDetail;
import net.merise.platform.orm.base.EstateInfoDetail;
import net.merise.platform.orm.base.EstateOwnerDetail;
import net.merise.platform.orm.base.OwnerHouseDetail;
import net.merise.platform.service.EstateBuildingDetailService;
import net.merise.platform.service.EstateCodeEetailService;
import net.merise.platform.service.EstateHouseDetailService;
import net.merise.platform.service.EstateInfoDetailService;
import net.merise.platform.service.OwnerService;
import net.merise.platform.utils.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 业主信息管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/owner")
@Transactional
public class OwnerController {

	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private EstateInfoDetailService estateService;
	
	@Autowired
	private EstateBuildingDetailService estateBuildingDetailService;
	
	@Autowired
	private EstateHouseDetailService estateHouseDetailService;
	
	@Autowired
	private EstateCodeEetailService estateCodeEetailService;
	
	/**
	 * 房屋转让
	 */
	@RequestMapping("/updateTransferHouse")
	@ResponseBody
	public void updateTransferHouse(String termination_time_value,String termination_reason,String id_value_transfer,String id_value_house,String id_value_owner){
		Map<String,Object> datamap=new HashMap<String,Object>();
		datamap.put("termination_reason", termination_reason);
		datamap.put("id", id_value_transfer);
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			if(!("".equals(termination_time_value))){
				Date termination_time = sdf.parse(termination_time_value);
				datamap.put("termination_time", termination_time);
			} 
			ownerService.updateTransferHouse(datamap);
			EstateHouseDetail ehd = estateHouseDetailService.findDataById(id_value_house);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("building_id", ehd.getBuilding_id());
			map.put("code_name", "房屋状态");
			map.put("code_content", "待售");
			EstateCodeDetail updateEcd=estateCodeEetailService.findByCodeNameAndBuildingAndContent(map);
			ehd.setHouse_status(updateEcd.getCode_id());
			estateHouseDetailService.update(ehd);
			
			ownerService.updateStatusByOwnerId(id_value_owner,"1");//修改该业主的状态为失效  1为失效
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除车牌信息
	 * @param house_id
	 */
	@RequestMapping("/deleteCarNum")
	@ResponseBody
	public void deleteCarNum(String house_id){
		ownerService.updateCarNumByHouseId(house_id,"","");
	}
	
	/**
	 * 修改或新增车牌信息
	 * @param house_id
	 * @param car_num
	 * @param charge_standard
	 */
	@RequestMapping("/editCarNum")
	@ResponseBody
	public void editCarNum(String house_id,String car_num,String charge_standard){
		ownerService.updateCarNumByHouseId(house_id,car_num,charge_standard);
	}
	

	/**
	 * 根据house_id查询这个车位信息
	 * @param house_id
	 * @return
	 */
	@RequestMapping("/findCarNumUserByHouseId")
	public @ResponseBody List<OwnerHouseDetail> findCarNumUserByHouseId(String house_id,String house_room) {
		OwnerHouseDetail ohd=ownerService.findLiveUserByHouseId(house_id);
		
		List<OwnerHouseDetail> list=new ArrayList<OwnerHouseDetail>();
		if(ohd!=null&&ohd.getCar_num()!=null&&!("").equals(ohd.getCar_num())){
			String car_num = ohd.getCar_num();
			String charge_standard = ohd.getCharge_standard();
			OwnerHouseDetail newohd=new OwnerHouseDetail();
			newohd.setId("0");
			newohd.setCar_num(car_num);
			newohd.setCharge_standard(charge_standard);
			newohd.setHouse_id(house_id);
			list.add(newohd);
		}
		return list;
	}
	
	/**
	 * 删除居住人员
	 * @param newOhd
	 * @param house_id
	 * @throws IOException 
	 */
	@RequestMapping("/deleteLiveUser")
	@ResponseBody
	public void deleteLiveUser(String id,String house_id){
		int updateId = Integer.parseInt(id);// 修改的坐标
		OwnerHouseDetail oldohd=ownerService.findLiveUserByHouseId(house_id);//旧值
		String live_names = oldohd.getLive_name();
		String live_mobiles = oldohd.getLive_mobile();
		String live_idcards = oldohd.getLive_idcard();
		String living_emails = oldohd.getLiving_email();
		String owner_relations = oldohd.getOwner_relation();
		
		String[] live_name_str = live_names.split(",");
		live_name_str[updateId]="";
		String new_live_name_str = getStr(live_name_str).replaceAll("[,]+", ",");
		oldohd.setLive_name(new_live_name_str);
		
		String[] live_mobile_str = live_mobiles.split(",");
		live_mobile_str[updateId]="";
		String new_live_mobile_str = getStr(live_mobile_str).replaceAll("[,]+", ",");
		oldohd.setLive_mobile(new_live_mobile_str);
		
		String[] live_idcard_str = live_idcards.split(",");
		live_idcard_str[updateId]="";
		String new_live_idcard_str = getStr(live_idcard_str).replaceAll("[,]+", ",");
		oldohd.setLive_idcard(new_live_idcard_str);
		
		String[] living_email_str = living_emails.split(",");
		living_email_str[updateId]="";
		String new_living_email_str = getStr(living_email_str).replaceAll("[,]+", ",");
		oldohd.setLiving_email(new_living_email_str);
		
		String[] owner_relation_str = owner_relations.split(",");
		owner_relation_str[updateId]="";
		String new_owner_relation_str = getStr(owner_relation_str).replaceAll("[,]+", ",");
		oldohd.setOwner_relation(new_owner_relation_str);
		
		ownerService.updateLiveOwnerHouseDetailByHouse_id(oldohd);
	}
	
	
	/**
	 * 修改居住人员
	 * @param newOhd
	 * @param house_id
	 */
	@RequestMapping("/editLiveUser")
	@ResponseBody
	public void editLiveUser(OwnerHouseDetail newOhd,String house_id) {
		int updateId = Integer.parseInt(newOhd.getId());// 修改的坐标
		OwnerHouseDetail oldohd=ownerService.findLiveUserByHouseId(house_id);//旧值
		String live_names = oldohd.getLive_name();
		String live_mobiles = oldohd.getLive_mobile();
		String live_idcards = oldohd.getLive_idcard();
		String living_emails = oldohd.getLiving_email();
		String owner_relations = oldohd.getOwner_relation();
		
		String[] live_name_str = live_names.split(",");
		live_name_str[updateId]=newOhd.getLive_name();
		oldohd.setLive_name(getStr(live_name_str));
		
		String[] live_mobile_str = live_mobiles.split(",");
		live_mobile_str[updateId]=newOhd.getLive_mobile();
		oldohd.setLive_mobile(getStr(live_mobile_str));
		
		String[] live_idcard_str = live_idcards.split(",");
		live_idcard_str[updateId]=newOhd.getLive_idcard();
		oldohd.setLive_idcard(getStr(live_idcard_str));
		
		String[] living_email_str = living_emails.split(",");
		living_email_str[updateId]=newOhd.getLiving_email();
		oldohd.setLiving_email(getStr(living_email_str));
		
		String[] owner_relation_str = owner_relations.split(",");
		owner_relation_str[updateId]=newOhd.getOwner_relation();
		oldohd.setOwner_relation(getStr(owner_relation_str));
		
		ownerService.updateLiveOwnerHouseDetailByHouse_id(oldohd);
	}

	private String getStr(String[] arr) {
		String a="";
		for (int i = 0; i < arr.length; i++) {
			a+=arr[i]+",";
		}
		return a;
	}
	
	/**
	 * 新增居住人员
	 * @param newOhd
	 * @param house_id
	 */
	@RequestMapping("/saveLiveUser")
	@ResponseBody
	public void saveLiveUser(OwnerHouseDetail newOhd,String house_id) {
		OwnerHouseDetail oldohd=ownerService.findLiveUserByHouseId(house_id);
		if(oldohd!=null&&oldohd.getLive_name()!=null&&!("").equals(oldohd.getLive_name())){
			String live_names = oldohd.getLive_name();
			oldohd.setLive_name(live_names+newOhd.getLive_name()+",");
			
			String live_mobiles = oldohd.getLive_mobile();
			oldohd.setLive_mobile(live_mobiles+newOhd.getLive_mobile()+",");
			
			String live_idcards = oldohd.getLive_idcard();
			oldohd.setLive_idcard(live_idcards+newOhd.getLive_idcard()+",");
			
			String living_emails = oldohd.getLiving_email();
			oldohd.setLiving_email(living_emails+newOhd.getLiving_email()+",");
			
			String owner_relations = oldohd.getOwner_relation();
			oldohd.setOwner_relation(owner_relations+newOhd.getOwner_relation()+",");
			ownerService.updateLiveOwnerHouseDetailByHouse_id(oldohd);
		}else{
			oldohd=new OwnerHouseDetail();
			oldohd.setLive_name(newOhd.getLive_name()+",");
			oldohd.setLive_mobile(newOhd.getLive_mobile()+",");
			oldohd.setLive_idcard(newOhd.getLive_idcard()+",");
			oldohd.setLiving_email(newOhd.getLiving_email()+",");
			oldohd.setOwner_relation(newOhd.getOwner_relation()+",");
			oldohd.setHouse_id(house_id);
			ownerService.updateLiveOwnerHouseDetailByHouse_id(oldohd);
		}
		
	}
	
	
	
	/**
	 * 根据house_id查询这个房子下的居住人信息，以,对象存储
	 * @param house_id
	 * @return
	 */
	@RequestMapping("/findLiveUserByHouseId")
	public @ResponseBody List<OwnerHouseDetail> findLiveUserByHouseId(String house_id) {
		OwnerHouseDetail ohd=ownerService.findLiveUserByHouseId(house_id);
		
		List<OwnerHouseDetail> list=new ArrayList<OwnerHouseDetail>();
		if(ohd!=null&&ohd.getLive_name()!=null&&!("").equals(ohd.getLive_name())){
			String live_names = ohd.getLive_name();
			String live_mobiles = ohd.getLive_mobile();
			String live_idcards = ohd.getLive_idcard();
			String living_emails = ohd.getLiving_email();
			String owner_relations = ohd.getOwner_relation();
			
			OwnerHouseDetail newohd=null;
			String[] live_name_str = live_names.split(",");
			String[] live_mobile_str = live_mobiles.split(",");
			String[] live_idcard_str = live_idcards.split(",");
			String[] living_email_str = living_emails.split(",");
			String[] owner_relation_str = owner_relations.split(",");
			for (int i = 0; i < live_name_str.length; i++) {
				newohd=new OwnerHouseDetail();
				newohd.setId(i+"");
				newohd.setLive_name(live_name_str[i]);
				newohd.setLive_mobile(live_mobile_str[i]);
				newohd.setLive_idcard(live_idcard_str[i]);
				newohd.setLiving_email(living_email_str[i]);
				newohd.setOwner_relation(owner_relation_str[i]);
				newohd.setHouse_id(house_id);
				list.add(newohd);
			}
		}
		return list;
	}
	
	@RequestMapping("/findOwnerByDong")
	public @ResponseBody Map<String, Object> findOwnerByDong(int page, int rows,String searchValue,String building_id,String dong) {
		searchValue=searchValue==null?"":searchValue;
		// 处理分页查询开始记录
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m); 
		dataMap.put("n", rows);
		dataMap.put("owner_name", searchValue);
		dataMap.put("owner_mobile", searchValue);
		dataMap.put("owner_idcard", searchValue);
		dataMap.put("building_id", building_id);
		dataMap.put("dong", dong);
		// 返回数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", ownerService.findCountOwnerByDong(dataMap));
		resultMap.put("rows", ownerService.findOwnerByDong(dataMap));
		return resultMap;
	}

	@RequestMapping("/save")
	public @ResponseBody String save(EstateOwnerDetail estateOwnerDetail,HttpServletRequest request) {
		String s="-1";
		try {
			String owner_idcard = estateOwnerDetail.getOwner_idcard();
			//判断该身份证是否以存在
			EstateOwnerDetail oldeod=ownerService.findByIdcard(owner_idcard);
			String owner_id="";
			if(oldeod==null){
				owner_id = Coder.getSerialCode20();
				estateOwnerDetail.setOwner_id(owner_id);
				estateOwnerDetail.setStatus("0");
				ownerService.insert(estateOwnerDetail);
			}else{
				owner_id=oldeod.getOwner_id();
				//该用户已存在，提示用户
				s="该业主已存在，信息未覆盖，如需修改，请手动修改!";
			}
			
			OwnerHouseDetail ownerHouseDetail=new OwnerHouseDetail();
			ownerHouseDetail.setId(Coder.getSerialCode20());
			ownerHouseDetail.setOwner_id(owner_id);
			String house_id = request.getParameter("house_id_value");
			ownerHouseDetail.setHouse_id(house_id);
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String contract_time = request.getParameter("contract_time_value");
			String house_time = request.getParameter("house_time_value");
			String decorate_time = request.getParameter("decorate_time_value");
			String live_time = request.getParameter("live_time_value");
			
			if(!("".equals(contract_time))){
				ownerHouseDetail.setContract_time(sdf.parse(contract_time));
			}
			if(!("".equals(house_time))){
				ownerHouseDetail.setHouse_time(sdf.parse(house_time));
			}
			if(!("".equals(decorate_time))){
				ownerHouseDetail.setDecorate_time(sdf.parse(decorate_time));
			}
			if(!("".equals(live_time))){
				ownerHouseDetail.setLive_time(sdf.parse(live_time));
			}
			
			EstateHouseDetail ehd=estateHouseDetailService.findDataById(house_id);
			String house_sort = ehd.getHouse_sort();
			EstateCodeDetail houseSortEcd = estateCodeEetailService.findDataById(house_sort);
			
			if("车库".equals(houseSortEcd.getCode_content())){
				//出售的是车库
				ownerHouseDetail.setGarage_start_date(new Date());
			}
			ownerService.insertOwnerHouseDetail(ownerHouseDetail);
			
			//修改房屋状态为售出
			ehd.setHouse_status(estateOwnerDetail.getHouse_status());
			estateHouseDetailService.update(ehd);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return s;
		
	}
	
	@RequestMapping("/bind")
	@ResponseBody
	public String bind(String building_id,String house_id,String owner_idcard){
		String s = "";
		//获取身份证号获取业主
		EstateOwnerDetail estateOwnerDetail=ownerService.findByIdcard(owner_idcard);
		if (estateOwnerDetail==null) {
			s="该业主信息不存在!";
		}else{
			//根据房屋id获取房屋信息
			EstateHouseDetail ehd=estateHouseDetailService.findDataById(house_id);
			//获取房屋的物业类型
			EstateCodeDetail houseSortEcd = estateCodeEetailService.findByCodeAndBuddi(building_id,"售出");
			//建立房屋关系
			OwnerHouseDetail ownerHouseDetail=new OwnerHouseDetail();
			ownerHouseDetail.setId(Coder.getSerialCode20());
			ownerHouseDetail.setOwner_id(estateOwnerDetail.getOwner_id());
			ownerHouseDetail.setHouse_id(house_id);
			if("车库".equals(houseSortEcd.getCode_content())){
				//出售的是车库
				ownerHouseDetail.setGarage_start_date(new Date());
			}
			ehd.setHouse_status(houseSortEcd.getCode_id());
			ownerService.insertOwnerHouseDetail(ownerHouseDetail);
			//修改房屋状态
			estateHouseDetailService.update(ehd);
			s="绑定成功!";
		}
		return s;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(EstateOwnerDetail estateOwnerDetail, String id,HttpServletRequest request) {
		estateOwnerDetail.setOwner_id(id);
		ownerService.update(estateOwnerDetail);
		
		OwnerHouseDetail ownerHouseDetail=new OwnerHouseDetail();
		String id_value = request.getParameter("id_value");
		ownerHouseDetail.setId(id_value);
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String contract_time = request.getParameter("contract_time_value");
			String house_time = request.getParameter("house_time_value");
			String decorate_time = request.getParameter("decorate_time_value");
			String live_time = request.getParameter("live_time_value");
			
			
			if(!("".equals(contract_time))){
				ownerHouseDetail.setContract_time(sdf.parse(contract_time));
			}
			if(!("".equals(house_time))){
				ownerHouseDetail.setHouse_time(sdf.parse(house_time));
			}
			if(!("".equals(decorate_time))){
				ownerHouseDetail.setDecorate_time(sdf.parse(decorate_time));
			}
			if(!("".equals(live_time))){
				ownerHouseDetail.setLive_time(sdf.parse(live_time));
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ownerService.updateOwnerHouseDetail(ownerHouseDetail);
		
	}

	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String id,String owner_id,String house_id) {
		ownerService.delete(owner_id);
		ownerService.deleteOwnerHouseDetail(id);
		//修改房屋状态为售出
		EstateHouseDetail ehd=estateHouseDetailService.findDataById(house_id);
		String code_id = ehd.getHouse_status();
		EstateCodeDetail ecd=estateCodeEetailService.findDataById(code_id);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("building_id", ecd.getBuilding_id());
		map.put("code_name", "房屋状态");
		map.put("code_content", "待售");
		EstateCodeDetail updateEcd=estateCodeEetailService.findByCodeNameAndBuildingAndContent(map);
		ehd.setHouse_status(updateEcd.getCode_id());
		estateHouseDetailService.update(ehd);
	}
	
	@RequestMapping("/getTreeNode")
	@ResponseBody
	public List<Map<String, Object>> getTreeNode(){
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("estate_name", "");
		List<EstateInfoDetail> estateList = estateService.findAllData(dataMap);
		for (EstateInfoDetail estateInfoDetail : estateList) {
			 Map<String, Object> node = new HashMap<String, Object>(); 
			 node.put("id", estateInfoDetail.getEstate_id());  
		     node.put("text", estateInfoDetail.getEstate_name());  
		     //根据物业获取下面所有的楼盘
		     List<EstateBuildingDetail> buildList=estateBuildingDetailService.findEstateBuildingByEstateId(estateInfoDetail.getEstate_id());		     
		     if(buildList!=null&&buildList.size()!=0){
		    	 List<Map<String, Object>> buildMapList = new ArrayList<Map<String, Object>>();
		    	 for (EstateBuildingDetail building : buildList) {
			    	 Map<String, Object> buildingNode = new HashMap<String, Object>(); 
			    	 buildingNode.put("id", building.getBuilding_id());  
			    	 buildingNode.put("text", building.getBuilding_name());  
			    	 
			    	 List<String> listDong = estateHouseDetailService.findDong(building.getBuilding_id());
			    	 
			    	 if(listDong != null && listDong.size() > 0){
			    		 List<Map<String, Object>> dongMapList = new ArrayList<Map<String, Object>>();
			    		 for (int i = 0; i < listDong.size(); i++) {
				    		 Map<String, Object> mapDong = new HashMap<String, Object>();
				    		 mapDong.put("id", building.getBuilding_id());
				    		 mapDong.put("text", listDong.get(i));
				    		 dongMapList.add(mapDong);
				    	 }
			    		 
			    		 buildingNode.put("children", dongMapList);
			    	 }
			    	 buildMapList.add(buildingNode);
				 }
		    	 node.put("children", buildMapList);
		     }
		     maplist.add(node);
		}
		return maplist;
	}
}
