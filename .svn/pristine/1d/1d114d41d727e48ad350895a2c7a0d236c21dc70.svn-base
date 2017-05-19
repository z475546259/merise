package net.merise.platform.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.merise.platform.orm.base.Emp;
import net.merise.platform.orm.base.Employees;
import net.merise.platform.orm.base.EmployeesEvent;
import net.merise.platform.service.EmpService;
import net.merise.platform.service.EmployeesEventService;
import net.merise.platform.service.EmployeesService;
import net.merise.platform.utils.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * hr管理 应聘人员管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/emp")
@Transactional
public class EmpController {

	@Autowired
	private EmpService empService;
	
	@Autowired
	private EmployeesService employeesService;
	
	@Autowired
	private EmployeesEventService employeesEventService;
	
	/**
	 * 录用
	 * @param id
	 */
	@RequestMapping("/luyong")
	@ResponseBody
	public void lvyong(String id) {
		Emp emp = empService.findDataById(id);
		Employees newEmployees=new Employees();
		String employees_id = Coder.getSerialCode20();
		newEmployees.setEmployees_id(employees_id);
		newEmployees.setEmployees_name(emp.getEmp_name());
		newEmployees.setEmployees_sex(emp.getEmp_sex());
		newEmployees.setEmployees_dept(emp.getEmp_dept());
		newEmployees.setEmployees_job(emp.getEmp_job());
		newEmployees.setEmployees_phone(emp.getEmp_phone());
		newEmployees.setEmployees_education(emp.getEmp_education());
		newEmployees.setGraduate_school(emp.getGraduate_school());
		newEmployees.setBirthday(emp.getBirthday());
		newEmployees.setQq(emp.getQq());
		newEmployees.setId_number(emp.getId_number());
		newEmployees.setLive_addr(emp.getLive_addr());
		newEmployees.setEmployees_wechat(emp.getEmp_wechat());
		newEmployees.setEmployees_email(emp.getEmp_email());
//		newEmployees.setEmployees_remark(emp.getEmp_remark());
		newEmployees.setBuilding_id(emp.getBuilding_id());
		newEmployees.setIs_positive("1");
		newEmployees.setOffice_status("1");
		employeesService.insert(newEmployees);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		EmployeesEvent event=new EmployeesEvent();
		event.setEvent_id(Coder.getSerialCode20());
		event.setEmployees_id(employees_id);
		event.setEvent_name("面试记录");
		
		Date event_time = new Date();
		event.setEvent_time(event_time);
		String event_context="面试日期："+sdf.format(emp.getEmp_time())+"，面试记录："+emp.getEmp_remark();
		event.setEvent_context(event_context);
		employeesEventService.insert(event);
	}
	
	/**
	 * 淘汰/恢复
	 * @param id
	 */
	@RequestMapping("/change")
	@ResponseBody
	public void change(String id,String status) {
		String newStatus="未淘汰".equals(status)?"已淘汰":"未淘汰";
		empService.updateStatus(id,newStatus);
	}
	
	
	@RequestMapping("/findEmpByBuildingId")
	public @ResponseBody Map<String, Object> findEmpByBuildingId(int page, int rows,String searchValue,String building_id) {
		searchValue=searchValue==null?"未淘汰":searchValue;
		// 处理分页查询开始记录
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("building_id", building_id);
		dataMap.put("status", searchValue);
		// 返回数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", empService.findCount(dataMap));
		resultMap.put("rows", empService.findDataByPage(dataMap));
		return resultMap;
	}

	@RequestMapping("/save")
	@ResponseBody
	public void save(Emp emp,HttpServletRequest request) {
		emp.setEmp_id(Coder.getSerialCode20());
		emp.setStatus("未淘汰");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String emp_time = request.getParameter("emp_time_value");
		String birthday = request.getParameter("birthday_value");
		
		try {
			if(!("".equals(emp_time))){
				emp.setEmp_time(sdf.parse(emp_time));
			}
			if(!("".equals(birthday))){
				emp.setBirthday(sdf.parse(birthday));
			}
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		empService.insert(emp);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(Emp emp, String id, HttpServletRequest request) {
		emp.setEmp_id(id);
		emp.setStatus("未淘汰");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String emp_time = request.getParameter("emp_time_value");
		String birthday = request.getParameter("birthday_value");
		
		try {
			if(!("".equals(emp_time))){
				emp.setEmp_time(sdf.parse(emp_time));
			}
			if(!("".equals(birthday))){
				emp.setBirthday(sdf.parse(birthday));
			}
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		empService.update(emp);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String id) {
		empService.delete(id);
	}
	
}
