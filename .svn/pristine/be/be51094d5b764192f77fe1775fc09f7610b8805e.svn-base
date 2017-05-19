package net.merise.platform.dao.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.TSmsCode;

import org.springframework.stereotype.Repository;

@Repository
public interface ISmsCodeDAO {

	public abstract void insert(TSmsCode smsCode);
	
	public abstract void update(TSmsCode smsCode);
	
	public abstract void delete(String id);
	
	public abstract void deleteByMobile(String mobile);
	
	public abstract TSmsCode findDataById(String id);
	
	public abstract TSmsCode findDataByMobile(String mobile);
	
	public abstract List<TSmsCode> findAllData(Map<String, Object> map);
	
	public abstract int findCount(Map<String, Object> map);
	
	public abstract List<TSmsCode> findDataByPage(Map<String, Object> map);
}
