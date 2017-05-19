
package net.merise.platform.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.Bill;
import net.merise.platform.dao.estate.pojo.ChargeStandard;
import net.merise.platform.dao.estate.pojo.EstateCodeDetail;
import net.merise.platform.dao.estate.pojo.EstateHouseDetail;
import net.merise.platform.dao.estate.pojo.OwnerBill;
import net.merise.platform.dao.estate.pojo.OwnerHouseDetail;
import net.merise.platform.service.estate.BillService;
import net.merise.platform.service.estate.ChargeStandardService;
import net.merise.platform.service.estate.EstateCodeEetailService;
import net.merise.platform.service.estate.EstateHouseDetailService;
import net.merise.platform.service.estate.HouseChargeRelationService;
import net.merise.platform.service.estate.OwnerBillService;
import net.merise.platform.service.estate.OwnerService;
import net.merise.platform.utils.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 票据记录管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/bill")
@Transactional
public class BillController {

	@Autowired
	private BillService billService;
	
	@Autowired
	private EstateHouseDetailService estateHouseDetailService;
	
	@Autowired
	private ChargeStandardService chargeStandardService;
	
	@Autowired
	private HouseChargeRelationService houseChargeRelationService;
	
	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private OwnerBillService ownerBillService;
	
	@Autowired
	private EstateCodeEetailService estateCodeEetailService;
	
	@RequestMapping("/save")
	@ResponseBody
	public void save(Bill bill){
		bill.setBill_id(Coder.getSerialCode20());
		bill.setBill_time(new Date());
		billService.save(bill);
	}
	
	@RequestMapping("/findDataByPage")
	public @ResponseBody Map<String, Object> findDataByPage(int page, int rows,String building_id){
		int m = (page - 1) * rows;
		Map<String, Object> mapData = new HashMap<String,Object>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m", m);
		map.put("n", rows);
		map.put("building_id", building_id);
		
		List<Bill> list = billService.findDataByPage(map);
		
		mapData.put("rows", list);
		mapData.put("total", billService.findPageCount(map));
		
		return mapData;
	}
	
	@RequestMapping("/findHouseData")
	public @ResponseBody Map<String, Object> findHouseData(int page, int rows,String building_id, String searchValue) {
		searchValue = Coder.NullToBlank(searchValue);
		// 处理分页查询开始记录
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("building_id", building_id);
		dataMap.put("house_location", searchValue);
		// 返回数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", estateHouseDetailService.findHouseBillDataByBuildingIdCount(dataMap));
		resultMap.put("rows", estateHouseDetailService.findHouseBillDataByBuildingId(dataMap));
		return resultMap;
	}
	
	@RequestMapping("/findHouseDong")
	public @ResponseBody JSONArray findHouseDong(String building_id) {
		List<Map<String, Object>> listDong = estateHouseDetailService.findHouseDongAndType(building_id);
		JSONArray dongArray = new JSONArray();
		for (Map<String, Object> dong : listDong) {
			JSONObject dongObject = new JSONObject();
			dongObject.put("name", dong.get("house_dong").toString()+"-"+dong.get("code_content").toString());
			dongObject.put("value", dong.get("house_dong").toString());
			dongObject.put("group", "楼盘栋列表");
			dongObject.put("editor", "checkbox");
			dongArray.add(dongObject);
		}
		return dongArray;
	}
	
	@RequestMapping("/findBillType")
	public @ResponseBody List<Map<String, Object>> findBillType(String building_id) {
		List<Map<String, Object>> listDong = estateHouseDetailService.findChargeDataByBuildingId(building_id);
		return listDong;
	}
	
	/**
	 * 批量账单生成
	 * @param building_id 楼盘编号
	 * @param houseDong 栋号
	 * @param houseCharge 收费项目
	 * @param expire_date 账单到期时间
	 * @return 操作状态
	 */
	@RequestMapping("/buildBatchBill")
	public @ResponseBody JSONObject buildBatchBill(String building_id, String houseDong, String houseCharge, String expire_date) {
		JSONObject result = new JSONObject();
		houseDong = Coder.NullToBlank(houseDong);
		houseCharge = Coder.NullToBlank(houseCharge);
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		BigDecimal fen = new BigDecimal("100");
		int number = 0;
		long time = Calendar.getInstance().getTimeInMillis() / 1000;
		try {
			if (!"".equals(houseDong) && !"".equals(houseCharge)) {
				String[] dong = houseDong.split(",");
				String[] charge = houseCharge.split(",");
				StringBuilder searchDong = new StringBuilder();
				for (int i = 0; i < dong.length; i++) {
					if ((i+1) == dong.length) {
						searchDong.append("'"+dong[i]+"'");
					} else {
						searchDong.append("'"+dong[i]+"',");
					}
				}
				List<EstateHouseDetail> estateHouseDetails = estateHouseDetailService.findHouseDataByBuildingIdAndDong(building_id, searchDong.toString());
				for (int i = 0; i < charge.length; i++) {
					ChargeStandard chargeStandard = chargeStandardService.findById(charge[i]);
					EstateCodeDetail estateCodeDetail = estateCodeEetailService.findDataById(chargeStandard.getDenominated_unit());
					for (EstateHouseDetail estateHouseDetail : estateHouseDetails) {
						OwnerHouseDetail ownerHouseDetail = ownerService.findLiveUserByHouseId(estateHouseDetail.getHouse_id());
						// 判断房屋是否有业主
						if ( null != ownerHouseDetail) {
							// 计费标准处理
//							HouseChargeRelation relation = new HouseChargeRelation();
//							relation.setRelation_id(Coder.getSerialCode20());
//							relation.setStandard_id(chargeStandard.getStandard_id());
//							relation.setHouse_id(estateHouseDetail.getHouse_id());
//							relation.setRemark("0");
//							houseChargeRelationService.save(relation);
							// 账单处理
							OwnerBill ownerBill = new OwnerBill();
							ownerBill.setHouse_id(estateHouseDetail.getHouse_id());
							ownerBill.setOwner_id(ownerHouseDetail.getOwner_id());
							ownerBill.setStandard_id(chargeStandard.getStandard_id());
							ownerBill.setStandard_name(chargeStandard.getProject_name());
							ownerBill.setHouse_number_id("");
							ownerBill.setStatus(0);
							if ("月".equals(chargeStandard.getCharge_unit())) {
								int num = Integer.parseInt(chargeStandard.getCharge_num());
								for (int m = 0; m < num; m++) {
									number += 1;
									ownerBill.setBill_id(buildNumber(number, time));
									Calendar temp = Calendar.getInstance();
									temp.set(Calendar.MONTH, temp.get(Calendar.MONTH) + m);
									ownerBill.setBatch(sdf.format(temp.getTime()));
									ownerBill.setExpire_date(dateFormat.parse(expire_date));
									// 删除已有的账单
									Map<String, Object> deleteMap = new HashMap<>();
									deleteMap.put("house_id", ownerBill.getHouse_id());
									deleteMap.put("owner_id", ownerBill.getOwner_id());
									deleteMap.put("batch", ownerBill.getBatch());
									deleteMap.put("standard_id", ownerBill.getStandard_id());
									ownerBillService.deleteByBatch(deleteMap);
									if ("单价*系数".equals(estateCodeDetail.getCode_content())) {
										double price = chargeStandard.getStandard_price().divide(fen).doubleValue(); //单价
										//单价*系数
										double count = price * Double.parseDouble(chargeStandard.getCoefficient()); 
										//+每层加价
										double total = count + chargeStandard.getPremium() * Double.parseDouble(estateHouseDetail.getHouse_floor()); 
										ownerBill.setMoney(decimalFormat.format(total));
									}
									if ("建筑面积*单价*系数".equals(estateCodeDetail.getCode_content())) {
										double price = chargeStandard.getStandard_price().divide(fen).doubleValue(); //单价
										//单价*系数*建筑面积
										double count = price * Double.parseDouble(chargeStandard.getCoefficient()) * Double.parseDouble(estateHouseDetail.getHouse_build_area());
										 //+每层加价
										double total = count + chargeStandard.getPremium() * Double.parseDouble(estateHouseDetail.getHouse_floor());
										ownerBill.setMoney(decimalFormat.format(total));
									}
									if ("套内面积*单价*系数".equals(estateCodeDetail.getCode_content())) {
										double price = chargeStandard.getStandard_price().divide(fen).doubleValue(); //单价
										//单价*系数*套内面积
										double count = price * Double.parseDouble(chargeStandard.getCoefficient()) * Double.parseDouble(estateHouseDetail.getHouse_in_area());
										 //+每层加价
										double total = count + chargeStandard.getPremium() * Double.parseDouble(estateHouseDetail.getHouse_floor());
										ownerBill.setMoney(decimalFormat.format(total));
									}
									if ("(建筑面积-套内面积)*单价*系数".equals(estateCodeDetail.getCode_content())) {
										double price = chargeStandard.getStandard_price().divide(fen).doubleValue(); //单价
										//(建筑面积-套内面积)*单价*系数
										double count = (Double.parseDouble(estateHouseDetail.getHouse_build_area()) - Double.parseDouble(estateHouseDetail.getHouse_in_area())) * price * Double.parseDouble(chargeStandard.getCoefficient());
										 //+每层加价
										double total = count + chargeStandard.getPremium() * Double.parseDouble(estateHouseDetail.getHouse_floor());
										ownerBill.setMoney(decimalFormat.format(total));
									}
									ownerBillService.insert(ownerBill);
								}
							}
						}
					}
				}
				result.put("status", true);
				result.put("message", "[OK]");
			} else {
				result.put("status", false);
				result.put("message", "房屋栋号或收费项目不能为空!");
			}
		} catch (Exception e) {
			result.put("status", false);
			result.put("message", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 账单流水号
	 * @param number
	 * @return
	 */
	private String buildNumber(int number, long timeMillis) {
		String result = "";
		if (number > 0 && number < 10) {
			result = timeMillis+"0000"+number;
		}
		if (number > 10 && number < 100) {
			result = timeMillis+"000"+number;
		}
		if (number > 100 && number < 1000) {
			result = timeMillis+"00"+number;
		}
		if (number > 1000 && number < 10000) {
			result = timeMillis+"0"+number;
		}
		if (number > 10000 && number < 100000) {
			result = timeMillis+""+number;
		}
		return result;
	}
	
	/**
	 * 单个订单生成
	 * @param building_id 楼盘编号
	 * @param houseSelect 房屋编号
	 * @param chargeSelect 收费项目
	 * @param expire_date 到期时间
	 * @return 操作状态
	 */
	@RequestMapping("/buildSingleBill")
	public @ResponseBody JSONObject buildSingleBill(String building_id, String houseSelect, String chargeSelect, String expire_date) {
		JSONObject result = new JSONObject();
		houseSelect = Coder.NullToBlank(houseSelect);
		chargeSelect = Coder.NullToBlank(chargeSelect);
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		BigDecimal fen = new BigDecimal("100");
		int number = 0;
		long time = Calendar.getInstance().getTimeInMillis() / 1000;
		boolean ownerStatus = true;
		String houseLocation = "";
		try {
			if (!"".equals(houseSelect) && !"".equals(chargeSelect)) {
				String[] charge = chargeSelect.split(",");
				EstateHouseDetail estateHouseDetail = estateHouseDetailService.findDataById(houseSelect);
				houseLocation = estateHouseDetail.getHouse_location();
				for (int i = 0; i < charge.length; i++) {
					ChargeStandard chargeStandard = chargeStandardService.findById(charge[i]);
					EstateCodeDetail estateCodeDetail = estateCodeEetailService.findDataById(chargeStandard.getDenominated_unit());
					OwnerHouseDetail ownerHouseDetail = ownerService.findLiveUserByHouseId(estateHouseDetail.getHouse_id());
					// 判断房屋是否有业主
					if ( null != ownerHouseDetail) {
						// 账单处理
						OwnerBill ownerBill = new OwnerBill();
						ownerBill.setHouse_id(estateHouseDetail.getHouse_id());
						ownerBill.setOwner_id(ownerHouseDetail.getOwner_id());
						ownerBill.setStandard_id(chargeStandard.getStandard_id());
						ownerBill.setStandard_name(chargeStandard.getProject_name());
						ownerBill.setHouse_number_id("");
						ownerBill.setStatus(0);
						if ("月".equals(chargeStandard.getCharge_unit())) {
							int num = Integer.parseInt(chargeStandard.getCharge_num());
							for (int m = 0; m < num; m++) {
								number += 1;
								ownerBill.setBill_id(buildNumber(number, time));
								Calendar temp = Calendar.getInstance();
								temp.set(Calendar.MONTH, temp.get(Calendar.MONTH) + m);
								ownerBill.setBatch(sdf.format(temp.getTime()));
								ownerBill.setExpire_date(dateFormat.parse(expire_date));
								// 删除已有的账单
								Map<String, Object> deleteMap = new HashMap<>();
								deleteMap.put("house_id", ownerBill.getHouse_id());
								deleteMap.put("owner_id", ownerBill.getOwner_id());
								deleteMap.put("batch", ownerBill.getBatch());
								deleteMap.put("standard_id", ownerBill.getStandard_id());
								ownerBillService.deleteByBatch(deleteMap);
								if ("单价*系数".equals(estateCodeDetail.getCode_content())) {
									double price = chargeStandard.getStandard_price().divide(fen).doubleValue(); //单价
									//单价*系数
									double count = price * Double.parseDouble(chargeStandard.getCoefficient()); 
									//+每层加价
									double total = count + chargeStandard.getPremium() * Double.parseDouble(estateHouseDetail.getHouse_floor()); 
									ownerBill.setMoney(decimalFormat.format(total));
								}
								if ("建筑面积*单价*系数".equals(estateCodeDetail.getCode_content())) {
									double price = chargeStandard.getStandard_price().divide(fen).doubleValue(); //单价
									//单价*系数*建筑面积
									double count = price * Double.parseDouble(chargeStandard.getCoefficient()) * Double.parseDouble(estateHouseDetail.getHouse_build_area());
									 //+每层加价
									double total = count + chargeStandard.getPremium() * Double.parseDouble(estateHouseDetail.getHouse_floor());
									ownerBill.setMoney(decimalFormat.format(total));
								}
								if ("套内面积*单价*系数".equals(estateCodeDetail.getCode_content())) {
									double price = chargeStandard.getStandard_price().divide(fen).doubleValue(); //单价
									//单价*系数*套内面积
									double count = price * Double.parseDouble(chargeStandard.getCoefficient()) * Double.parseDouble(estateHouseDetail.getHouse_in_area());
									 //+每层加价
									double total = count + chargeStandard.getPremium() * Double.parseDouble(estateHouseDetail.getHouse_floor());
									ownerBill.setMoney(decimalFormat.format(total));
								}
								if ("(建筑面积-套内面积)*单价*系数".equals(estateCodeDetail.getCode_content())) {
									double price = chargeStandard.getStandard_price().divide(fen).doubleValue(); //单价
									//(建筑面积-套内面积)*单价*系数
									double count = (Double.parseDouble(estateHouseDetail.getHouse_build_area()) - Double.parseDouble(estateHouseDetail.getHouse_in_area())) * price * Double.parseDouble(chargeStandard.getCoefficient());
									 //+每层加价
									double total = count + chargeStandard.getPremium() * Double.parseDouble(estateHouseDetail.getHouse_floor());
									ownerBill.setMoney(decimalFormat.format(total));
								}
								ownerBillService.insert(ownerBill);
							}
						}
					} else {
						ownerStatus = false;
						break;
					}
				}
			}
			if (ownerStatus) {
				result.put("status", true);
				result.put("message", "[OK]");
			} else {
				result.put("status", false);
				if ("".equals(houseLocation)) {
					result.put("message", "房屋没有业主数据, 账单生成失败!");
				} else {
					result.put("message", "房屋【"+houseLocation+"】没有业主数据, 账单生成失败!");
				}
			}
		} catch (Exception e) {
			result.put("status", false);
			result.put("message", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/deleteBill")
	public @ResponseBody JSONObject deleteBill(String bill_id) {
		JSONObject result = new JSONObject();
		try {
			ownerBillService.delete(bill_id);
			result.put("status", true);
		} catch (Exception e) {
			result.put("status", false);
		}
		return result;
	}
	
	@RequestMapping("/searchHouse")
	public @ResponseBody JSONArray searchHouse(String building_id) {
		JSONArray array = new JSONArray();
		List<EstateHouseDetail> details = estateHouseDetailService.findEstateHouseDetailByBuildingId(building_id);
		for (EstateHouseDetail estateHouseDetail : details) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("house_id", estateHouseDetail.getHouse_id());
			jsonObject.put("house_location", estateHouseDetail.getHouse_location());
			array.add(jsonObject);
		}
		return array;
	}
}
