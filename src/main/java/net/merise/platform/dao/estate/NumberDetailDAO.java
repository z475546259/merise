package net.merise.platform.dao.estate;

import java.util.Map;

import net.merise.platform.dao.estate.pojo.NumberDetail;

import org.springframework.stereotype.Repository;

@Repository
public interface NumberDetailDAO {

	void insert(NumberDetail numberDetail);
	
	void update(NumberDetail houseNumber);

	NumberDetail getNumberDetail(Map<String, Object> params);
}
