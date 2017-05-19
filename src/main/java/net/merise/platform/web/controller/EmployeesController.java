package net.merise.platform.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.merise.platform.orm.base.Employees;
import net.merise.platform.orm.base.EmployeesEvent;
import net.merise.platform.orm.base.EstateBuildingDetail;
import net.merise.platform.orm.base.EstateCodeDetail;
import net.merise.platform.orm.base.EstateInfoDetail;
import net.merise.platform.orm.base.SysUser;
import net.merise.platform.orm.base.TUserEmployees;
import net.merise.platform.service.EmployeesEventService;
import net.merise.platform.service.EmployeesService;
import net.merise.platform.service.EstateBuildingDetailService;
import net.merise.platform.service.EstateCodeEetailService;
import net.merise.platform.service.EstateHouseDetailService;
import net.merise.platform.service.EstateInfoDetailService;
import net.merise.platform.service.SysUserService;
import net.merise.platform.service.UserEmployeeService;
import net.merise.platform.utils.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * hr管理  员工管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/employees")
@Transactional
public class EmployeesController {
	
	@Autowired
	private EmployeesService employeesService;
	
	@Autowired
	private EstateBuildingDetailService estateBuildingDetailService;
	
	@Autowired
	private EstateHouseDetailService estateHouseDetailService;
	
	@Autowired
	private EstateInfoDetailService estateInfoDetailService;
	
	@Autowired
	private EstateCodeEetailService estateCodeEetailService;
	
	@Autowired
	private EmployeesEventService employeesEventService;
	
	@Autowired
	private UserEmployeeService userEmployeeService;
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 *  考核
	 * @param request
	 */
	@RequestMapping("/saveCheck")
	@ResponseBody
	public void saveCheck(HttpServletRequest request){
		String employees_id = request.getParameter("old_employees_id");
		String check_time = request.getParameter("check_time");
		String checkContext = request.getParameter("checkContext");
		String checkResult = request.getParameter("checkResult");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		EmployeesEvent event=new EmployeesEvent();
		event.setEvent_id(Coder.getSerialCode20());
		event.setEmployees_id(employees_id);
		event.setEvent_name("考核");
		
		Date event_time = new Date();
		event.setEvent_time(event_time);
		String event_context="考核日期："+check_time+"，考核内容："+checkContext+"，考核结果："+checkResult+"，考核时间："+sdf.format(event_time);
		event.setEvent_context(event_context);
		employeesEventService.insert(event);
	}
	
	/**
	 * 合同更新
	 * @param request
	 */
	@RequestMapping("/contractChange")
	@ResponseBody
	public void contractChange(HttpServletRequest request){
		try {
			String employees_id = request.getParameter("old_employees_id");
			Employees oldEmployees=employeesService.findDataById(employees_id);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String signed_time = request.getParameter("signed_time");
			String contract_start_value = request.getParameter("contract_start_value");
			String contract_end_value = request.getParameter("contract_end_value");
			String reason = request.getParameter("reason");
			String contract_status = request.getParameter("contract_status");
			if(!("".equals(contract_start_value))){
				oldEmployees.setContract_start(sdf.parse(contract_start_value));
			}
			if(!("".equals(contract_end_value))){
				oldEmployees.setContract_end(sdf.parse(contract_end_value));
			}
			employeesService.update(oldEmployees);
			
			EmployeesEvent event=new EmployeesEvent();
			event.setEvent_id(Coder.getSerialCode20());
			event.setEmployees_id(employees_id);
			event.setEvent_name("合同更新");
			
			Date event_time = new Date();
			event.setEvent_time(event_time);
			String event_context="签订日期："+signed_time+"，合同状态："+contract_status+"，合同起始日期："+contract_start_value+"，合同终止日期："+contract_end_value+"，原因："+reason+"，更新时间："+sdf.format(event_time);
			event.setEvent_context(event_context);
			employeesEventService.insert(event);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 薪资变动
	 * @param request
	 */
	@RequestMapping("/salaryChange")
	@ResponseBody
	public void salaryChange(HttpServletRequest request){
		String employees_id = request.getParameter("old_employees_id_value");
		String old_salary_value = request.getParameter("old_salary_value");
		double new_salary=Double.parseDouble(request.getParameter("new_salary"));
		String reason = request.getParameter("reason");
		
		Employees oldEmployees=employeesService.findDataById(employees_id);
		oldEmployees.setSalary(new_salary);
		employeesService.update(oldEmployees);
		
		EmployeesEvent event=new EmployeesEvent();
		event.setEvent_id(Coder.getSerialCode20());
		event.setEmployees_id(employees_id);
		event.setEvent_name("薪资变动");
		
		Date event_time = new Date();
		event.setEvent_time(event_time);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String event_context="原薪资："+old_salary_value+"，变更薪资："+new_salary+"，原因："+reason+"，变动时间："+sdf.format(event_time);
		event.setEvent_context(event_context);
		employeesEventService.insert(event);
		
	}
	
	@RequestMapping("/findEventByEmployeesId")
	public @ResponseBody Map<String, Object> findEventByEmployeesId(int page, int rows,String searchValue,String employees_id){
		searchValue=searchValue==null?"":searchValue;
		// 处理分页查询开始记录
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("employees_id", employees_id);
		dataMap.put("event_name", searchValue);
		
		List<EmployeesEvent> list=employeesEventService.findEventPageByEmployeesId(dataMap);
		
		// 返回数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", employeesEventService.findCountByEmployeesId(dataMap));
		resultMap.put("rows", list);
		return resultMap;
	}
	
	
	/**
	 * 岗位变动
	 * @param request
	 */
	@RequestMapping("/jobchange")
	@ResponseBody
	public void jobchange(HttpServletRequest request){
		String new_dept_id = request.getParameter("new_dept");
		EstateCodeDetail newdept = estateCodeEetailService.findDataById(new_dept_id);
		String new_job = request.getParameter("new_job");
		String employees_id = request.getParameter("employees_id_value");
		Employees oldEmployees=employeesService.findDataById(employees_id);
		oldEmployees.setEmployees_dept(new_dept_id);
		oldEmployees.setEmployees_job(new_job);
		employeesService.update(oldEmployees);
		
		
		String old_dept = request.getParameter("employees_dept_value");
		String old_job = request.getParameter("employees_job_value");
		String reason = request.getParameter("reason");
		
		EmployeesEvent event=new EmployeesEvent();
		event.setEvent_id(Coder.getSerialCode20());
		event.setEmployees_id(employees_id);
		event.setEvent_name("岗位变动");
		
		Date event_time = new Date();
		event.setEvent_time(event_time);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String event_context="原部门："+old_dept+"，原岗位："+old_job+"，新部门："+newdept.getCode_content()+"，新岗位："+new_job+"，原因："+reason+"，变动时间："+sdf.format(event_time);
		event.setEvent_context(event_context);
		event.setRemark(request.getParameter("remark"));
		
		employeesEventService.insert(event);
		
	}
	
	
	@RequestMapping("/findEmployeesByBuildingId")
	public @ResponseBody Map<String, Object> findEmployeesByBuildingId(int page, int rows,String searchValue,String building_id) {
		searchValue=searchValue==null?"":searchValue;
		// 处理分页查询开始记录
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("building_id", building_id);
		dataMap.put("employees_id", searchValue);
		dataMap.put("employees_name", searchValue);
		dataMap.put("employees_job", searchValue);
		// 返回数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", employeesService.findCount(dataMap));
		resultMap.put("rows", employeesService.findDataByPage(dataMap));
		return resultMap;
	}

	@RequestMapping("/save")
	@ResponseBody
	public void save(Employees employees,HttpServletRequest request) {
		employees.setEmployees_id(Coder.getSerialCode20());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String induction_time = request.getParameter("induction_time_value");
		String birthday = request.getParameter("birthday_value");
		String departure_time = request.getParameter("departure_time_value");
		String contract_start = request.getParameter("contract_start_value");
		String contract_end = request.getParameter("contract_end_value");
		
		try {
			if(!("".equals(induction_time))){
				employees.setInduction_time(sdf.parse(induction_time));
			}
			if(!("".equals(birthday))){
				employees.setBirthday(sdf.parse(birthday));
			}
			if(!("".equals(departure_time))){
				employees.setDeparture_time(sdf.parse(departure_time));
			}
			if(!("".equals(contract_start))){
				employees.setContract_start(sdf.parse(contract_start));
			}
			if(!("".equals(contract_end))){
				employees.setContract_end(sdf.parse(contract_end));
			}
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		employeesService.insert(employees);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(Employees employees, String id, HttpServletRequest request) {
		employees.setEmployees_id(id);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String induction_time = request.getParameter("induction_time_value");
		String birthday = request.getParameter("birthday_value");
		String departure_time = request.getParameter("departure_time_value");
		String contract_start = request.getParameter("contract_start_value");
		String contract_end = request.getParameter("contract_end_value");
		
		try {
			if(!("".equals(induction_time))){
				employees.setInduction_time(sdf.parse(induction_time));
			}
			if(!("".equals(birthday))){
				employees.setBirthday(sdf.parse(birthday));
			}
			if(!("".equals(departure_time))){
				employees.setDeparture_time(sdf.parse(departure_time));
			}
			if(!("".equals(contract_start))){
				employees.setContract_start(sdf.parse(contract_start));
			}
			if(!("".equals(contract_end))){
				employees.setContract_end(sdf.parse(contract_end));
			}
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		employeesService.update(employees);
	}

	@RequestMapping("/delete")
	public void delete(String id) {
		employeesService.delete(id);
	}
	
	/**
	 * 物业楼盘树
	 * @return JSON格式数据
	 */
	@RequestMapping("/tree")
	public @ResponseBody JSONArray tree() {
		JSONArray result = new JSONArray();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("estate_name", "");
		List<EstateInfoDetail> estateList = estateInfoDetailService.findAllData(dataMap);
		for (EstateInfoDetail estateInfoDetail : estateList) {
			JSONObject object = new JSONObject();
			object.put("id", estateInfoDetail.getEstate_id());
			object.put("text", estateInfoDetail.getEstate_name());
			JSONObject attr = new JSONObject();
			attr.put("level", "estate");
			object.put("attributes", attr);
			// 查询物业的楼盘
			List<EstateBuildingDetail> buildList=estateBuildingDetailService.findEstateBuildingByEstateId(estateInfoDetail.getEstate_id());
			JSONArray buildArray = new JSONArray();
			for (EstateBuildingDetail estateBuildingDetail : buildList) {
				JSONObject buildObject = new JSONObject();
				buildObject.put("id", estateBuildingDetail.getBuilding_id());
				buildObject.put("text", estateBuildingDetail.getBuilding_name());
				JSONObject buildAttr = new JSONObject();
				buildAttr.put("level", "build");
				buildObject.put("attributes", buildAttr);
				buildArray.add(buildObject);
			}
			object.put("children", buildArray);
			result.add(object);
		}
		return result;
	}
	
	@RequestMapping("/getEventRemark")
	public @ResponseBody List<Map<String, Object>> getEventRemark(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> mapData = new HashMap<String,Object>();
		mapData.put("remark", "升职");
		mapData.put("remarkValue", "升职");
		
		Map<String, Object> mapData1 = new HashMap<String,Object>();
		mapData1.put("remark", "降职");
		mapData1.put("remarkValue", "降职");
		
		Map<String, Object> mapData2 = new HashMap<String,Object>();
		mapData2.put("remark", "岗位调转");
		mapData2.put("remarkValue", "岗位调转");
		
		list.add(mapData);
		list.add(mapData1);
		list.add(mapData2);
		return list;
	}
	
	@RequestMapping("/findByDeptId")
	public @ResponseBody List<Employees> findByDeptId(String id){
		return employeesService.findByDeptId(id);
	}
	
	@RequestMapping("/findUser")
	public @ResponseBody JSONArray findUserList() {
		JSONArray array = new JSONArray();
		List<SysUser> list = sysUserService.findAllData();
		for (SysUser sysUser : list) {
			JSONObject object = new JSONObject();
			object.put("id", sysUser.getId());
			object.put("mobile", sysUser.getName());
			array.add(object);
		}
		return array;
	}
	
	@RequestMapping("/bindingUser")
	public @ResponseBody JSONObject bindingUser(String employeeId, String userId) {
		JSONObject object = new JSONObject();
		employeeId = Coder.NullToBlank(employeeId);
		userId = Coder.NullToBlank(userId);
		try {
			if (!"".equals(employeeId) && !"".equals(userId)) {
				userEmployeeService.deleteByEmployeesId(employeeId);
				userEmployeeService.deleteByUserId(userId);
				SysUser sysUser = sysUserService.findDataById(userId);
				if (null == sysUser) {
					object.put("status", false);
					object.put("message", "用户获取失败!");
				} else {
					TUserEmployees tUserEmployees = new TUserEmployees();
					tUserEmployees.setEmployees_id(employeeId);
					tUserEmployees.setUser_id(userId);
					userEmployeeService.insert(tUserEmployees);
					object.put("status", true);
					object.put("message", "[OK]");
				}
			} else {
				object.put("status", false);
				object.put("message", "参数不能为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			object.put("status", false);
			object.put("message", e.getMessage());
		}
		return object;
	}
}
