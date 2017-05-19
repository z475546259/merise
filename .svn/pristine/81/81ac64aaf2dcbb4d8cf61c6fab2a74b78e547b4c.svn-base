package net.merise.platform.orm.mapper;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.HouseNumber;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseNumberDAO {

	void insert(HouseNumber houseNumber);

	int findAllByCount(Map<String, Object> params);

	List<Map<String, Object>> findHouseNumberByPage(Map<String, Object> params);

	HouseNumber getHoseNumber(Map<String, Object> params);

	Map<String, Object> findByHouseId(@Param("house_id")String house_id);

	Map<String, Object> getHouseAndOwen(Map<String, Object> params);

}
