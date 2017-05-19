package net.merise.platform.service.estate.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.IReportDAO;
import net.merise.platform.dao.estate.pojo.Report;
import net.merise.platform.service.estate.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private IReportDAO dao;
	@Override
	public List<Report> findDataByReport(String ownerId, String newspaperType) {
		
		return dao.findDataByReport(ownerId,newspaperType);
	}
	@Override
	public List<Report> findDataByReportStatue(String ownerId, String newspaper_status) {
		return dao.findDataByReportStatue(ownerId,newspaper_status);
	}
	@Override
	public List<Report> findDataByOwner(Map<String, Object> map) {
		return dao.findDataByOwner(map);
	}
	
}
