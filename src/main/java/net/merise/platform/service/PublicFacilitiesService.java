package net.merise.platform.service;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.PublicFacilities;

public interface PublicFacilitiesService {
public abstract void save(PublicFacilities publicFacilities);
	
	public abstract void edit(PublicFacilities publicFacilities);
	
	public abstract void delete(String id);
	
	public abstract List<PublicFacilities> findByBuildingAndDong(Map<String, Object> map);

	public abstract List<PublicFacilities> findAllByBbuildingId(
			String building_id);
}
