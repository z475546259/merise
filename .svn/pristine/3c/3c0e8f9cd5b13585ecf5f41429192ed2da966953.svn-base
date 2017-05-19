package net.merise.platform.orm.mapper;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.PublicFacilities;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicFacilitiesDAO {
	
	public abstract void save(PublicFacilities publicFacilities);
	
	public abstract void edit(PublicFacilities publicFacilities);
	
	public abstract void delete(String id);
	
	public abstract List<PublicFacilities> findByBuildingAndDong(Map<String, Object> map);

	public abstract List<PublicFacilities> findAllByBbuildingId(@Param("building_id")String building_id);
}
