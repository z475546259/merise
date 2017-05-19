package net.merise.platform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.merise.platform.orm.base.OwnerBill;
import net.merise.platform.orm.mapper.OwnerBillDAO;
import net.merise.platform.service.OwnerBillService;

@Service
@Transactional
public class OwnerBillServiceImpl implements OwnerBillService {
	
	@Autowired
	private OwnerBillDAO dao;

	@Override
	public void insert(OwnerBill ownerBill) {
		dao.insert(ownerBill);
	}

	@Override
	public void updateStatus(String bill_id) {
		dao.updateStatus(bill_id);
	}

	@Override
	public void delete(String bill_id) {
		dao.delete(bill_id);
	}

	@Override
	public void deleteByHouseAndOwnerId(String house_id, String owner_id) {
		dao.deleteByHouseAndOwnerId(house_id, owner_id);
	}

	@Override
	public void deleteByStandardId(String standard_id) {
		dao.deleteByStandardId(standard_id);
	}

	@Override
	public OwnerBill findDataById(String bill_id) {
		return dao.findDataById(bill_id);
	}

	@Override
	public List<OwnerBill> findAllData() {
		return dao.findAllData();
	}

	@Override
	public List<OwnerBill> findDataByHouseAndOwnerId(String house_id, String owner_id) {
		return dao.findDataByHouseAndOwnerId(house_id, owner_id);
	}

	@Override
	public void deleteByBatch(Map<String, Object> map) {
		dao.deleteByBatch(map);
	}
	
}
