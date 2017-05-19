package net.merise.platform.service.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.Bill;
import net.merise.platform.orm.mapper.BillDAO;
import net.merise.platform.service.BillService;

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
