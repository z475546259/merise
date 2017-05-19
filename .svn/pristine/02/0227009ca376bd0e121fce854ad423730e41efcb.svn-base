package net.merise.platform.dao.estate;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import net.merise.platform.dao.estate.pojo.TUserExtend;

@Repository
public interface IUserExtendDAO {

	void insert(TUserExtend userExtend);
	
	void update(TUserExtend userExtend);
	
	void delete(String extend_id);
	
	void deleteByUserId(String user_id);
	
	TUserExtend findDataById(String extend_id);
	
	TUserExtend findDataByUserId(String user_id);
	
	List<TUserExtend> findAllData(Map<String, Object> map);
	
	int findCount(Map<String, Object> map);
	
	List<TUserExtend> findDataByPage(Map<String, Object> map);
	
	TUserExtend findDataByOwnerId(String owner_id);
}
