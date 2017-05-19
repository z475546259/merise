package net.merise.platform.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.merise.platform.service.estate.HouseNumberService;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 房屋户号管理  导入
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/houseNumber")
@Transactional
public class HouseNumberController {
	
	protected static final Logger log = LoggerFactory.getLogger(HouseNumberController.class);
	
	public static final String path = "";
	
	@Autowired
	private HouseNumberService houseNumberService;
	
	/**
	 * 
	 * @param page
	 * @param rows
	 * @param building_id 楼盘id
	 * @param house_id 房屋id
	 * @param dong 栋号
	 * @return
	 */
	@RequestMapping("/findHouseNumberByPage")
	@ResponseBody
	public Object findHouseNumberByPage(int page, int rows,String building_id,String dong){
		// 处理分页查询开始记录
		int m = (page - 1) * rows;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("m", m);
		params.put("n", rows);
		params.put("building_id", building_id);
		params.put("house_dong", dong);
		
		// 返回数据
		return houseNumberService.findHouseNumberByPage(params);
	}
	
	/**
	 * 房屋户号关系导入
	 * @param upfile
	 * @param request
	 * @return
	 */
	@RequestMapping("/importHouse")
	@ResponseBody
	public String importHouse(String building_id,String dong,@RequestParam("importIndex") MultipartFile file,
			HttpServletRequest request){
		InputStream is = null;
		try {
			is = file.getInputStream();
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);//获取第一张表格
			int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			for (int i = 1; i < rowCount; i++) {
				Row row = sheet.getRow(i);//获取每一行
				
				//表信息
				Map<String,Object> map = new HashMap<String, Object>();
				
				
				map.put("building_id",building_id);
				map.put("house_dong", dong);
				
				//单元
				map.put("house_units", row.getCell(0).getStringCellValue());
				//楼层
				map.put("house_floor",row.getCell(1).getStringCellValue());
				//房号
				map.put("house_room", row.getCell(2).getStringCellValue());
				//电户号
				map.put("electricity_number", row.getCell(3).getStringCellValue());
				//电起始度数
				map.put("electricity_start", row.getCell(4).getStringCellValue());
				//气户号
				map.put("gas_number", row.getCell(5).getStringCellValue());
				//气起始度数
				map.put("gas_start", row.getCell(6).getStringCellValue());
				//水户号
				map.put("water_number", row.getCell(7).getStringCellValue());
				//水起始度数
				map.put("water_start", row.getCell(8).getStringCellValue());
				//期数
				map.put("nper", row.getCell(9).getStringCellValue());
				list.add(map);
			}
			houseNumberService.importHouse(list);
			return "数据导入成功";
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (is != null) {
				try {
					is.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return "数据导入失败!";
	}
	
	/**
	 * 水电气等费用导入
	 * @param upfile
	 * @param request
	 * @return 
	 */
	@RequestMapping("/importCharge")
	@ResponseBody
	public String importCharge(String building_id,String dong,@RequestParam("importIndex") MultipartFile file){
		InputStream is = null;
		try {
			is = file.getInputStream();
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			for (int i = 1; i < rowCount; i++) {
				Row row = sheet.getRow(i);//获取每一行
				//表信息
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("building_id",building_id);
				map.put("house_dong", dong);
				
				map.put("electricity_number", row.getCell(0).getStringCellValue());
				map.put("electricity_end",row.getCell(1).getStringCellValue());
				map.put("gas_number",row.getCell(2).getStringCellValue());
				map.put("gas_end", row.getCell(3).getStringCellValue());
				map.put("water_number", row.getCell(4).getStringCellValue());
				map.put("water_end", row.getCell(5).getStringCellValue());
				map.put("nper", row.getCell(6).getStringCellValue());
				list.add(map);
			}
			houseNumberService.importCharge(list);
			return "数据导入成功";
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (is != null) {
				try {
					is.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return "数据导入失败!";
	}

}
