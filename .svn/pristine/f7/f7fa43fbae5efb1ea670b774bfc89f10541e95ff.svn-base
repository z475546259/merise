package net.merise.platform.dao.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.Report;

import org.springframework.stereotype.Repository;

@Repository
public interface IReportDAO {
	public abstract List<Report> findDataByReport(String ownerId,String newspaperType);
	public abstract List<Report> findDataByReportStatue(String ownerId,String newspaper_status);
	
	List<Report> findDataByOwner(Map<String, Object> map);
}
