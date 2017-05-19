package net.merise.platform.service.impl;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.Report;
import net.merise.platform.orm.mapper.IReportDAO;
import net.merise.platform.service.ReportService;

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
