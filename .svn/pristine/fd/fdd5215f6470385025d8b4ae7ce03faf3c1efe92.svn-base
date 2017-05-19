package net.merise.platform.service.estate.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.ReportTotalDAO;
import net.merise.platform.dao.estate.pojo.NewsPaperRepairs;
import net.merise.platform.dao.estate.pojo.NewspaperReport;
import net.merise.platform.service.estate.ReportTotalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReportTotalServiceImpl implements ReportTotalService {

	@Autowired
	private ReportTotalDAO reportTotalDao;
	
	@Override
	public List<NewspaperReport> findCount(Map<String, Object> map) {
		return reportTotalDao.findCount(map);
	}

	@Override
	public List<NewspaperReport> findDataByTime(Map<String, Object> map) {
		return reportTotalDao.findDataByTime(map);
	}

	@Override
	public List<NewsPaperRepairs> findHuiFangByReportId(String report_id) {
		return reportTotalDao.findHuiFangByReportId(report_id);
	}

	@Override
	public List<NewspaperReport> findDataByBeginTime(Map<String, Object> map) {
		return reportTotalDao.findDataByBeginTime(map);
	}

	@Override
	public List<Map<String, Object>> findEmpRepairByTime(Map<String, Object> map) {
		return reportTotalDao.findEmpRepairByTime(map);
	}

	@Override
	public List<String> findEmpRepairByTimeCount(Map<String, Object> map) {
		return reportTotalDao.findEmpRepairByTimeCount(map);
	}

}
