package net.merise.platform.service.impl;

import java.util.List;

import net.merise.platform.orm.base.Repairs;
import net.merise.platform.orm.mapper.IRepairsDAO;
import net.merise.platform.service.RepairsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class RepairsServiceImpl implements RepairsService {
	
	@Autowired
	private IRepairsDAO dao;
	
	@Override
	public List<Repairs> incidentRecord(String str) {
		
		return dao.incidentRecord(str);
	}

}
