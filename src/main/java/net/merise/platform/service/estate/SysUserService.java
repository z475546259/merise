package net.merise.platform.service.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.SysUser;

public interface SysUserService {

	public abstract void insert(SysUser sysUser);
	
	public abstract void update(SysUser sysUser);
	
	public abstract void delete(String id);
	
	public abstract SysUser findDataById(String id);
	
	public abstract SysUser findDataByMobile(String mobile);
	
	public abstract List<SysUser> findAllData();
	
	public abstract int findCount(Map<String, Object> map);
	
	public abstract List<SysUser> findDataByPage(Map<String, Object> map);
}
