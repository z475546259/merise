package net.merise.platform.service;

import java.util.List;
import java.util.Map;

public interface HouseNumberService {

	void importHouse(List<Map<String, Object>> list);

	void importCharge(List<Map<String, Object>> list);

	Map<String,Object> findHouseNumberByPage(Map<String, Object> params);

}
