package net.merise.platform.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.EstateBuildingDetail;
import net.merise.platform.dao.estate.pojo.EstateCodeDetail;
import net.merise.platform.dao.estate.pojo.EstateInfoDetail;
import net.merise.platform.dao.estate.pojo.EstateOwnerDetail;
import net.merise.platform.dao.estate.pojo.TMessageTask;
import net.merise.platform.service.estate.EstateBuildingDetailService;
import net.merise.platform.service.estate.EstateCodeEetailService;
import net.merise.platform.service.estate.EstateHouseDetailService;
import net.merise.platform.service.estate.EstateInfoDetailService;
import net.merise.platform.service.estate.IMessageTaskService;
import net.merise.platform.service.estate.OwnerService;
import net.merise.platform.utils.Coder;
import net.merise.platform.utils.Console;
import net.merise.platform.utils.IdcardInfoExtractor;
import net.merise.platform.utils.IdcardValidator;
import net.merise.platform.utils.MailUtils;
import net.merise.platform.utils.MessageUtils;
import net.merise.platform.utils.PushUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gexin.rp.sdk.template.NotificationTemplate;

/**
 * @ClassName: MessageFestivalController 
 * @Description: 生日祝福
 * @author SunXiaoYong.Inc
 * @date 2017年1月19日 下午3:22:53
 */
@Controller
@RequestMapping("/festival")
@Transactional
public class MessageFestivalController {
	
	@Autowired
	private EstateInfoDetailService estateInfoDetailService;
	
	@Autowired
	private EstateBuildingDetailService estateBuildingDetailService;
	
	@Autowired
	private EstateHouseDetailService estateHouseDetailService;
	
	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private EstateCodeEetailService codeEetailService;
	
	@Autowired
	private IMessageTaskService messageTaskService;
	
	/**
	 * APP消息发送
	 * @param data 选中的JSON数据
	 * @param title 标题
	 * @param content 消息内容
	 * @return 发送提示信息
	 */
	@RequestMapping("/app")
	public @ResponseBody JSONObject app(String data, String title, String content) {
		JSONObject result = new JSONObject();
		try {
			JSONArray array = JSONArray.parseArray(data);
			List<String> clientIds = new ArrayList<String>();
			for (int i = 0; i < array.size(); i++) {
				JSONObject object = array.getJSONObject(i);
				if (object.containsKey("clientid")) {
					String clientid = object.getString("clientid");
					if (!"".equals(Coder.NullToBlank(clientid))) {
						clientIds.add(clientid);
					}
				}
			}
			// 调用PushUtils中的方法推送APP消息
			PushUtils utils = new PushUtils();
			NotificationTemplate notificationTemplate = new NotificationTemplate();
			notificationTemplate.setAppId(utils.getAppId());
			notificationTemplate.setAppkey(utils.getAppKey());
			notificationTemplate.setTitle(title);
			notificationTemplate.setText(content);
			notificationTemplate.setLogo("icon.png");// 配置通知栏图标
			notificationTemplate.setLogoUrl("");// 配置通知栏网络图标，填写图标URL地址
			notificationTemplate.setIsRing(true); // 设置通知是否响铃
			notificationTemplate.setIsVibrate(true); // 设置通知是否震动
			notificationTemplate.setIsClearable(true); // 设置通知是否可清除
			notificationTemplate.setTransmissionType(2); // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
			notificationTemplate.setTransmissionContent(content);
			if (clientIds.size() > 0) {
				String[] cs = clientIds.toArray(new String[clientIds.size()]); // 将客户端集合转为数组
				JSONObject re = utils.pushToList(notificationTemplate, cs); // 推送消息
				Console.i("推送返回："+re.toJSONString(), MessageFestivalController.class);
			}
			// 返回状态
			result.put("status", true);
			result.put("message", "消息已发送!");
		} catch (Exception e) {
			result.put("status", false);
			result.put("message", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 邮件发送
	 * @param data 选中的JSON数据
	 * @param title 标题
	 * @param content 消息内容
	 * @return 发送提示信息
	 */
	@RequestMapping("/email")
	public @ResponseBody JSONObject email(String data, String title,String content) {
		JSONObject result = new JSONObject();
		try {
			JSONArray array = JSONArray.parseArray(data);
			StringBuilder emails = new StringBuilder();
			for (int i = 0; i < array.size(); i++) {
				JSONObject object = array.getJSONObject(i);
				if (object.containsKey("owner_email") && !Coder.NullToBlank(object.getString("owner_email")).equals("")) {
					if ((i+1) == array.size()) {
						emails.append(object.getString("owner_email"));
					} else {
						emails.append(object.getString("owner_email")+",");
					}
				}
			}
			System.out.println(emails);
			String[] mails = emails.toString().split(",");
			// 调用邮件发送的代码
			MailUtils mailUtils = new MailUtils();
			mailUtils.sendTextMail(title, content, mails);
			// 返回状态
			result.put("status", true);
			result.put("message", "消息已发送!");
		} catch (Exception e) {
			result.put("status", false);
			result.put("message", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 短信发送
	 * @param data 选中的JSON数据
	 * @param title 标题
	 * @param content 消息内容
	 * @return 发送提示信息
	 */
	@RequestMapping("/message")
	public @ResponseBody JSONObject message(String data, String title, String content) {
		JSONObject result = new JSONObject();
		try {
			JSONArray array = JSONArray.parseArray(data);
			StringBuilder ms = new StringBuilder();
			for (int i = 0; i < array.size(); i++) {
				JSONObject object = array.getJSONObject(i);
				if (object.containsKey("owner_mobile") && !Coder.NullToBlank(object.getString("owner_mobile")).equals("")) {
					if ((i+1) == array.size()) {
						ms.append(object.getString("owner_mobile"));
					} else {
						ms.append(object.getString("owner_mobile")+",");
					}
				}
			}
			String[] mobiles = ms.toString().split(",");
			// 调用MessageUtils中的方法发送短信，未实现具体发送的方法
			MessageUtils.send(mobiles, content);
			// 返回状态
			result.put("status", true);
			result.put("message", "消息已发送!");
		} catch (Exception e) {
			result.put("status", false);
			result.put("message", e.getMessage());
			e.printStackTrace();
		}
		return result;
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
				// 查询栋
				List<String> listDong = estateHouseDetailService.findDong(estateBuildingDetail.getBuilding_id());
				JSONArray dongArray = new JSONArray();
				for (String string : listDong) {
					JSONObject dongObject = new JSONObject();
					dongObject.put("id", estateBuildingDetail.getBuilding_id());
					dongObject.put("text", string);
					JSONObject dongAttr = new JSONObject();
					dongAttr.put("level", "house");
					dongObject.put("attributes", dongAttr);
					dongArray.add(dongObject);
				}
				buildObject.put("children", dongArray);
				buildArray.add(buildObject);
			}
			object.put("children", buildArray);
			result.add(object);
		}
		return result;
	}
	
	/**
	 * 查询房屋数据
	 * @param page 页数
	 * @param rows 每页显示的行数
	 * @param houseStatus 房屋状态
	 * @param buildId 楼盘编号
	 * @param dong 栋号
	 * @return JSON格式房屋数据
	 */
	@RequestMapping("/index")
	public @ResponseBody Map<String, Object> findHouseOwner(int page, int rows, String houseStatus, String buildId, String dong) {
		// 处理分页查询开始记录
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("building_id", buildId);
		dataMap.put("dong", dong);
		dataMap.put("owner_name", "");
		dataMap.put("owner_mobile", "");
		dataMap.put("owner_idcard", "");
		// 返回数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<EstateOwnerDetail> details = ownerService.findOwnerByDong(dataMap);
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		String current = sdf.format(new Date());
		if ("".equals(Coder.NullToBlank(houseStatus))) {
			// 当天生日查询处理
			List<EstateOwnerDetail> festival = new ArrayList<EstateOwnerDetail>();
			for (EstateOwnerDetail estateOwnerDetail : details) {
				if (!"".equals(Coder.NullToBlank(estateOwnerDetail.getOwner_idcard()))) {
					IdcardValidator validator = new IdcardValidator();
					boolean v = validator.isValidatedAllIdcard(estateOwnerDetail.getOwner_idcard());
//					System.out.println(estateOwnerDetail.getOwner_name()+", "+estateOwnerDetail.getOwner_idcard()+", "+v);
					if (v) {
						IdcardInfoExtractor extractor = new IdcardInfoExtractor(estateOwnerDetail.getOwner_idcard());
						String userFestival = sdf.format(extractor.getBirthday());
						if (current.equals(userFestival)) {
							festival.add(estateOwnerDetail);
						}
					}
				}
			}
			resultMap.put("total", festival.size());
			resultMap.put("rows", festival);
		} else {
			// 房屋状态查询处理
			List<EstateOwnerDetail> temp = new ArrayList<EstateOwnerDetail>();
			for (EstateOwnerDetail estateOwnerDetail : details) {
				if (houseStatus.equals(estateOwnerDetail.getHouse_status())) {
					temp.add(estateOwnerDetail);
				}
			}
			// 当天生日查询处理
			List<EstateOwnerDetail> festival = new ArrayList<EstateOwnerDetail>();
			for (EstateOwnerDetail estateOwnerDetail : temp) {
				if (!"".equals(Coder.NullToBlank(estateOwnerDetail.getOwner_idcard()))) {
					IdcardValidator validator = new IdcardValidator();
					boolean v = validator.isValidatedAllIdcard(estateOwnerDetail.getOwner_idcard());
					if (v) {
						IdcardInfoExtractor extractor = new IdcardInfoExtractor(estateOwnerDetail.getOwner_idcard());
						String userFestival = sdf.format(extractor.getBirthday());
						if (current.equals(userFestival)) {
							festival.add(estateOwnerDetail);
						}
					}
				}
			}
			resultMap.put("total", festival.size());
			resultMap.put("rows", festival);
		}
		return resultMap;
	}
	
	/**
	 * 房屋状态列表
	 * @param buildId 楼盘编号
	 * @return JSON格式数据
	 */
	@RequestMapping("/housestatus")
	public @ResponseBody JSONArray findHouseStatus(String buildId) {
		JSONArray result = new JSONArray();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("code_name", "房屋状态");
		dataMap.put("building_id", buildId);
		List<EstateCodeDetail> codeDetails = codeEetailService.findByCodeNameAndBuilding(dataMap);
		for (EstateCodeDetail estateCodeDetail : codeDetails) {
			JSONObject object = new JSONObject();
			object.put("id", estateCodeDetail.getCode_id());
			object.put("text", estateCodeDetail.getCode_content());
			result.add(object);
		}
		return result;
	}
	
	/**
	 * 自动推送生日祝福
	 * @param messageTask 实体
	 * @return 推送状态
	 */
	@RequestMapping("/task")
	public @ResponseBody JSONObject autoTask(TMessageTask messageTask) {
		JSONObject result = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("building_id", messageTask.getBuilding_id());
			map.put("house_dong", messageTask.getHouse_dong());
			List<TMessageTask> temp = messageTaskService.findDataByParams(map);
			// 判断是否有任务，如果有就修改，没有就新增
			if (temp != null && temp.size() > 0) {
				TMessageTask t = temp.get(0);
				messageTask.setMessage_id(t.getMessage_id());
				messageTask.setMessage_status(t.getMessage_status());
				messageTask.setCreate_user(t.getCreate_user());
				messageTask.setCreate_date(t.getCreate_date());
				messageTask.setBuilding_id(t.getBuilding_id());
				messageTask.setHouse_dong(t.getHouse_dong());
				messageTaskService.update(messageTask);
			} else {
				messageTask.setMessage_id(Coder.getSerialCode20());
				messageTask.setCreate_user("");
				messageTask.setCreate_date(new Date());
				messageTaskService.insert(messageTask);
			}
			result.put("status", true);
			result.put("message", "操作成功");
		} catch (Exception e) {
			result.put("status", false);
			result.put("message", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 自动任务查询
	 * @param building_id 楼盘编号
	 * @param house_dong 房屋栋号
	 * @return 数据
	 */
	@RequestMapping("/taskSearch")
	public @ResponseBody JSONObject selectTask(String building_id, String house_dong) {
		JSONObject result = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("building_id", building_id);
			map.put("house_dong", house_dong);
			List<TMessageTask> temp = messageTaskService.findDataByParams(map);
			if (temp != null && temp.size() > 0) {
				result.put("status", true);
				result.put("message", "[OK]");
				result.put("data", temp.get(0));
			} else {
				result.put("status", true);
				result.put("message", "[no search data]");
				result.put("data", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", false);
			result.put("message", "["+e.getMessage()+"]");
			result.put("data", "");
		}
		return result;
	}
}
