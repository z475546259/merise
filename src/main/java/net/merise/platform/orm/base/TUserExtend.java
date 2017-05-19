package net.merise.platform.orm.base;

/**
 * @ClassName: TUserExtend 
 * @Description: 用户表扩展
 * @author SunXiaoYong.Inc
 * @date 2017年3月7日 下午5:16:33
 */
public class TUserExtend {

	private String extend_id;
	private String user_id;
	private String client_id;
	private String owner_id;

	public void setExtend_id(String extend_id) {
		this.extend_id = extend_id;
	}
	public String getExtend_id() {
		return extend_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}
	public String getOwner_id() {
		return owner_id;
	}
}
