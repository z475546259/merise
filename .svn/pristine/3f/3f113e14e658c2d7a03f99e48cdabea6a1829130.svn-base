package net.merise.platform.service.estate.impl;

import java.util.List;

import net.merise.platform.dao.estate.IRepairsDAO;
import net.merise.platform.dao.estate.pojo.Repairs;
import net.merise.platform.service.estate.RepairsService;

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
