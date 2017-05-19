package net.merise.platform.service.estate.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.NewsPaperReportDAO;
import net.merise.platform.dao.estate.pojo.NewsPaperRepairs;
import net.merise.platform.dao.estate.pojo.NewspaperReport;
import net.merise.platform.service.estate.NewsPaperReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NewsPaperReportServiceImpl implements NewsPaperReportService {

	@Autowired
	private NewsPaperReportDAO dao;
	
	@Override
	public void insert(NewspaperReport newsPaperReport) {
		dao.insert(newsPaperReport);
	}

	@Override
	public void update(NewspaperReport newsPaperReport) {
		dao.update(newsPaperReport);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public List<Map<String, Object>> findDataByPage(Map<String, Object> map) {
		return dao.findDataByPage(map);
	}

	@Override
	public List<Map<String, Object>> findRepairsByReportId(Map<String, Object> map) {
		return dao.findRepairsByReportId(map);
	}

	@Override
	public void saveRepaies(NewsPaperRepairs newsPaperRepairs) {
		dao.saveRepaies(newsPaperRepairs);
	}

	@Override
	public int findRepairsByReportIdCount(Map<String, Object> map) {
		return dao.findRepairsByReportIdCount(map);
	}

	@Override
	public int findDataByPageCount(Map<String, Object> map) {
		return dao.findDataByPageCount(map);
	}

	@Override
	public NewspaperReport findDataById(String newspaper_id) {
		return dao.findDataById(newspaper_id);
	}

	@Override
	public List<Map<String, Object>> findRepairsByReportIdNoLimit(
			String newspaper_id) {
		return dao.findRepairsByReportIdNoLimit(newspaper_id);
	}

	@Override
	public Map<String, Object> findOwnerDataByReportId(String newspaper_id) {
		return dao.findOwnerDataByReportId(newspaper_id);
	}

	@Override
	public List<Map<String, Object>> findReportByEmpId(String employees_id) {
		return dao.findReportByEmpId(employees_id);
	}

}
