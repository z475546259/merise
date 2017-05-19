package net.merise.platform.service.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.NewsPaperRepairs;
import net.merise.platform.dao.estate.pojo.NewspaperReport;

public interface ReportTotalService {

	List<NewspaperReport> findCount(Map<String, Object> map);
	
	List<NewspaperReport> findDataByBeginTime(Map<String, Object> map);

	List<NewspaperReport> findDataByTime(Map<String, Object> map);
	
	List<NewsPaperRepairs> findHuiFangByReportId(String report_id);
	
	List<Map<String, Object>> findEmpRepairByTime(Map<String, Object> map);
	
	List<String> findEmpRepairByTimeCount(Map<String, Object> map);
}
