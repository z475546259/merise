package net.merise.platform.service;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.TSmsCode;

public interface ISmsCodeService {

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
