package net.merise.platform.web.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.merise.platform.orm.base.BillingType;
import net.merise.platform.orm.base.ChargeStandard;
import net.merise.platform.orm.base.EstateBuildingDetail;
import net.merise.platform.orm.base.EstateHouseDetail;
import net.merise.platform.orm.base.EstateInfoDetail;
import net.merise.platform.orm.base.HouseChargeRelation;
import net.merise.platform.orm.base.OwnerBill;
import net.merise.platform.orm.base.OwnerHouseDetail;
import net.merise.platform.service.BillingTypeService;
import net.merise.platform.service.ChargeStandardService;
import net.merise.platform.service.EstateBuildingDetailService;
import net.merise.platform.service.EstateHouseDetailService;
import net.merise.platform.service.EstateInfoDetailService;
import net.merise.platform.service.HouseChargeRelationService;
import net.merise.platform.service.OwnerBillService;
import net.merise.platform.service.OwnerService;
import net.merise.platform.utils.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/chargeStandard")
@Transactional
public class ChargeStandardController {
	
	@Autowired
	private EstateInfoDetailService estateInfoDetailService;
	
	@Autowired
	private EstateBuildingDetailService estateBuildingDetailService;
	
	@Autowired
	private ChargeStandardService chargeStandardService;
	
	@Autowired
	private BillingTypeService billingTypeService;
	
	@Autowired
	private EstateHouseDetailService estateHouseDetailService;
	
	@Autowired
	private HouseChargeRelationService houseChargeRelationService;
	
	@Autowired
	private OwnerBillService ownerBillService;
	
	@Autowired
	private OwnerService ownerService;
	
	@RequestMapping("/findDataByPage")
	public @ResponseBody Map<String, Object> findChargeStandardByBillingType(int page,int rows,String searchValue,String billing_type_id,String billing_type_name){
		searchValue=searchValue==null?"":searchValue;
		// 处理分页查询开始记录
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("billing_type_id", billing_type_id);
		dataMap.put("project_name", searchValue);
		
		BigDecimal fen = new BigDecimal("100");
		List<ChargeStandard> list=chargeStandardService.findDataByPage(dataMap);
		
		for (ChargeStandard chargeStandard : list) {
			chargeStandard.setBilling_type_name(billing_type_name);
			chargeStandard.setStandard_price(chargeStandard.getStandard_price().divide(fen));
		}
		
		// 返回数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", chargeStandardService.findDataByPageCount(dataMap));
		resultMap.put("rows", list);
		return resultMap;
	}
	
	@RequestMapping("/save")
	public @ResponseBody String save(ChargeStandard chargeStandard,String building_id){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("name", chargeStandard.getProject_name());
		map.put("building_id", building_id);
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		// 数据查询
		ChargeStandard chargeStandards = chargeStandardService.findByBuildingIdAndName(map);
		String status = "true";
		if(chargeStandards == null){//判断名称是否存在
			chargeStandard.setStandard_id(Coder.getSerialCode20());
			chargeStandard.setCreate_time(new Date());
			BigDecimal fen = new BigDecimal("100");
			chargeStandard.setStandard_price(chargeStandard.getStandard_price().multiply(fen));//按分存
//			map.put("house_dong", chargeStandard.getHouse_dong());
//			map.put("house_units", chargeStandard.getHouse_units());
//			map.put("starFloor", chargeStandard.getStart_floor());
//			map.put("endFloor", chargeStandard.getEnd_floor());
//			//插入选择楼层内的所有房屋计费标准和账单
//			List<EstateHouseDetail> listHouse = estateHouseDetailService.findHouseByBuildingDongUnitsFloor(map);
//			for (EstateHouseDetail estateHouseDetail : listHouse) {
//				// 计费标准处理
//				HouseChargeRelation relation = new HouseChargeRelation();
//				relation.setRelation_id(Coder.getSerialCode20());
//				relation.setStandard_id(chargeStandard.getStandard_id());
//				relation.setHouse_id(estateHouseDetail.getHouse_id());
//				relation.setRemark("0");
//				houseChargeRelationService.save(relation);
//				// 账单处理
//				OwnerBill ownerBill = new OwnerBill();
//				ownerBill.setHouse_id(estateHouseDetail.getHouse_id());
//				OwnerHouseDetail ownerHouseDetail = ownerService.findLiveUserByHouseId(estateHouseDetail.getHouse_id());
//				if ( null != ownerHouseDetail) {
//					ownerBill.setOwner_id(ownerHouseDetail.getOwner_id());
//				} else {
//					ownerBill.setOwner_id("");
//				}
//				ownerBill.setStandard_id(chargeStandard.getStandard_id());
//				ownerBill.setStandard_name(chargeStandard.getProject_name());
//				ownerBill.setHouse_number_id("");
//				ownerBill.setStatus(0);
//				if ("月".equals(chargeStandard.getCharge_unit())) {
//					int num = Integer.parseInt(chargeStandard.getCharge_num());
//					for (int i = 0; i < num; i++) {
//						ownerBill.setBill_id(UUID.randomUUID().toString());
//						Calendar temp = Calendar.getInstance();
//						temp.set(Calendar.MONTH, temp.get(Calendar.MONTH) + i);
//						ownerBill.setBatch(sdf.format(temp.getTime()));
//						double price = chargeStandard.getStandard_price().divide(fen).doubleValue();
//						double count = price * Double.parseDouble(chargeStandard.getCoefficient()) + chargeStandard.getPremium() * Double.parseDouble(estateHouseDetail.getHouse_floor());
//						ownerBill.setMoney(decimalFormat.format(count));
//						ownerBillService.insert(ownerBill);
//					}
//				}
				// 年和季度暂不处理
//				if ("季度".equals(chargeStandard.getCharge_unit())) {
//					int num = Integer.parseInt(chargeStandard.getCharge_num());
//					ownerBill.setBatch("");
//					ownerBill.setMoney("");
//					ownerBillService.insert(ownerBill);
//				}
//				if ("年".equals(chargeStandard.getCharge_unit())) {
//					int num = Integer.parseInt(chargeStandard.getCharge_num());
//					ownerBill.setBatch("");
//					ownerBill.setMoney("");
//					ownerBillService.insert(ownerBill);
//				}
//			}
			chargeStandardService.save(chargeStandard);
		}else{
			status = "false";
		}
		return status;
	}

	@RequestMapping("/update")
	public @ResponseBody String update(ChargeStandard chargeStandard,String building_id){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("name", chargeStandard.getProject_name());
		map.put("building_id", building_id);
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		ChargeStandard chargeStandards = chargeStandardService.findByBuildingIdAndName(map);
		BigDecimal fen = new BigDecimal("100");
		chargeStandard.setStandard_price(chargeStandard.getStandard_price().multiply(fen));//按分存
		String status = "true";
		if(chargeStandards == null || chargeStandards.getStandard_id().equals(chargeStandard.getStandard_id())){
			//修改选择楼层内的所有房屋计费标准
			chargeStandardService.update(chargeStandard);
			//删除账单
//			ownerBillService.deleteByStandardId(chargeStandard.getStandard_id());
//			//新增账单
//			map.put("house_dong", chargeStandard.getHouse_dong());
//			map.put("house_units", chargeStandard.getHouse_units());
//			map.put("starFloor", chargeStandard.getStart_floor());
//			map.put("endFloor", chargeStandard.getEnd_floor());
//			List<EstateHouseDetail> listHouse = estateHouseDetailService.findHouseByBuildingDongUnitsFloor(map);
//			for (EstateHouseDetail estateHouseDetail : listHouse) {
//				// 账单处理
//				OwnerBill ownerBill = new OwnerBill();
//				ownerBill.setHouse_id(estateHouseDetail.getHouse_id());
//				OwnerHouseDetail ownerHouseDetail = ownerService.findLiveUserByHouseId(estateHouseDetail.getHouse_id());
//				if ( null != ownerHouseDetail) {
//					ownerBill.setOwner_id(ownerHouseDetail.getOwner_id());
//				} else {
//					ownerBill.setOwner_id("");
//				}
//				ownerBill.setStandard_id(chargeStandard.getStandard_id());
//				ownerBill.setStandard_name(chargeStandard.getProject_name());
//				ownerBill.setHouse_number_id("");
//				ownerBill.setStatus(0);
//				if ("月".equals(chargeStandard.getCharge_unit())) {
//					int num = Integer.parseInt(chargeStandard.getCharge_num());
//					for (int i = 0; i < num; i++) {
//						ownerBill.setBill_id(UUID.randomUUID().toString());
//						Calendar temp = Calendar.getInstance();
//						temp.set(Calendar.MONTH, temp.get(Calendar.MONTH) + i);
//						ownerBill.setBatch(sdf.format(temp.getTime()));
//						double price = chargeStandard.getStandard_price().divide(fen).doubleValue();
//						double count = price * Double.parseDouble(chargeStandard.getCoefficient()) + chargeStandard.getPremium() * Double.parseDouble(estateHouseDetail.getHouse_floor());
//						ownerBill.setMoney(decimalFormat.format(count));
//						ownerBillService.insert(ownerBill);
//					}
//				}
//			}
		}else{
			status = "false";
		}
		return status;
	}
	@RequestMapping("/delete")
	public void delete(String id){
		houseChargeRelationService.delete(id);
		//删除选择楼层内的所有房屋计费标准
		chargeStandardService.delete(id);
		// 删除账单
		ownerBillService.deleteByStandardId(id);
	}
	
	/**
	 * 物业楼盘树
	 * @return JSON格式数据
	 */
	@RequestMapping("/tree")
	public @ResponseBody JSONArray tree() {
		JSONArray result = new JSONArray();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("estate_name", "");
		List<EstateInfoDetail> estateList = estateInfoDetailService.findAllData(dataMap);
		for (EstateInfoDetail estateInfoDetail : estateList) {
			JSONObject object = new JSONObject();
			object.put("id", estateInfoDetail.getEstate_id());
			object.put("text", estateInfoDetail.getEstate_name());
			JSONObject attr = new JSONObject();
			attr.put("level", "estate");
			object.put("attributes", attr);
			// 查询物业的楼盘
			List<EstateBuildingDetail> buildList=estateBuildingDetailService.findEstateBuildingByEstateId(estateInfoDetail.getEstate_id());
			JSONArray buildArray = new JSONArray();
			for (EstateBuildingDetail estateBuildingDetail : buildList) {
				JSONObject buildObject = new JSONObject();
				buildObject.put("id", estateBuildingDetail.getBuilding_id());
				buildObject.put("text", estateBuildingDetail.getBuilding_name());
				JSONObject buildAttr = new JSONObject();
				buildAttr.put("level", "build");
				buildObject.put("attributes", buildAttr);
				// 查询楼盘下的计费类别
				Map<String, Object> map = new HashMap<String,Object>();
				map.put("building_id", estateBuildingDetail.getBuilding_id());
				List<BillingType> billTypeList = billingTypeService.findAllByBuilding(map);
				JSONArray dongArray = new JSONArray();
				for (BillingType type : billTypeList) {
					JSONObject codeObject = new JSONObject();
					codeObject.put("id", type.getBilling_type_id());
					codeObject.put("text", type.getType_name());
					codeObject.put("building_id", type.getBuilding_id());
					JSONObject codeAttr = new JSONObject();
					codeAttr.put("level", "jflb");
					codeObject.put("attributes", codeAttr);
					dongArray.add(codeObject);
				}
				buildObject.put("children", dongArray);
				buildArray.add(buildObject);
			}
			object.put("children", buildArray);
			result.add(object);
		}
		return result;
	}
}
