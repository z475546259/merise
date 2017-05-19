package net.merise.platform.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.alibaba.fastjson.JSONObject;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;

/**
 * @ClassName: PushUtils 
 * @Description: APP消息推送工具类, 目前支持个推
 * @author SunXiaoYong.Inc
 * @date 2017年1月16日 下午3:35:07
 */
public class PushUtils {
	
	private String appId, appKey, masterSecret, url;

	/**
	 * 加载配置文件
	 */
	public PushUtils () {
		try {
			InputStream is = getClass().getResourceAsStream("/push.properties");
			Properties prop = new Properties();
			prop.load(is);
			this.appId = prop.getProperty("getui.appId", "");
			this.appKey = prop.getProperty("getui.appKey", "");
			this.masterSecret = prop.getProperty("getui.masterSecret", "");
			this.url = prop.getProperty("getui.url", "");
		} catch (Exception e) {
			Console.i("push.properties 属性文件加载失败...", PushUtils.class);
			e.printStackTrace();
		}
	}
	
	/**
	 * 单条消息推送
	 * @param linkTemplate 消息实体
	 * @param clientId 客户端ID, mui手机端通过 plus.push.getClientInfo().clientid 获取
	 * @return 推送状态
	 */
	public JSONObject pushToSingle(LinkTemplate linkTemplate, String clientId) {
		JSONObject result = new JSONObject();
		IGtPush push = new IGtPush(url, appKey, masterSecret);
		SingleMessage message = new SingleMessage();
		message.setOffline(true); // 设置离线推送
		message.setOfflineExpireTime(24 * 3600 * 1000); // 设置离线推送消息保存时间
		message.setData(linkTemplate);
		message.setPushNetWorkType(0); // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
		Target target = new Target();
		target.setAppId(appId);
		target.setClientId(clientId);
		IPushResult ret = null;
		try {
			ret = push.pushMessageToSingle(message, target);
		} catch (RequestException e) {
			e.printStackTrace();
			ret = push.pushMessageToSingle(message, target, e.getRequestId());
		}
		if (ret != null) {
			result.put("status", true);
			result.put("message", ret.getResponse().toString());
		} else {
			result.put("status", false);
			result.put("message", "服务器响应异常");
		}
		return result;
	}
	
	/**
	 * 群体消息推送
	 * @param notificationTemplate 消息实体
	 * @param clientIds 客户端id数组 mui手机端通过 plus.push.getClientInfo().clientid 获取
	 * @return 推送状态
	 */
	public JSONObject pushToList(NotificationTemplate notificationTemplate, String[] clientIds) {
		JSONObject result = new JSONObject();
		IGtPush push = new IGtPush(url, appKey, masterSecret);
		ListMessage message = new ListMessage();
		message.setOffline(true); // 设置离线推送
		message.setOfflineExpireTime(24 * 3600 * 1000); // 设置离线推送消息保存时间
		message.setData(notificationTemplate);
		// 设置推送列表
		List<Target> list = new ArrayList<Target>();
		for (int i = 0; i < clientIds.length; i++) {
			Target target = new Target();
			target.setAppId(appId);
			target.setClientId(clientIds[i]);
			list.add(target);
		}
		String taskId = push.getContentId(message);
		IPushResult ret = null;
		try {
			ret = push.pushMessageToList(taskId, list);
		} catch (RequestException e) {
			e.printStackTrace();
		}
		if (ret != null) {
			result.put("status", true);
			result.put("message", ret.getResponse().toString());
			push.cancelContentId(taskId); // 推送完了取消tashId
		} else {
			result.put("status", false);
			result.put("message", "服务器响应异常");
		}
		return result;
	}
	
	public String getAppId() {
		return appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public String getMasterSecret() {
		return masterSecret;
	}

	public String getUrl() {
		return url;
	}

	/**
	 * 推送实例
	 * @param args
	 */
	public static void main(String[] args) {
		PushUtils utils = new PushUtils();
		// 单条消息推送测试
		LinkTemplate linkTemplate = new LinkTemplate();
		linkTemplate.setAppId(utils.getAppId());
		linkTemplate.setAppkey(utils.getAppKey());
		linkTemplate.setTitle("通知栏标题");
		linkTemplate.setText("通知栏内容");
		linkTemplate.setLogo("icon.png");// 配置通知栏图标
		linkTemplate.setLogoUrl("");// 配置通知栏网络图标，填写图标URL地址
		linkTemplate.setIsRing(true); // 设置通知是否响铃
		linkTemplate.setIsVibrate(true); // 设置通知是否震动
		linkTemplate.setIsClearable(true); // 设置通知是否可清除
		linkTemplate.setUrl("http://www.baidu.com");// 设置打开的网址地址
		JSONObject j1 = utils.pushToSingle(linkTemplate, "54d9834acbc942c323234a67a4acc460");
		System.out.println(j1.toJSONString());
		// 群体消息推送
		NotificationTemplate notificationTemplate = new NotificationTemplate();
		notificationTemplate.setAppId(utils.getAppId());
		notificationTemplate.setAppkey(utils.getAppKey());
		notificationTemplate.setTitle("通知栏标题");
		notificationTemplate.setText("通知栏内容");
		notificationTemplate.setLogo("icon.png");// 配置通知栏图标
		notificationTemplate.setLogoUrl("");// 配置通知栏网络图标，填写图标URL地址
		notificationTemplate.setIsRing(true); // 设置通知是否响铃
		notificationTemplate.setIsVibrate(true); // 设置通知是否震动
		notificationTemplate.setIsClearable(true); // 设置通知是否可清除
		notificationTemplate.setTransmissionType(2); // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		notificationTemplate.setTransmissionContent("请输入您要透传的内容");
		JSONObject j2 = utils.pushToList(notificationTemplate, new String[]{"", ""});
		System.out.println(j2.toJSONString());
	}
}
