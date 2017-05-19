package net.merise.platform.dao.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.TrainInfo;

import org.springframework.stereotype.Repository;

@Repository
public interface TrainInfoDAO {

	void insert(TrainInfo trainInfo);
	
	void update(TrainInfo trainInfo);
	
	void delete(String id);
	
	int findCount(Map<String, Object> dataMap);
	
	List<TrainInfo> findDataByPage(Map<String, Object> dataMap);
	
	TrainInfo findDataById(String id);
	
	List<Map<String, Object>> findByEstate_idAndBuilding(Map<String, Object> map);
	
	int findByEstate_idAndBuildingCount(Map<String, Object> map);
}
