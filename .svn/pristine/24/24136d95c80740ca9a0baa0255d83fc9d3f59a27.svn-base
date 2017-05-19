package net.merise.platform.service.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.EstateConfig;

public interface EstateConfigService {

	public abstract void insert(EstateConfig estateConfig);
	
	public abstract void update(EstateConfig estateConfig);
	
	public abstract void delete(String config_id);
	
	public abstract List<EstateConfig> findByOwnerIdAndTable(String owner_id,String owner_table);

	public abstract List<EstateConfig> findDataByPage(Map<String, Object> dataMap);

	public abstract int findCount(String owner_id, String owner_table);

	public abstract EstateConfig findDataById(String config_id);
}
