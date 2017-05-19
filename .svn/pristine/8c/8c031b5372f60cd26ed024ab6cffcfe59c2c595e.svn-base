package net.merise.platform.service.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.PublicFacilities;
import net.merise.platform.orm.mapper.PublicFacilitiesDAO;
import net.merise.platform.service.PublicFacilitiesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PublicFacilitiesServiceImpl implements PublicFacilitiesService {

	@Autowired
	private PublicFacilitiesDAO dao;
	
	@Override
	public void save(PublicFacilities publicFacilities) {
		dao.save(publicFacilities);
	}

	@Override
	public void edit(PublicFacilities publicFacilities) {
		dao.edit(publicFacilities);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public List<PublicFacilities> findByBuildingAndDong(Map<String, Object> map) {
		return dao.findByBuildingAndDong(map);
	}

	@Override
	public List<PublicFacilities> findAllByBbuildingId(String building_id) {
		return dao.findAllByBbuildingId(building_id);
	}

}
