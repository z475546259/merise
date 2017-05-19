package net.merise.platform.service.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.TMessageTask;

public interface IMessageTaskService {

	public abstract void insert(TMessageTask messageTask);
	
	public abstract void update(TMessageTask messageTask);
	
	public abstract void delete(String id);
	
	public abstract TMessageTask findDataById(String id);
	
	public abstract List<TMessageTask> findAllData();
	
	public abstract int findCount();
	
	public abstract List<TMessageTask> findDataByPage();
	
	public abstract List<TMessageTask> findDataByParams(Map<String, Object> map);
	
	public abstract List<TMessageTask> findDataByHour(String message_hour);
}
