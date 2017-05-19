package net.merise.platform.dao.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.EstateInfoDetail;

import org.springframework.stereotype.Repository;

@Repository
public interface EstateInfoDetailDAO {
	
	public abstract void insert(EstateInfoDetail estateInfoDetail);
	
	public abstract void update(EstateInfoDetail estateInfoDetail);
	
	public abstract void delete(String id);
	
	public abstract EstateInfoDetail findDataById(String id);
	
	public abstract List<EstateInfoDetail> findAllData(Map<String, Object> map);
	
	public abstract int findCount(Map<String, Object> map);
	
	public abstract List<EstateInfoDetail> findDataByPage(Map<String, Object> map);

	public abstract List<EstateInfoDetail> findEastateInfoByName(String name);
}
