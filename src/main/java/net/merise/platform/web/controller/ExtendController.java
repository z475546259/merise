package net.merise.platform.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import net.merise.platform.encrypt.MD5;
import net.merise.platform.orm.base.Announcement;
import net.merise.platform.orm.base.AppointService;
import net.merise.platform.orm.base.Employees;
import net.merise.platform.orm.base.EstateCodeDetail;
import net.merise.platform.orm.base.EstateHouseDetail;
import net.merise.platform.orm.base.EstateOwnerDetail;
import net.merise.platform.orm.base.Image;
import net.merise.platform.orm.base.NewsPaperRepairs;
import net.merise.platform.orm.base.NewspaperReport;
import net.merise.platform.orm.base.OwnerBill;
import net.merise.platform.orm.base.OwnerHouseDetail;
import net.merise.platform.orm.base.PayCost;
import net.merise.platform.orm.base.Report;
import net.merise.platform.orm.base.SysUser;
import net.merise.platform.orm.base.TSmsCode;
import net.merise.platform.orm.base.TUserEmployees;
import net.merise.platform.orm.base.TUserExtend;
import net.merise.platform.orm.base.TUserOpinion;
import net.merise.platform.service.AnnouncementService;
import net.merise.platform.service.AppointServiceService;
import net.merise.platform.service.EmployeesService;
import net.merise.platform.service.EstateCodeEetailService;
import net.merise.platform.service.EstateHouseDetailService;
import net.merise.platform.service.HouseChargeRelationService;
import net.merise.platform.service.ISmsCodeService;
import net.merise.platform.service.IUserExtendService;
import net.merise.platform.service.ImageService;
import net.merise.platform.service.NewsPaperReportService;
import net.merise.platform.service.OwnerBillService;
import net.merise.platform.service.OwnerService;
import net.merise.platform.service.PayCostService;
import net.merise.platform.service.RepairsService;
import net.merise.platform.service.ReportService;
import net.merise.platform.service.SysUserService;
import net.merise.platform.service.UserEmployeeService;
import net.merise.platform.service.UserOpinionService;
import net.merise.platform.utils.Coder;
import net.merise.platform.utils.Console;
import net.merise.platform.utils.MessageUtils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: ExtendController 
 * @Description: 客户端接口类
 * @author SunXiaoYong.Inc
 * @date 2017年2月13日 下午5:08:23
 */
@Controller
@RequestMapping("/api")
@Transactional
public class ExtendController {
	
	@Autowired
	private ISmsCodeService smsCodeService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private RepairsService repairsService;
	@Autowired
	private OwnerService ownerService;
	@Autowired
	private UserOpinionService userOpinionService;
	@Autowired
	private IUserExtendService userExtendService;
	@Autowired
	private EstateHouseDetailService estateHouseDetailService;
	@Autowired
	private AppointServiceService appointServiceService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private NewsPaperReportService newsPaperReportService;
	@Autowired
	private EstateCodeEetailService estateCodeEetailService;
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private HouseChargeRelationService houseChargeRelationService;
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private OwnerBillService ownerBillService;
	@Autowired
	private PayCostService payCostService;
	@Autowired
	private UserEmployeeService userEmployeeService;
	
	private String basePath = ""; //项目根路径,根据客户端请求的路径计算 例如：http://127.0.0.1:8020/merise/
	
	private String imageFilePath = ""; //图片存放本地路径

	/**
	 * 客户端开放接口处理
	 * @param request HttpServletRequest
	 * @return Json格式返回结果
	 */
	@RequestMapping("/gateway")
	public @ResponseBody JSONObject gateway(HttpServletRequest request) {
		BufferedReader br = null;
		JSONObject result = new JSONObject();
		basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		imageFilePath = request.getSession().getServletContext().getRealPath("/");
		try {
			request.setCharacterEncoding("UTF-8");
			br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(),"UTF-8"));
	        String line = null;
	        StringBuffer sb = new StringBuffer();
	        while ((line = br.readLine()) != null) {
	            sb.append(line);
	        }
	        String appMsg=sb.toString();
	        Console.i("请求的数据："+appMsg, ExtendController.class);
	        if (!"".equals(sb.toString())) {
	        	JSONObject jsonObject = JSONObject.parseObject(appMsg);
	        	Object object = null;
	        	if (jsonObject.containsKey("method")) {
	        		if (jsonObject.containsKey("params")) {
	        			String params = jsonObject.getString("params");
	        			switch (Function.findFunction(jsonObject.getString("method"))) {
	        			// 登录接口
			        	case login:
			        		object = this.login(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
							break;
						// 获取验证码
			        	case validateCode:
			        		object = this.validateCode(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
				        // 验证验证码是否正确
				        case validate:
				        	object = this.validate(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
				        // 注册
				        case register:
				        	object = this.register(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
				        // 重置密码
				        case resetPassword:
				        	object = this.resetPassword(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
			        	 // 修改昵称
				        case editNickName:
				        	object = this.editNickName(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
			        	 // 业主信息验证
				        case validateUserInfo:
				        	object = this.validateUserInfo(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
			        	 // 意见反馈
				        case opinion:
				        	object = this.opinion(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
				        // 社区服务
				        case serviceList:
				        	object = this.serviceList(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
				        // 报事报修列表
				        case reportList:
				        	object = this.reportList(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
				        // 报事报修详情
				        case reportDetail:
				        	object = this.reportDetail(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
				        // 事件上报
				        case reportSave:
				        	object = this.reportSave(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
				        // 报修详情经过列表
				        case reportDetailRepairs:
				        	object = this.reportDetailRepairs(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
				        // 事件列表
				        case eventList:
				        	object = this.eventList(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
				        // 部门人员查询
				        case deptEmployees:
				        	object = this.deptEmployees(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
				        // 事件提交
				        case eventSave:
				        	object = this.eventSave(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
				        // 费用列表
				        case chargeList:
				        	object = this.chargeList(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
				        // 公告列表
				        case noticeDetail:
				        	object = this.noticeDetail(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
				        // 缴费
				        case chargeSave:
				        	object = this.chargeSave(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
				        // 登录数据刷新
				        case refresh:
				        	object = this.refresh(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
				        // 事件处理详情列表
				        case eventDetail:
				        	object = this.eventDetail(params);
			        		result.put("status", true);
				        	result.put("statusMessage", "调用成功");
				        	result.put("data", object);
				        	result.put("exception", "");
				        	break;
	        			default:
							result.put("status", false);
							result.put("statusMessage", "调用的方法不存在");
				        	result.put("data", "");
				        	result.put("exception", "");
							break;
	        			}
	        		} else {
	        			result.put("status", false);
		    			result.put("statusMessage", "缺少[params]参数");
		    			result.put("data", "");
		    			result.put("exception", "");
	        		}
	        	} else {
	        		result.put("status", false);
	    			result.put("statusMessage", "缺少[method]参数");
	    			result.put("data", "");
	    			result.put("exception", "");
	        	}
	        } else {
	        	result.put("status", false);
    			result.put("statusMessage", "未获取到任何参数");
    			result.put("data", "");
    			result.put("exception", "");
	        }
		} catch (Exception e) {
			result.put("status", false);
			result.put("statusMessage", "参数错误,请认真检查");
			result.put("data", "");
			result.put("exception", e.getMessage());
			e.printStackTrace();
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	/**
	 * 登录接口
	 * @param data
	 * @return
	 */
	private JSONObject login(String data) {
		JSONObject result = new JSONObject();
		try {
			JSONObject datajson = JSONObject.parseObject(data);
			SysUser sysUser = sysUserService.findDataByMobile(datajson.getString("mobile"));
			String psd = MD5.encode(datajson.getString("password"));
			if (sysUser != null) {
				if (datajson.containsKey("password") && psd.equals(sysUser.getPassword())) {
					// 记录最后一次登录时间
					sysUser.setLastLoginTime(new Date());
					sysUserService.update(sysUser);
					// 记录clientid, 判断是否第一次登录
					TUserExtend extend = userExtendService.findDataByUserId(sysUser.getId()+"");
					if (extend != null) {
						extend.setClient_id(datajson.getString("clientid"));
						userExtendService.update(extend);
						// 查询房屋数据
						if (!"".equals(Coder.NullToBlank(extend.getOwner_id()))) {
							// 查询业主数据
							EstateOwnerDetail estateOwnerDetail = ownerService.findDataById(extend.getOwner_id());
							// 处理返回数据
							result.put("userScore", 0);
							if (null == estateOwnerDetail) {
								result.put("userBalance", 0);
							} else {
								result.put("userBalance", Coder.NullToZero(estateOwnerDetail.getMoney().toString()));
							}
							List<Map<String, Object>> list = estateHouseDetailService.findHouseByOwnerId(extend.getOwner_id());
							JSONArray array = new JSONArray();
							if (null != list && list.size() > 0) {
								for (Map<String, Object> map : list) {
									JSONObject o = new JSONObject();
									o.put("house_id", map.get("house_id"));
									o.put("house_text", map.get("building_name")+" "+map.get("house_dong")+" "+map.get("house_units")+" "+map.get("house_floor")+"-"+map.get("house_room"));
									o.put("building_id", map.get("building_id"));
									array.add(o);
								}
							} else {
								JSONObject o = new JSONObject();
								o.put("house_id", "");
								o.put("house_text", "");
								o.put("building_id", "");
								array.add(o);
							}
							result.put("userHourse", array);
						} else {
							JSONArray array = new JSONArray();
							JSONObject o = new JSONObject();
							o.put("house_id", "");
							o.put("house_text", "");
							o.put("building_id", "");
							array.add(o);
							result.put("userHourse", array);
							result.put("userScore", 0);
							result.put("userBalance", 0);
						}
					} else {
						TUserExtend userExtend = new TUserExtend();
						userExtend.setExtend_id(UUID.randomUUID().toString());
						userExtend.setUser_id(sysUser.getId()+"");
						userExtend.setOwner_id("");
						userExtend.setClient_id(datajson.getString("clientid"));
						userExtendService.insert(userExtend);
						// 处理首次登陆返回数据
						JSONArray array = new JSONArray();
						JSONObject o = new JSONObject();
						o.put("house_id", "");
						o.put("house_text", "");
						o.put("building_id", "");
						array.add(o);
						result.put("userHourse", array);
						result.put("userScore", 0);
						result.put("userBalance", 0);
					}
					// 查询员工编号
					TUserEmployees tUserEmployees = userEmployeeService.findDataByUserId(sysUser.getId()+"");
					if (null == tUserEmployees) {
						result.put("employee_id", "");
					} else {
						result.put("employee_id", tUserEmployees.getEmployees_id());
					}
					result.put("loginStatus", true);
					result.put("message", "登录成功!");
					result.put("userId", sysUser.getId());
					result.put("userNickName", Coder.NullToBlank(sysUser.getNickName()));
				} else {
					result.put("loginStatus", false);
					result.put("message", "密码错误!");
				}
			} else {
				result.put("loginStatus", false);
				result.put("message", "账号不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("loginStatus", false);
			result.put("message", e.getMessage());
		}
		return result;
	}
	
	private JSONObject refresh(String data) {
		JSONObject result = new JSONObject();
		try {
			JSONObject datajson = JSONObject.parseObject(data);
			SysUser sysUser = sysUserService.findDataById(datajson.getString("userId"));
			TUserExtend extend = userExtendService.findDataByUserId(sysUser.getId()+"");
			if (!"".equals(Coder.NullToBlank(extend.getOwner_id()))) {
				// 查询业主数据
				EstateOwnerDetail estateOwnerDetail = ownerService.findDataById(extend.getOwner_id());
				// 处理返回数据
				result.put("userScore", 0);
				if (null == estateOwnerDetail) {
					result.put("userBalance", 0);
				} else {
					result.put("userBalance", Coder.NullToZero(estateOwnerDetail.getMoney().toString()));
				}
				List<Map<String, Object>> list = estateHouseDetailService.findHouseByOwnerId(extend.getOwner_id());
				JSONArray array = new JSONArray();
				if (null != list && list.size() > 0) {
					for (Map<String, Object> map : list) {
						JSONObject o = new JSONObject();
						o.put("house_id", map.get("house_id"));
						o.put("house_text", map.get("building_name")+" "+map.get("house_dong")+" "+map.get("house_units")+" "+map.get("house_floor")+"-"+map.get("house_room"));
						o.put("building_id", map.get("building_id"));
						array.add(o);
					}
				} else {
					JSONObject o = new JSONObject();
					o.put("house_id", "");
					o.put("house_text", "");
					o.put("building_id", "");
					array.add(o);
				}
				result.put("userHourse", array);
			} else {
				JSONArray array = new JSONArray();
				result.put("userHourse", array);
				result.put("userScore", 0);
				result.put("userBalance", 0);
			}
			// 查询员工编号
			TUserEmployees tUserEmployees = userEmployeeService.findDataByUserId(sysUser.getId()+"");
			if (null == tUserEmployees) {
				result.put("employee_id", "");
			} else {
				result.put("employee_id", tUserEmployees.getEmployees_id());
			}
			result.put("refreshStatus", true);
			result.put("message", "[OK]");
			result.put("userId", sysUser.getId());
			result.put("userNickName", Coder.NullToBlank(sysUser.getNickName()));
		} catch (Exception e) {
			result.put("refreshStatus", false);
			result.put("message", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 判断验证码是否正确接口
	 * @param data
	 * @return
	 */
	private JSONObject validate(String data) {
		JSONObject result = new JSONObject();
		try {
			JSONObject datajson = JSONObject.parseObject(data);
			TSmsCode sms_code = smsCodeService.findDataByMobile(datajson.getString("mobile"));
			if (sms_code == null) {
				result.put("validateStatus", false);
				result.put("message", "验证码无效!");
			} else {
				// 计算验证码的分钟数
				Calendar calendar = Calendar.getInstance();
				long temp = calendar.getTimeInMillis() - sms_code.getSms_code_time();
				int min = (int) (temp / 1000 / 60);
				// 判断验证码是否有效
				if (min <= 15) {
					if (datajson.containsKey("code") && sms_code.getSms_code_content().equals(datajson.getString("code"))) {
						result.put("validateStatus", true);
						result.put("message", "验证成功!");
						// 清空数据记录
						smsCodeService.deleteByMobile(sms_code.getSms_code_mobile());
					} else {
						result.put("validateStatus", false);
						result.put("message", "验证码错误!");
					}
				} else {
					result.put("validateStatus", false);
					result.put("message", "验证码已失效,请重新获取!");
					// 清空数据记录
					smsCodeService.deleteByMobile(sms_code.getSms_code_mobile());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("validateStatus", false);
			result.put("message", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取验证码接口
	 * @param data
	 * @return
	 */
	private JSONObject validateCode(String data) {
		JSONObject result = new JSONObject();
		String code = "";
		try {
			JSONObject datajson = JSONObject.parseObject(data);
			String number = Coder.randomNumber(6);
			code = number;
			String content = "【米睿物业】您的验证码为："+number+"，请在15分钟内按提示提交验证码，切勿将验证码泄露于其他人。";
			String type = datajson.getString("type"); // 请求类型：1-验证手机号是否存在，2-不验证手机号
			
			TSmsCode sms_code = new TSmsCode();
			Calendar calendar = Calendar.getInstance();
			sms_code.setSms_code_id(Coder.getSerialCode20());
			sms_code.setSms_code_content(number);
			sms_code.setSms_code_mobile(datajson.getString("mobile"));
			sms_code.setSms_code_time(calendar.getTimeInMillis());
			
			if (!"".equals(Coder.NullToBlank(type)) && "1".equals(type)) {
				SysUser user = sysUserService.findDataByMobile(datajson.getString("mobile"));
				if (user != null) {
					result.put("message", "手机号码已注册!");
				} else {
					boolean smsResult = MessageUtils.send(datajson.getString("mobile"), content);
					if (smsResult) {
						smsCodeService.deleteByMobile(datajson.getString("mobile"));
						result.put("message", "短信发送成功!");
						smsCodeService.insert(sms_code);
					} else {
						result.put("message", "短信发送失败,请稍候再试!");
					}
				}
			} else {
				boolean smsResult = MessageUtils.send(datajson.getString("mobile"), content);
				if (smsResult) {
					result.put("message", "短信发送成功!");
					smsCodeService.deleteByMobile(datajson.getString("mobile"));
					smsCodeService.insert(sms_code);
				} else {
					result.put("message", "短信发送失败,请稍候再试!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("message", e.getMessage());
		}
		result.put("code", code); //测试时返回,正式上线后注释掉
		return result;
	}
	
	/**
	 * 重置密码
	 * @param data
	 * @return
	 */
	private JSONObject resetPassword(String data) {
		JSONObject result = new JSONObject();
		try {
			JSONObject datajson = JSONObject.parseObject(data);
			SysUser user = sysUserService.findDataByMobile(datajson.getString("mobile"));
			if (user == null) {
				result.put("resetStatus", false);
				result.put("message", "手机号码未注册!");
			} else {
				user.setPassword(MD5.encode(datajson.getString("password")));
				sysUserService.update(user);
				result.put("resetStatus", true);
				result.put("message", "密码找回成功，请重新登录！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("resetStatus", false);
			result.put("message", e.getMessage());
		}
		return result;
	}
	
	
	/**
	 * 注册
	 * @param data
	 * @return
	 */
	private JSONObject register(String data) {
		JSONObject result = new JSONObject();
		try {
			JSONObject datajson = JSONObject.parseObject(data);
			SysUser user = sysUserService.findDataByMobile(datajson.getString("mobile"));
			if (user == null) {
				SysUser insert = new SysUser();
				insert.setMobile(datajson.getString("mobile"));
				insert.setName(datajson.getString("mobile"));
				insert.setPassword(MD5.encode(datajson.getString("password")));
				insert.setType((short) 1);
				sysUserService.insert(insert);
				// 记录clientid
				TUserExtend userExtend = new TUserExtend();
				userExtend.setExtend_id(UUID.randomUUID().toString());
				userExtend.setUser_id(insert.getId()+"");
				userExtend.setOwner_id("");
				userExtend.setClient_id(datajson.getString("clientid"));
				userExtendService.insert(userExtend);
				// 返回处理
				result.put("registerStatus", true);
				result.put("message", "注册成功!");
			} else {
				result.put("registerStatus", false);
				result.put("message", "手机号码已存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("registerStatus", false);
			result.put("message", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 昵称修改
	 * @param data
	 * @return
	 */
	private JSONObject editNickName(String data) {
		JSONObject result = new JSONObject();
		try {
			JSONObject datajson = JSONObject.parseObject(data);
			SysUser user = sysUserService.findDataById(datajson.getString("userId"));
			if (user != null) {
				user.setNickName(datajson.getString("userNickName"));
				sysUserService.update(user);
				result.put("editStatus", true);
				result.put("message", "修改成功!");
			} else {
				result.put("editStatus", false);
				result.put("message", "用户编号不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("editStatus", false);
			result.put("message", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 业主信息验证
	 * @param data
	 * @return
	 */
	private JSONObject validateUserInfo(String data) {
		JSONObject result = new JSONObject();
		try {
			JSONObject datajson = JSONObject.parseObject(data);
			String owner_name = Coder.NullToBlank(datajson.getString("owner_name"));
			String owner_mobile = Coder.NullToBlank(datajson.getString("owner_mobile"));
			String owner_idcard = Coder.NullToBlank(datajson.getString("owner_idcard"));
			if ("".equals(owner_name) || "".equals(owner_mobile) || "".equals(owner_idcard)) {
				result.put("validateStatus", false);
				result.put("message", "数据不能为空,请认真检查!");
			} else {
				EstateOwnerDetail ownerDetail = new EstateOwnerDetail();
				ownerDetail.setOwner_name(owner_name);
				ownerDetail.setOwner_mobile(owner_mobile);
				ownerDetail.setOwner_idcard(owner_idcard);
				List<EstateOwnerDetail> details = ownerService.findDataForValidate(ownerDetail);
				if (details != null && details.size() > 0) {
					SysUser user = sysUserService.findDataById(datajson.getString("userId"));
					if (user != null) {
						TUserExtend extend = userExtendService.findDataByUserId(datajson.getString("userId"));
						TUserExtend owner = userExtendService.findDataByOwnerId(details.get(0).getOwner_id());
						String ownerId = Coder.NullToBlank(extend.getOwner_id());
						if (extend != null && "".equals(ownerId) && null == owner) {
							extend.setOwner_id(details.get(0).getOwner_id());
							userExtendService.update(extend);
							result.put("validateStatus", true);
							result.put("message", "验证成功,已自动关联房屋信息!");
						} else {
							result.put("validateStatus", false);
							result.put("message", "绑定失败,该业主已绑定用户信息!");
						}
					} else {
						result.put("validateStatus", false);
						result.put("message", "登录用户编号获取失败!");
					}
				} else {
					result.put("validateStatus", false);
					result.put("message", "未查询到相关业主登记信息!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("validateStatus", false);
			result.put("message", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 社区服务列表
	 * @param data 楼盘编号
	 * @return 社区服务JSON格式数据
	 */
	private JSONObject serviceList(String data) {
		JSONObject result = new JSONObject();
		try {
			JSONObject datajson = JSONObject.parseObject(data);
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			List<AppointService> list = appointServiceService.findDataByBuildingId(datajson.getString("building_id"));
			JSONArray array = new JSONArray();
			for (AppointService appointService : list) {
				JSONObject object = new JSONObject();
				object.put("service_id", appointService.getService_id());
				object.put("service_name", appointService.getService_name());
				object.put("service_picurl", basePath+appointService.getService_picurl());
				object.put("charge_standard", appointService.getCharge_standard());
				object.put("service_phone", appointService.getService_phone());
				object.put("service_des", appointService.getService_des());
				object.put("service_time", format.format(appointService.getStart_time())+" - "+format.format(appointService.getEnd_time()));
				List<Image> images = imageService.findImageByOwnerId(appointService.getService_id(), "t_appoint_service");
				JSONArray imageArr = new JSONArray();
				for (Image im : images) {
					JSONObject imageObj = new JSONObject();
					imageObj.put("image_id", im.getImage_id());
					imageObj.put("image_url", basePath+im.getImage_url());
					imageArr.add(imageObj);
				}
				object.put("service_image", imageArr);
				array.add(object);
			}
			result.put("list", array);
			result.put("functionStatus", true);
			result.put("functionMessage", "[OK]");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("list", new JSONArray());
			result.put("functionStatus", false);
			result.put("functionMessage", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 意见反馈
	 * @param data
	 * @return
	 */
	private JSONObject opinion(String data) {
		JSONObject result = new JSONObject();
		try {
			TUserOpinion userOpinion = JSON.parseObject(data, TUserOpinion.class);
			userOpinion.setOpinion_id(Coder.getSerialCode20());
			userOpinion.setOpinion_date(new Date());
			userOpinion.setOpinion_reply("");
			userOpinionService.insert(userOpinion);
			result.put("opinionStatus", true);
			result.put("message", "意见反馈成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("opinionStatus", false);
			result.put("message", e.getMessage());
		}
		return  result;
	}
	
	/**
	 * 报事报修列表
	 * @param data 请求参数
	 * @return JSON格式数据
	 */
	private JSONObject reportList(String data) {
		JSONObject result = new JSONObject();
		try {
			JSONObject datajson = JSONObject.parseObject(data);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			TUserExtend extend = userExtendService.findDataByUserId(datajson.getString("user_id"));
			EstateHouseDetail estateHouseDetail = estateHouseDetailService.findDataById(datajson.getString("house_id"));
			if (null != extend) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("owner_id", extend.getOwner_id());
				map.put("building_id", estateHouseDetail.getBuilding_id());
				JSONArray array = new JSONArray();
				List<Report> list = reportService.findDataByOwner(map);
				for (Report report : list) {
					JSONObject object = new JSONObject();
					object.put("newspaper_id", report.getNewspaper_id());
					object.put("newspaper_type", report.getNewspaper_type());
					object.put("newspaper_name", report.getNewspaper_name());
					object.put("newspaper_time", sdf.format(report.getNewspaper_time()));
					object.put("newspaper_status", report.getNewspaper_status());
					array.add(object);
				}
				result.put("list", array);
				result.put("functionStatus", true);
				result.put("functionMessage", "[OK]");
			} else {
				result.put("list", new JSONArray());
				result.put("functionStatus", false);
				result.put("functionMessage", "未查询到用户的房屋数据, 请验证个人信息。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("list", new JSONArray());
			result.put("functionStatus", false);
			result.put("functionMessage", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 物业报修详情
	 * @param data
	 * @return
	 */
	private JSONObject reportDetail(String data) {
		JSONObject result = new JSONObject();
		try {
			JSONObject datajson = JSONObject.parseObject(data);
			NewspaperReport report = newsPaperReportService.findDataById(datajson.getString("newspaper_id"));
			if (null != report) {
				result.put("newspaper_status", report.getNewspaper_status());
				result.put("newspaper_name", report.getNewspaper_name());
				result.put("newspaper_content", report.getNewspaper_content());
				List<Image> images = imageService.findImageByOwnerId(datajson.getString("newspaper_id"), "t_newspaper_report");
				if (null != images && images.size() > 0) {
					JSONArray array = new JSONArray();
					for (Image image : images) {
						JSONObject object = new JSONObject();
						object.put("image_name", image.getImage_name());
						object.put("image_url", basePath+image.getImage_url());
						array.add(object);
					}
					result.put("image_list", array);
				} else {
					result.put("image_list", new JSONArray());
				}
				result.put("functionStatus", true);
				result.put("functionMessage", "[OK]");
			} else {
				result.put("functionStatus", false);
				result.put("functionMessage", "[无效的报修编号]");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("functionStatus", false);
			result.put("functionMessage", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 事件上报
	 * @param data
	 * @return
	 */
	@Transactional
	private JSONObject reportSave(String data) {
		JSONObject result = new JSONObject();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String current = sdf.format(new Date());
			JSONObject datajson = JSONObject.parseObject(data);
			NewspaperReport report = new NewspaperReport();
			TUserExtend userExtend = userExtendService.findDataByUserId(datajson.getString("owner_id"));
			if (userExtend != null && !"".equals(Coder.NullToBlank(userExtend.getOwner_id()))) {
				report.setNewspaper_id(Coder.getSerialCode20());
				report.setNewspaper_type(datajson.getString("newspaper_type"));
				report.setReport_type("app");
				report.setNewspaper_name(datajson.getString("newspaper_name"));
				report.setNewspaper_content(datajson.getString("newspaper_content"));
				report.setNewspaper_time(new Date());
				report.setNewspaper_status("未处理");
				report.setNewspaper_remark("");
				report.setOwner_id(userExtend.getOwner_id());
				report.setHouse_id(datajson.getString("house_id"));
				newsPaperReportService.insert(report);
				// 图片处理
				if (datajson.containsKey("image_list")) {
					JSONArray imageList = datajson.getJSONArray("image_list");
					for (int i = 0; i < imageList.size(); i++) {
						JSONObject imageObject = imageList.getJSONObject(i);
						// 目录处理
						String savePath = imageFilePath.replace("\\", "/")+"UploadFiles/"+current;
						File file = new File(savePath);
						if (!file.exists() && !file.isDirectory()) {
							file.mkdirs();
						}
						String fileName = UUID.randomUUID().toString()+".png";
						// 图片本地存储
						String imagePath = savePath + "/" +fileName;
						byte[] imageByte = Base64.decodeBase64(imageObject.getString("image_data"));
						FileUtils.writeByteArrayToFile(new File(imagePath), imageByte);
						// 图片数据存储
						Image image = new Image();
						image.setImage_id(UUID.randomUUID().toString());
						image.setImage_name(fileName);
						image.setImage_remark("报事报修手机客户端上传的图片");
						image.setImage_url("UploadFiles/"+current+"/"+fileName);
						image.setImage_date(new Date());
						image.setImage_owner_table("t_newspaper_report");
						image.setImage_owner_id(report.getNewspaper_id());
						image.setImage_order((i+1)+"");
						imageService.insert(image);
					}
				}
				result.put("functionStatus", true);
				result.put("functionMessage", "[OK]");
			} else {
				result.put("functionStatus", false);
				result.put("functionMessage", "操作失败,该用户未验证个人信息！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("functionStatus", false);
			result.put("functionMessage", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 报修详情经过查询
	 * @param data
	 * @return
	 */
	private JSONObject reportDetailRepairs(String data) {
		JSONObject result = new JSONObject();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			JSONObject datajson = JSONObject.parseObject(data);
			JSONArray array = new JSONArray();
			Map<String, Object> ownerData = newsPaperReportService.findOwnerDataByReportId(datajson.getString("newspaper_id"));
			if (null != ownerData) {
				result.put("newspaper_name", ownerData.get("newspaper_name"));
				result.put("newspaper_content", ownerData.get("newspaper_content"));
				result.put("owner_name", ownerData.get("owner_name"));
				result.put("owner_mobile", ownerData.get("owner_mobile"));
				result.put("owner_location", ownerData.get("building_name")+" "+ownerData.get("house_dong")+" "+ownerData.get("house_units")+" "+ownerData.get("house_floor")+"-"+ownerData.get("house_room"));
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("employees_name", ownerData.get("owner_name"));
				jsonObject.put("repairs_time", sdf.format(ownerData.get("newspaper_time")));
				jsonObject.put("repairs_type", "登记");
				array.add(jsonObject);
			}
			List<Map<String, Object>> list = newsPaperReportService.findRepairsByReportIdNoLimit(datajson.getString("newspaper_id"));
			if (null != list && list.size() > 0) {
				for (Map<String, Object> map : list) {
					JSONObject object = new JSONObject();
					object.put("employees_name", map.get("employees_name"));
					object.put("repairs_time", sdf.format(map.get("repairs_time")));
					object.put("repairs_type", map.get("repairs_type"));
					array.add(object);
				}
			}
			result.put("details", array);
			result.put("functionStatus", true);
			result.put("functionMessage", "[OK]");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("functionStatus", false);
			result.put("functionMessage", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 事件列表
	 * @param data
	 * @return
	 */
	private JSONObject eventList(String data) {
		JSONObject result = new JSONObject();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			JSONObject datajson = JSONObject.parseObject(data);
			JSONArray array = new JSONArray();
			List<Map<String, Object>> list = newsPaperReportService.findReportByEmpId(datajson.getString("employees_id"));
			System.out.println(JSON.toJSON(list).toString());
			if (null != list && list.size() > 0) {
				for (Map<String, Object> map : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("newspaper_id", map.get("newspaper_id"));
					jsonObject.put("newspaper_time", sdf.format(map.get("newspaper_time")));
					jsonObject.put("newspaper_name", map.get("newspaper_name"));
					jsonObject.put("house_id", map.get("house_id"));
					jsonObject.put("owner_id", map.get("owner_id"));
					jsonObject.put("building_id", map.get("building_id"));
					if (map.get("repairs_type").toString().indexOf("协调派工") != -1) {
						jsonObject.put("newspaper_status", "处理中");
					} else if (map.get("repairs_type").toString().indexOf("回访") != -1) {
						jsonObject.put("newspaper_status", "处理中");
					}  else if (map.get("repairs_type").toString().indexOf("外委派工") != -1) {
						jsonObject.put("newspaper_status", "处理中");
					} else if (map.get("repairs_type").toString().indexOf("回单") != -1) {
						jsonObject.put("newspaper_status", "处理中");
					}  else if (map.get("repairs_type").toString().indexOf("已归档") != -1 || null != map.get("end_time")) {
						jsonObject.put("newspaper_status", "处理完");
					} else {
						jsonObject.put("newspaper_status", "未处理");
					}
					// 处理重复数据
					for (int i = 0; i < array.size(); i++) {
						JSONObject temp = array.getJSONObject(i);
						if (map.get("newspaper_id").toString().equals(temp.getString("newspaper_id"))) {
							array.remove(0);
						}
					} 
					array.add(jsonObject);
				}
			}
			result.put("details", array);
			result.put("functionStatus", true);
			result.put("functionMessage", "[OK]");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("functionStatus", false);
			result.put("functionMessage", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 部门人员查询
	 * @param data
	 * @return
	 */
	private JSONObject deptEmployees(String data) {
		JSONObject result = new JSONObject();
		try {
			JSONObject datajson = JSONObject.parseObject(data);
			Map<String, Object> map = new HashMap<>();
			map.put("code_name", "部门");
			map.put("building_id", datajson.getString("building_id"));
			JSONArray deptArray = new JSONArray();
			List<EstateCodeDetail> codeDetails = estateCodeEetailService.findByCodeNameAndBuilding(map);
			for (EstateCodeDetail estateCodeDetail : codeDetails) {
				JSONObject deptObject = new JSONObject();
				deptObject.put("dept_id", estateCodeDetail.getCode_id());
				deptObject.put("dept_name", estateCodeDetail.getCode_content());
				JSONArray empArray = new JSONArray();
				List<Employees> employees = employeesService.findByDeptId(estateCodeDetail.getCode_id());
				for (Employees emp : employees) {
					JSONObject empObject = new JSONObject();
					empObject.put("employees_id", emp.getEmployees_id());
					empObject.put("employees_name", emp.getEmployees_name());
					empArray.add(empObject);
				}
				deptObject.put("employees", empArray);
				deptArray.add(deptObject);
			}
			result.put("items", deptArray);
			result.put("functionStatus", true);
			result.put("functionMessage", "[OK]");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("functionStatus", false);
			result.put("functionMessage", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 事件提交
	 * @param data
	 * @return
	 */
	@Transactional
	private JSONObject eventSave(String data) {
		JSONObject result = new JSONObject();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String current = sdf.format(new Date());
			JSONObject datajson = JSONObject.parseObject(data);
			NewsPaperRepairs newsPaperRepairs = new NewsPaperRepairs();
			newsPaperRepairs.setRepairs_id(Coder.getSerialCode20());
			newsPaperRepairs.setNewspaper_id(datajson.getString("newspaper_id"));
			newsPaperRepairs.setEmployees_id(datajson.getString("employees_id"));
			newsPaperRepairs.setRepairs_type(datajson.getString("repairs_type"));
			newsPaperRepairs.setRepairs_time(new Date());
			newsPaperRepairs.setRepairs_status("");
			newsPaperRepairs.setRepairs_remark(datajson.getString("repairs_remark"));
			newsPaperReportService.saveRepaies(newsPaperRepairs);
			// 修改状态
			NewspaperReport report = newsPaperReportService.findDataById(datajson.getString("newspaper_id"));
			report.setNewspaper_status(datajson.getString("repairs_type"));
			report.setEnd_time(null);
			newsPaperReportService.update(report);
			// 图片处理
			if (datajson.containsKey("image_list")) {
				JSONArray imageList = datajson.getJSONArray("image_list");
				for (int i = 0; i < imageList.size(); i++) {
					JSONObject imageObject = imageList.getJSONObject(i);
					// 目录处理
					String savePath = imageFilePath.replace("\\", "/")+"UploadFiles/"+current;
					File file = new File(savePath);
					if (!file.exists() && !file.isDirectory()) {
						file.mkdirs();
					}
					String fileName = UUID.randomUUID().toString()+".png";
					// 图片本地存储
					String imagePath = savePath + "/" +fileName;
					byte[] imageByte = Base64.decodeBase64(imageObject.getString("image_data"));
					FileUtils.writeByteArrayToFile(new File(imagePath), imageByte);
					// 图片数据存储
					Image image = new Image();
					image.setImage_id(UUID.randomUUID().toString());
					image.setImage_name(fileName);
					image.setImage_remark("事件处理手机客户端上传的图片");
					image.setImage_url("UploadFiles/"+current+"/"+fileName);
					image.setImage_date(new Date());
					image.setImage_owner_table("t_newspaper_repairs");
					image.setImage_owner_id(newsPaperRepairs.getRepairs_id());
					image.setImage_order((i+1)+"");
					imageService.insert(image);
				}
			}
			result.put("functionStatus", true);
			result.put("functionMessage", "[OK]");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("functionStatus", false);
			result.put("functionMessage", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 费用列表
	 * @param data
	 * @return
	 */
	private JSONObject chargeList(String data) {
		JSONObject result = new JSONObject();
		try {
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			double total = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar current = Calendar.getInstance();
			int currentym = Integer.parseInt(sdf.format(current.getTime()));
			JSONArray costList = new JSONArray();
			JSONArray estateArray = new JSONArray();
			JSONObject datajson = JSONObject.parseObject(data);
			List<Map<String, Object>> list = ownerService.findEstateByUserId(datajson.getString("user_id"));
			if (null == list || list.size() == 0) {
				result.put("details", estateArray);
				result.put("costDetails", costList);
				result.put("total", "0.00");
				result.put("functionStatus", false);
				result.put("functionMessage", "未检测到该用户的房屋信息!");
			} else {
				List<Map<String, Object>> estateList = this.buildEstate(list);
				for (Map<String, Object> estateMap : estateList) {
					JSONObject estateObject = new JSONObject();
					estateObject.put("estate_id", estateMap.get("estate_id"));
					estateObject.put("estate_name", estateMap.get("estate_name"));
					List<Map<String, Object>> houseList = this.buildHouse(list, estateMap.get("estate_id").toString());
					JSONArray houseArray = new JSONArray();
					for (Map<String, Object> houseMap : houseList) {
						JSONObject houseObject = new JSONObject();
						houseObject.put("house_id", houseMap.get("house_id"));
						houseObject.put("house_location", houseMap.get("house_location"));
						houseObject.put("building_id", houseMap.get("building_id"));
						houseObject.put("building_name", houseMap.get("building_name"));
						List<OwnerBill> ownerBills = ownerBillService.findDataByHouseAndOwnerId(houseMap.get("house_id").toString(), houseMap.get("owner_id").toString());
						JSONArray billArray = new JSONArray();
						for (OwnerBill ownerBill : ownerBills) {
							int batch = Integer.parseInt(ownerBill.getBatch());
							if (currentym >= batch && ownerBill.getStatus() == 0) {
								JSONObject jsonObject = new JSONObject();
								jsonObject.put("standard_name", ownerBill.getStandard_name());
								jsonObject.put("bill_id", ownerBill.getBill_id());
								jsonObject.put("owner_id", ownerBill.getOwner_id());
								jsonObject.put("batch", ownerBill.getBatch());
								jsonObject.put("money", ownerBill.getMoney());
								total += Double.parseDouble(ownerBill.getMoney());
								billArray.add(jsonObject);
							}
						}
						houseObject.put("billDetails", billArray);
						houseArray.add(houseObject);
					}
					estateObject.put("houseDetails", houseArray);
					estateArray.add(estateObject);
				}
				// 缴费记录
				TUserExtend tUserExtend = userExtendService.findDataByUserId(datajson.getString("user_id"));
				List<Map<String, Object>> cost = payCostService.findPayCostByHouseAndOwnerId(tUserExtend.getOwner_id(), "");
				if (null != cost && cost.size() > 0) {
					List<String> estateNameList = new ArrayList<>();
					for (Map<String, Object> map : cost) {
						if (JSON.toJSON(estateNameList).toString().indexOf(map.get("estate_name").toString()) == -1) {
							estateNameList.add(map.get("estate_name").toString());
						}
					}
					for (String estateName : estateNameList) {
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("estate_name", estateName);
						double money = this.buildMoney(cost, estateName);
						jsonObject.put("total_money", decimalFormat.format(money));
						jsonObject.put("paycost_time", cost.get(0).get("paycost_time"));
						costList.add(jsonObject);
					}
				}
				result.put("details", estateArray);
				result.put("costDetails", costList);
				result.put("total", decimalFormat.format(total));
				result.put("functionStatus", true);
				result.put("functionMessage", "[OK]");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("details", new JSONArray());
			result.put("costDetails", new JSONArray());
			result.put("total", "0.00");
			result.put("functionStatus", false);
			result.put("functionMessage", e.getMessage());
		}
		return result;
	}
	
	private double buildMoney(List<Map<String, Object>> list, String estateName) {
		double result = 0;
		for (Map<String, Object> map : list) {
			if (estateName.equals(map.get("estate_name").toString())) {
				result += Double.parseDouble(map.get("paycost_moneny").toString());
			}
		}
		return result;
	}
	
	private List<Map<String, Object>> buildEstate(List<Map<String, Object>> list) {
		List<Map<String, Object>> result = new ArrayList<>();
		for (Map<String, Object> map : list) {
			if (JSON.toJSON(result).toString().indexOf(map.get("estate_id").toString()) == -1){
				result.add(map);
			}
		}
		return result;
	}
	
	private List<Map<String, Object>> buildHouse(List<Map<String, Object>> list, String estate_id) {
		List<Map<String, Object>> result = new ArrayList<>();
		for (Map<String, Object> map : list) {
			if (estate_id.equals(map.get("estate_id").toString())) {
				result.add(map);
			}
		}
		return result;
	}
	
	/**
	 * 公告列表
	 * @param data
	 * @return
	 */
	private JSONObject noticeDetail(String data) {
		JSONObject result = new JSONObject();
		try {
			JSONObject datajson = JSONObject.parseObject(data);
			List<Announcement> list = announcementService.findDataByBuildingId(datajson.getString("building_id"));
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				Announcement an = list.get(i);
				JSONObject jsonObject = new JSONObject();
				if (0 == an.getStatus()) {
					jsonObject.put("title", an.getTitle());
					jsonObject.put("content", an.getContent());
					jsonObject.put("id", an.getId());
					if (null != an.getSend_time()) {
						jsonObject.put("send_time", sdf.format(an.getSend_time()));
					} else {
						jsonObject.put("send_time", sdf.format(an.getCreatetime()));
					}
					array.add(jsonObject);
				} else {
					Calendar cal = Calendar.getInstance();
					cal.setTime(an.getSend_time());
					if (calendar.getTimeInMillis() >= cal.getTimeInMillis()) {
						jsonObject.put("title", an.getTitle());
						jsonObject.put("content", an.getContent());
						jsonObject.put("id", an.getId());
						if (null != an.getSend_time()) {
							jsonObject.put("send_time", sdf.format(an.getSend_time()));
						} else {
							jsonObject.put("send_time", sdf.format(an.getCreatetime()));
						}
						array.add(jsonObject);
					}
				}
			}
			result.put("details", array);
			result.put("functionStatus", true);
			result.put("functionMessage", "[OK]");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("functionStatus", false);
			result.put("functionMessage", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 缴费
	 * @param data
	 * @return
	 */
	@Transactional
	private JSONObject chargeSave(String data) {
		JSONObject result = new JSONObject();
		try {
			JSONObject datajson = JSONObject.parseObject(data);
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			OwnerHouseDetail ownerHouseDetail = ownerService.findLiveUserByHouseId(datajson.getString("house_id"));
			if (null != ownerHouseDetail) {
				JSONArray array = datajson.getJSONArray("billDetail");
				EstateOwnerDetail detail = ownerService.findDataById(ownerHouseDetail.getOwner_id());
				// 计算总金额
				double total = detail.getMoney().doubleValue();
				double costMoney = 0;
				for (int i = 0; i < array.size(); i++) {
					JSONObject jsonObject = array.getJSONObject(i);
					OwnerBill ob = ownerBillService.findDataById(jsonObject.getString("bill_id"));
					if (null != ob && ob.getStatus() == 0) {
						costMoney += jsonObject.getDouble("money");
					}
				}
				if (total - costMoney >= 0) {
					for (int i = 0; i < array.size(); i++) {
						JSONObject jsonObject = array.getJSONObject(i);
						OwnerBill ob = ownerBillService.findDataById(jsonObject.getString("bill_id"));
						if (null != ob && ob.getStatus() == 0) {
							double money = jsonObject.getDouble("money");
							PayCost payCost = new PayCost();
							payCost.setPaycost_id(Coder.getSerialCode20());
							payCost.setPaycost_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
							payCost.setPaycost_way("余额扣款");
							payCost.setPaycost_project(jsonObject.getString("standard_name"));
							payCost.setPaycost_moneny(jsonObject.getString("money"));
							payCost.setPaycost_balance(decimalFormat.format(total - money));
							total = total - money;
							payCost.setOwner_id(ownerHouseDetail.getOwner_id());
							payCost.setBill_id(jsonObject.getString("bill_id"));
							payCost.setHouse_id(ownerHouseDetail.getHouse_id());
							payCostService.insert(payCost);
							// 修改状态
							ownerBillService.updateStatus(jsonObject.getString("bill_id"));
						}
					}
					detail.setMoney(new BigDecimal(total));
					ownerService.update(detail);
					result.put("total", decimalFormat.format(detail.getMoney().doubleValue()));
					result.put("functionStatus", true);
					result.put("functionMessage", "[OK]");
				} else {
					result.put("total", "0.00");
					result.put("functionStatus", false);
					result.put("functionMessage", "余额不足!");
				}
			} else {
				result.put("total", "0.00");
				result.put("functionStatus", false);
				result.put("functionMessage", "房屋未绑定业主信息!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("functionStatus", false);
			result.put("functionMessage", e.getMessage());
		}
		return result;
	}
	
	private JSONObject eventDetail(String data) {
		JSONObject result = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		try {
			JSONObject datajson = JSONObject.parseObject(data);
			List<Map<String, Object>> list = newsPaperReportService.findRepairsByReportIdNoLimit(datajson.getString("newspaper_id"));
			JSONArray array = new JSONArray();
			for (Map<String, Object> map : list) {
				JSONObject object = new JSONObject();
				object.put("repairs_id", map.get("repairs_id"));
				object.put("repairs_type", map.get("repairs_type"));
				object.put("repairs_time", sdf.format(map.get("repairs_time")));
				object.put("repairs_remark", map.get("repairs_remark"));
				object.put("employees_name", map.get("employees_name"));
				List<Image> images = imageService.findImageByOwnerId(map.get("repairs_id").toString(), "t_newspaper_repairs");
				if (null != images && images.size() > 0) {
					JSONArray imageList = new JSONArray();
					for (Image image : images) {
						JSONObject imageObject = new JSONObject();
						imageObject.put("image_name", image.getImage_name());
						imageObject.put("image_url", basePath+image.getImage_url());
						imageList.add(imageObject);
					}
					object.put("image_list", imageList);
				} else {
					object.put("image_list", new JSONArray());
				}
				array.add(object);
			}
			result.put("details", array);
			result.put("functionStatus", true);
			result.put("functionMessage", "[OK]");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("functionStatus", false);
			result.put("functionMessage", e.getMessage());
		}
		return result;
	}
}

/**
 * @ClassName: Function 
 * @Description: 所有客户端接口方法名枚举
 * @author SunXiaoYong.Inc
 * @date 2017年2月13日 上午11:20:26
 */
enum Function {
	
	login,
	validate,
	validateCode,
	resetPassword,
	editNickName,
	validateUserInfo,
	opinion,
	serviceList,
	register,
	reportList,
	reportDetail,
	reportDetailRepairs,
	reportSave,
	eventList,
	deptEmployees,
	eventSave,
	chargeList,
	noticeDetail,
	chargeSave,
	refresh,
	eventDetail;
	public static Function findFunction(String function) {
		return valueOf(function);
	}
}
