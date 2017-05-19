package net.merise.platform.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.merise.platform.orm.base.NewsPaperRepairs;
import net.merise.platform.orm.base.NewspaperReport;
import net.merise.platform.service.ReportTotalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/reportTotal")
@Transactional
public class ReportTotalController {
	
	@Autowired
	private ReportTotalService reportTotalService;

	@RequestMapping("/findCount")
	public @ResponseBody  List<Object> findCount(HttpServletRequest request,String building_id){
		List<Object> resultMap = new ArrayList<Object>();
		Map<String, Object> map = new HashMap<String,Object>();
		String begin_time=request.getParameter("begin_time");
		String after_time=request.getParameter("after_time");
		map.put("begin_time",begin_time);
		map.put("after_time",after_time);
		map.put("building_id", building_id);
		List<NewspaperReport> list=reportTotalService.findCount(map);
		//报事数量
		int bsone=0;
		int bstwo=0;
		int bsother=0;
		
		//报修数量
		int bxone=0;
		int bxtwo=0;
		int bxother=0;
		
		//其它数量
		int qtone=0;
		int qttwo=0;
		int qtother=0;
		for (int i = 0; i < list.size(); i++) {
			NewspaperReport newspaperReport = list.get(i);
			int wctimes = Integer.parseInt(newspaperReport.getWctimes());
			String newspaper_type = newspaperReport.getNewspaper_type();//事件类别
			if("报事".equals(newspaper_type)){
				if(wctimes<=24){
					//一天之内处理完时间   统计总数量
					bsone++;
				}else if(wctimes>24&&wctimes<=48){
					//两天之内处理完时间   统计总数量
					bstwo++;
				}else{
					bsother++;
				}
			}else if("报修".equals(newspaper_type)){
				if(wctimes<=24){
					//一天之内处理完时间   统计总数量
					bxone++;
				}else if(wctimes>24&&wctimes<=48){
					//两天之内处理完时间   统计总数量
					bxtwo++;
				}else{
					bxother++;
				}
			}else{
				if(wctimes<=24){
					//一天之内处理完时间   统计总数量
					qtone++;
				}else if(wctimes>24&&wctimes<=48){
					//两天之内处理完时间   统计总数量
					qttwo++;
				}else{
					qtother++;
				}
			}
			
		}
		
		List<Integer> bsvalueList=new ArrayList<Integer>();
		bsvalueList.add(bsone);
		bsvalueList.add(bstwo);
		bsvalueList.add(bsother);
		List<Integer> bxvalueList=new ArrayList<Integer>();
		bxvalueList.add(bxone);
		bxvalueList.add(bxtwo);
		bxvalueList.add(bxother);
		List<Integer> qtvalueList=new ArrayList<Integer>();
		qtvalueList.add(qtone);
		qtvalueList.add(qttwo);
		qtvalueList.add(qtother);
		
		
		List<String> nameList=new ArrayList<String>();
		nameList.add("一天内");
		nameList.add("两天内");
		nameList.add("其它天内");
		
		resultMap.add(nameList);
		resultMap.add(bsvalueList);
		resultMap.add(bxvalueList);
		resultMap.add(qtvalueList);
		return resultMap;
	}
	
	@RequestMapping("/findReportCount")
	public @ResponseBody List<Map<String, Object>> findReportCount(String begin_time,String end_time,String building_id){
		List<Map<String, Object>> mapData = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("begin_time", begin_time);
		map.put("end_time", end_time);
		map.put("building_id", building_id);
		
		//查询当前时间段内开始的事件
		List<NewspaperReport> listAllReport = reportTotalService.findDataByBeginTime(map);
		
		//计算事件总量
		int baoshiCount = 0;
		int baoxiuCount = 0;
		int qitaCount = 0;
		for (NewspaperReport newspaperReport : listAllReport) {
			if(newspaperReport.getNewspaper_type().equals("报事")){
				baoshiCount = baoshiCount+1;
			}else if(newspaperReport.getNewspaper_type().equals("报修")){
				baoxiuCount = baoxiuCount+1;
			}else if(newspaperReport.getNewspaper_type().equals("其他")){
				qitaCount = qitaCount+1;
			}
		}
		
		//查询当前时间段开始并完成的事件
		List<NewspaperReport> listReport = reportTotalService.findDataByTime(map);
		
		//计算完成时间总量
		int baoshiFCount = 0;
		int baoxiuFCount = 0;
		int qitaFCount = 0;
		//计算回访满意数量
		int baoshiMCount = 0;
		int baoxiuMCount = 0;
		int qitaMCount = 0;
		//计算回访数量
		int baoshiHCount = 0;
		int baoxiuHCount = 0;
		int qitaHCount = 0;
		//计算多次派工率
		int baoshiDCount = 0;
		int baoxiuDCount = 0;
		int qitaDCount = 0;
		for (NewspaperReport newspaperReport : listReport) {
			if(newspaperReport.getNewspaper_type().equals("报事")){
				baoshiFCount =baoshiFCount+1;
				List<NewsPaperRepairs> list = reportTotalService.findHuiFangByReportId(newspaperReport.getNewspaper_id());
				for (NewsPaperRepairs newsPaperRepairs : list) {
					if(newsPaperRepairs.getRepairs_type().equals("回访")){
						baoshiHCount = baoshiHCount+1;
						if(!newsPaperRepairs.getRepairs_remark().contains("不满意")){
							if(newsPaperRepairs.getRepairs_remark().contains("满意")){
								baoshiMCount = baoshiMCount +1;
							}
						}
					}
					if(newsPaperRepairs.getRepairs_type().equals("外委派工") || newsPaperRepairs.getRepairs_type().equals("协调派工")){
						baoshiDCount = baoshiDCount+1;
					}
				}
			}else if(newspaperReport.getNewspaper_type().equals("报修")){
				baoxiuFCount = baoxiuFCount+1;
				List<NewsPaperRepairs> list = reportTotalService.findHuiFangByReportId(newspaperReport.getNewspaper_id());
				for (NewsPaperRepairs newsPaperRepairs : list) {
					if(newsPaperRepairs.getRepairs_type().equals("回访")){
						baoxiuHCount = baoxiuHCount+1;
						if(!newsPaperRepairs.getRepairs_remark().contains("不满意")){
							if(newsPaperRepairs.getRepairs_remark().contains("满意")){
								baoxiuMCount = baoxiuMCount +1;
							}
						}
					}
					if(newsPaperRepairs.getRepairs_type().equals("外委派工") || newsPaperRepairs.getRepairs_type().equals("协调派工")){
						baoxiuDCount = baoxiuDCount+1;
					}
				}
			}else if(newspaperReport.getNewspaper_type().equals("其他")){
				qitaFCount = qitaFCount+1;
				List<NewsPaperRepairs> list = reportTotalService.findHuiFangByReportId(newspaperReport.getNewspaper_id());
				for (NewsPaperRepairs newsPaperRepairs : list) {
					if(newsPaperRepairs.getRepairs_type().equals("回访")){
						qitaHCount = qitaHCount+1;
						if(!newsPaperRepairs.getRepairs_remark().contains("不满意")){
							if(newsPaperRepairs.getRepairs_remark().contains("满意")){
								qitaMCount = qitaMCount +1;
							}
						}
					}
					if(newsPaperRepairs.getRepairs_type().equals("外委派工") || newsPaperRepairs.getRepairs_type().equals("协调派工")){
						qitaDCount = qitaDCount+1;
					}
				}
			}
		}
		
		Map<String, Object> map1 = new HashMap<String,Object>();
		map1.put("name", "报事");
		map1.put("zongliang", baoshiCount);
		map1.put("wanchengliang", baoshiFCount);
		if(baoshiMCount != 0 && baoshiFCount != 0){
			
			map1.put("manyidu", getbfs(baoshiMCount, baoshiFCount));
		}else{
			map1.put("manyidu", "0%");
		}
		if(baoshiDCount != 0 && baoshiCount != 0){
			map1.put("paigonglv", getbfs(baoshiDCount, baoshiCount));
		}else{
			map1.put("paigonglv", "0%");
		}
		if(baoshiHCount != 0 && baoshiCount != 0){
			map1.put("huifanglv", getbfs(baoshiHCount, baoshiCount));
		}else{
			map1.put("huifanglv", "0%");
		}
		mapData.add(map1);
		
		Map<String, Object> map2 = new HashMap<String,Object>();
		map2.put("name", "报修");
		map2.put("zongliang", baoxiuCount);
		map2.put("wanchengliang", baoxiuFCount);
		if(baoxiuMCount != 0 && baoxiuFCount != 0){
			map2.put("manyidu", getbfs(baoxiuMCount, baoxiuFCount));
		}else{
			map2.put("manyidu", "0%");
		}
		if(baoxiuDCount != 0 && baoxiuCount != 0){
			map2.put("paigonglv", getbfs(baoxiuDCount, baoxiuCount));
		}else{
			map2.put("paigonglv", "0%");
		}
		if(baoxiuHCount != 0 && baoxiuCount != 0){
			map2.put("huifanglv", getbfs(baoxiuHCount, baoxiuCount));
		}else{
			map2.put("huifanglv", "0%");
		}
		mapData.add(map2);
		
		Map<String, Object> map3 = new HashMap<String,Object>();
		map3.put("name", "其他");
		map3.put("zongliang", qitaCount);
		map3.put("wanchengliang", qitaFCount);
		if(qitaMCount != 0 && qitaFCount != 0){
			
			map3.put("manyidu", getbfs(qitaMCount, qitaFCount));
		}else{
			map3.put("manyidu", "0%");
		}
		if(qitaDCount != 0 && qitaCount != 0){
			map3.put("paigonglv", getbfs(qitaDCount, qitaCount));
		}else{
			map3.put("paigonglv", "0%");
		}
		if(qitaHCount != 0 && qitaCount != 0){
			map3.put("huifanglv", getbfs(qitaHCount, qitaCount));
		}else{
			map3.put("huifanglv", "0%");
		}
		mapData.add(map3);
		
		Map<String, Object> map4 = new HashMap<String,Object>();
		map4.put("name", "总计");
		map4.put("zongliang", baoshiCount + baoxiuCount + qitaCount);
		map4.put("wanchengliang", baoshiFCount + baoxiuFCount + qitaFCount);
		
		if((baoshiMCount + baoxiuMCount + qitaMCount) != 0 && (baoshiFCount + baoxiuFCount + qitaFCount) != 0){
			map4.put("manyidu", getbfs((baoshiMCount + baoxiuMCount + qitaMCount), (baoshiFCount + baoxiuFCount + qitaFCount)));
		}else{
			map4.put("manyidu", "0%");
		}
		if((baoshiDCount + baoxiuDCount + qitaDCount) != 0 && (baoshiCount + baoxiuCount + qitaCount) != 0){
			map4.put("paigonglv", getbfs((baoshiDCount + baoxiuDCount + qitaDCount), (baoshiCount + baoxiuCount + qitaCount)));
		}else{
			map4.put("paigonglv", "0%");
		}
		if((baoshiHCount + baoxiuHCount + qitaHCount) != 0 && (baoshiCount + baoxiuCount + qitaCount) != 0){
			map4.put("huifanglv", getbfs((baoshiHCount + baoxiuHCount + qitaHCount), (baoshiCount + baoxiuCount + qitaCount)));
		}else{
			map4.put("huifanglv", "0%");
		}
		mapData.add(map4);
		
		return mapData;
	}
	/**
	 * 获取百分比
	 * @param i
	 * @param j
	 * @return
	 */
	private static String getbfs(int i,int j) {
	    double k = (double)i/j*100;
	    java.math.BigDecimal   big   =   new   java.math.BigDecimal(k);  
	    String  l = big.setScale(2,java.math.BigDecimal.ROUND_HALF_UP).doubleValue() +"%";
		return l;
	}
	
	@RequestMapping("/findEmpRepairsCount")
	public @ResponseBody Map<String, Object> findEmp(int page, int rows,String begin_time,String end_time,String building_id){
		Map<String, Object> mapData = new HashMap<String,Object>();
		
		int m = (page - 1) * rows;
		Map<String, Object> mapPara = new HashMap<String,Object>();
		mapPara.put("begin_time", begin_time);
		mapPara.put("end_time", end_time);
		mapPara.put("building_id", building_id);
		mapPara.put("m", m);
		mapPara.put("n", rows);
		
		List<Map<String, Object>> map = reportTotalService.findEmpRepairByTime(mapPara);
		
		mapData.put("rows", map);
		mapData.put("total", reportTotalService.findEmpRepairByTimeCount(mapPara).size());
		
		return mapData;
	}
}
