package net.merise.platform.dao.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.EstateConfig;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstateConfigDAO {
	
	public abstract void insert(EstateConfig estateConfig);
	
	public abstract void update(EstateConfig estateConfig);
	
	public abstract void delete(String config_id);
	
	public abstract List<EstateConfig> findByOwnerIdAndTable(@Param("owner_id")String owner_id,@Param("owner_table")String owner_table);

	public abstract List<EstateConfig> findDataByPage(Map<String, Object> dataMap);

	public abstract int findCount(@Param("owner_id")String owner_id,@Param("owner_table")String owner_table);

	public abstract EstateConfig findDataById(String config_id);
}
