package net.merise.platform.service;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.NewsPaperRepairs;
import net.merise.platform.orm.base.NewspaperReport;

public interface ReportTotalService {

	List<NewspaperReport> findCount(Map<String, Object> map);
	
	List<NewspaperReport> findDataByBeginTime(Map<String, Object> map);

	List<NewspaperReport> findDataByTime(Map<String, Object> map);
	
	List<NewsPaperRepairs> findHuiFangByReportId(String report_id);
	
	List<Map<String, Object>> findEmpRepairByTime(Map<String, Object> map);
	
	List<String> findEmpRepairByTimeCount(Map<String, Object> map);
}
