package net.merise.platform.service.estate.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.EstateBuildingDetailDAO;
import net.merise.platform.dao.estate.EstateHouseDetailDAO;
import net.merise.platform.dao.estate.EstateInfoDetailDAO;
import net.merise.platform.dao.estate.HouseNumberDAO;
import net.merise.platform.dao.estate.NumberDetailDAO;
import net.merise.platform.dao.estate.pojo.EstateHouseDetail;
import net.merise.platform.dao.estate.pojo.HouseNumber;
import net.merise.platform.dao.estate.pojo.NumberDetail;
import net.merise.platform.service.estate.HouseNumberService;
import net.merise.platform.utils.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HouseNumberServiceImpl implements HouseNumberService {

	@Autowired
	private EstateInfoDetailDAO eaEstateInfoDetailDAO;

	@Autowired
	private EstateBuildingDetailDAO estateBuildingDetailDAO;

	@Autowired
	private EstateHouseDetailDAO estateHouseDetailDAO;
	
	@Autowired
	private HouseNumberDAO houseNumberDao;
	
	@Autowired
	private NumberDetailDAO numberDetailDAO;

	@Override
	public void importHouse(List<Map<String, Object>> list) {

		for (Map<String, Object> params : list) {
			//保存房屋信息
			//根据楼盘号，栋号，房屋单元，楼屋，房号查询房屋是否存在
			EstateHouseDetail estateHouseDetail = estateHouseDetailDAO.getEstateHouseBYParams(params);
			if (estateHouseDetail == null) {
				estateHouseDetail = new EstateHouseDetail();
				estateHouseDetail.setHouse_id(Coder.getSerialCode20());
				estateHouseDetail.setBuilding_id(params.get("building_id").toString());
				estateHouseDetail.setHouse_dong(params.get("house_dong").toString());
				estateHouseDetail.setHouse_units(params.get("house_units").toString());
				estateHouseDetail.setHouse_floor(params.get("house_floor").toString());
				estateHouseDetail.setHouse_room(params.get("house_room").toString());
				estateHouseDetailDAO.insert(estateHouseDetail);
			}
			
			// 房屋户号
			params.put("house_id", estateHouseDetail.getHouse_id());
			HouseNumber houseNumber = houseNumberDao.getHoseNumber(params);
			if (houseNumber == null) {
				houseNumber = new HouseNumber();
				houseNumber.setHouse_number_id(Coder.getSerialCode20());
				houseNumber.setHouse_id(estateHouseDetail.getHouse_id());
				houseNumber.setElectricity_number(params.get("electricity_number").toString());
				houseNumber.setGas_number(params.get("gas_number").toString());
				houseNumber.setWater_number(params.get("water_number").toString());
				houseNumber.setNper(params.get("nper").toString());
				houseNumberDao.insert(houseNumber);
				
				//户屋流水表
				NumberDetail numberDetail = new NumberDetail();
				numberDetail.setDetail_id(Coder.getSerialCode20());
				numberDetail.setHouse_number_id(houseNumber.getHouse_number_id());
				numberDetail.setWater_start(params.get("water_start").toString());
				numberDetail.setGas_start(params.get("gas_start").toString());
				numberDetail.setElectricity_start(params.get("electricity_start").toString());
				
				numberDetailDAO.insert(numberDetail);
			}
		}
	}

	@Override
	public void importCharge(List<Map<String, Object>> list) {
		for (Map<String, Object> params : list) {
			//根据电户号,水户号,气户号,以及期数获取用户
			NumberDetail houseNumber = numberDetailDAO.getNumberDetail(params);
			if(houseNumber != null){
				houseNumber.setElectricity_end(params.get("electricity_end").toString());
				houseNumber.setGas_end(params.get("gas_end").toString());
				houseNumber.setWater_end(params.get("water_end").toString());
				numberDetailDAO.update(houseNumber);
			}
		}
	}

	@Override
	public Map<String, Object> findHouseNumberByPage(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", houseNumberDao.findAllByCount(params));
		map.put("rows", houseNumberDao.findHouseNumberByPage(params));
		return map;
	}

}
