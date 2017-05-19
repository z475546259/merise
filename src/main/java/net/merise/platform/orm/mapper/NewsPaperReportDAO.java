package net.merise.platform.orm.mapper;

import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.NewsPaperRepairs;
import net.merise.platform.orm.base.NewspaperReport;

import org.springframework.stereotype.Repository;

@Repository
public interface NewsPaperReportDAO {
	void insert(NewspaperReport newsPaperReport);
	void update(NewspaperReport newsPaperReport);
	void delete(String id);
	
	List<Map<String, Object>> findDataByPage(Map<String, Object> map);
	
	int findDataByPageCount(Map<String, Object> map);
	
	List<Map<String, Object>> findRepairsByReportId(Map<String, Object> map);
	
	int findRepairsByReportIdCount(Map<String, Object> map);
	
	void saveRepaies(NewsPaperRepairs newsPaperRepairs);
	
	NewspaperReport findDataById(String newspaper_id);
	
	List<Map<String, Object>> findRepairsByReportIdNoLimit(String newspaper_id);
	
	Map<String, Object> findOwnerDataByReportId(String newspaper_id);
	
	List<Map<String, Object>> findReportByEmpId(String employees_id);
 }
