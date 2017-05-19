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

import net.merise.platform.orm.base.ChargeStandard;
import net.merise.platform.orm.base.EstateHouseDetail;
import net.merise.platform.orm.base.HouseChargeRelation;
import net.merise.platform.orm.base.OwnerBill;
import net.merise.platform.orm.base.OwnerHouseDetail;
import net.merise.platform.service.ChargeStandardService;
import net.merise.platform.service.EstateHouseDetailService;
import net.merise.platform.service.HouseChargeRelationService;
import net.merise.platform.service.OwnerBillService;
import net.merise.platform.service.OwnerService;
import net.merise.platform.utils.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 房屋收费项目管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/houseChargeRelation")
@Transactional
public class HouseChargeRelationController {

	@Autowired
	private HouseChargeRelationService houseChargeRelationService;
	
	@Autowired
	private ChargeStandardService chargeStandardService;
	
	@Autowired
	private EstateHouseDetailService estateHouseDetailService;
	
	@Autowired
	private OwnerBillService ownerBillService;
	
	@Autowired
	private OwnerService ownerService;
	
	BigDecimal fen = new BigDecimal("100");
	
	/**
	 * 查询该房屋下面的所有收费项目
	 * @param page
	 * @param rows
	 * @param searchValue
	 * @param building_id
	 * @param dong
	 * @return
	 */
	@RequestMapping("/findchargeRelationByHouseId")
	public @ResponseBody Map<String, Object> findchargeRelationByHouseId(int page, int rows,String searchValue,String house_id) {
		// 处理分页查询开始记录
		searchValue=searchValue==null?"":searchValue;
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("house_id", house_id);
		
		List<ChargeStandard> chargeStandards = houseChargeRelationService.findchargeRelationByHouseId(dataMap);
		for (ChargeStandard chargeStandard : chargeStandards) {
			chargeStandard.setStandard_price(chargeStandard.getStandard_price().divide(fen));
		}
		
		// 返回数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("total", chargeStandards.size());
		resultMap.put("rows", chargeStandards);
		return resultMap;
	}
	
	
	/**
	 * 查询栋下面的已经签合同的房子
	 * @param page
	 * @param rows
	 * @param searchValue
	 * @param building_id
	 * @param dong
	 * @return
	 */
	@RequestMapping("/findHouseDataByPage")
	public @ResponseBody Map<String, Object> findHouseDataByPage(int page, int rows,String searchValue,String building_id,String dong) {
		// 处理分页查询开始记录
		searchValue=searchValue==null?"":searchValue;
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("house_floor", searchValue);
		dataMap.put("id", building_id);
		dataMap.put("dong", dong);
		
		// 返回数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", houseChargeRelationService.findHouseDataCount(dataMap));
		resultMap.put("rows", houseChargeRelationService.findHouseDataByPage(dataMap));
		return resultMap;
	}
	
	@RequestMapping("/save")
	public @ResponseBody String save(ChargeStandard chargeStandard,String building_id) {
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("name", chargeStandard.getProject_name());
		map.put("building_id", building_id);
		ChargeStandard chargeStandards = chargeStandardService.findByBuildingIdAndName(map);
		if (null == chargeStandard.getPremium()) {
			chargeStandard.setPremium(Double.parseDouble("0"));
		}
		String status = "true";
		if(chargeStandards == null){//判断名称是否存在
			String standard_id = Coder.getSerialCode20();
			chargeStandard.setStandard_id(standard_id);
			chargeStandard.setCreate_time(new Date());
			chargeStandard.setStandard_price(chargeStandard.getStandard_price().multiply(fen));//按分存
			HouseChargeRelation hcr=new HouseChargeRelation();
			hcr.setRelation_id(Coder.getSerialCode20());
			hcr.setStandard_id(standard_id);
			hcr.setHouse_id(chargeStandard.getHouse_id());
			hcr.setRemark("0");
			chargeStandardService.save(chargeStandard);
			houseChargeRelationService.save(hcr);
			// 账单添加
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			EstateHouseDetail detail = estateHouseDetailService.findDataById(chargeStandard.getHouse_id());
			map.put("house_dong", detail.getHouse_dong());
			map.put("house_units", detail.getHouse_units());
			map.put("starFloor", detail.getHouse_floor());
			map.put("endFloor", detail.getHouse_floor());
			// 账单处理
			OwnerBill ownerBill = new OwnerBill();
			ownerBill.setHouse_id(detail.getHouse_id());
			OwnerHouseDetail ownerHouseDetail = ownerService.findLiveUserByHouseId(detail.getHouse_id());
			if ( null != ownerHouseDetail) {
				ownerBill.setOwner_id(ownerHouseDetail.getOwner_id());
			} else {
				ownerBill.setOwner_id("");
			}
			ownerBill.setStandard_id(chargeStandard.getStandard_id());
			ownerBill.setStandard_name(chargeStandard.getProject_name());
			ownerBill.setHouse_number_id("");
			ownerBill.setStatus(0);
			if ("月".equals(chargeStandard.getCharge_unit())) {
				int num = Integer.parseInt(chargeStandard.getCharge_num());
				for (int i = 0; i < num; i++) {
					ownerBill.setBill_id(UUID.randomUUID().toString());
					Calendar temp = Calendar.getInstance();
					temp.set(Calendar.MONTH, temp.get(Calendar.MONTH) + i);
					ownerBill.setBatch(sdf.format(temp.getTime()));
					double price = chargeStandard.getStandard_price().divide(fen).doubleValue();
					double count = price * Double.parseDouble(chargeStandard.getCoefficient()) + chargeStandard.getPremium() * Double.parseDouble(detail.getHouse_floor());
					ownerBill.setMoney(decimalFormat.format(count));
					ownerBillService.insert(ownerBill);
				}
			}
		}else{
			status = "false";
		}
		return status;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(ChargeStandard chargeStandard,String id) {
		chargeStandard.setStandard_id(id);
		chargeStandardService.update(chargeStandard);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String relation_id) {
		HouseChargeRelation houseChargeRelation = houseChargeRelationService.findDataById(relation_id);
		houseChargeRelationService.updateStatus(relation_id);
		houseChargeRelationService.delete(relation_id);
		chargeStandardService.delete(houseChargeRelation.getStandard_id());
		// 删除账单
		ownerBillService.deleteByStandardId(houseChargeRelation.getStandard_id());
	}
	
}
