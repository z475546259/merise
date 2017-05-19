package net.merise.platform.service.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.EstateConfig;
import net.merise.platform.orm.mapper.EstateConfigDAO;
import net.merise.platform.service.EstateConfigService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EstateConfigServiceImpl implements EstateConfigService {
	
	@Autowired
	private EstateConfigDAO dao;

	@Override
	public void insert(EstateConfig estateConfig) {
		dao.insert(estateConfig);
	}

	@Override
	public void update(EstateConfig estateConfig) {
		dao.update(estateConfig);
	}

	@Override
	public void delete(String config_id) {
		dao.delete(config_id);
	}

	@Override
	public List<EstateConfig> findByOwnerIdAndTable(String owner_id,String owner_table) {
		return dao.findByOwnerIdAndTable(owner_id, owner_table);
	}

	@Override
	public List<EstateConfig> findDataByPage(Map<String, Object> dataMap) {
		return dao.findDataByPage(dataMap);
	}

	@Override
	public int findCount(String owner_id, String owner_table) {
		return dao.findCount(owner_id,owner_table);
	}

	@Override
	public EstateConfig findDataById(String config_id) {
		return dao.findDataById(config_id);
	}

}
