package net.merise.platform.orm.mapper;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.TMessageTask;

import org.springframework.stereotype.Repository;

@Repository
public interface IMessageTaskDAO {

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
