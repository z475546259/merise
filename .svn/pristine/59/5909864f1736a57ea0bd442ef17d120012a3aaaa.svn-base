package net.merise.platform.service.estate.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.BillDAO;
import net.merise.platform.dao.estate.pojo.Bill;
import net.merise.platform.service.estate.BillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BillServiceImpl implements BillService {
	
	@Autowired
	private BillDAO dao;

	@Override
	public List<Bill> findDataByPage(Map<String, Object> map) {
		return dao.findDataByPage(map);
	}

	@Override
	public int findPageCount(Map<String, Object> map) {
		return dao.findPageCount(map);
	}

	@Override
	public void save(Bill bill) {
		dao.save(bill);
	}

}
