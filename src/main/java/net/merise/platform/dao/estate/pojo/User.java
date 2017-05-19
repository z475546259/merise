package net.merise.platform.dao.estate.pojo;

import java.io.Serializable;

/**
 * @ClassName: User 
 * @Description: TODO
 * @author SunXiaoYong.Inc
 * @date 2016年12月29日 下午12:48:04
 */
public class User implements Serializable {

	private static final long serialVersionUID = -3364997446811905501L;
	private String id;
	private String username;
	private String password;
	private String nickname;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
