package net.merise.platform.service.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.PublicFacilities;

public interface PublicFacilitiesService {
public abstract void save(PublicFacilities publicFacilities);
	
	public abstract void edit(PublicFacilities publicFacilities);
	
	public abstract void delete(String id);
	
	public abstract List<PublicFacilities> findByBuildingAndDong(Map<String, Object> map);

	public abstract List<PublicFacilities> findAllByBbuildingId(
			String building_id);
}
