package net.merise.platform.dao.estate.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 公告
 * @author Administrator
 *
 */
public class Announcement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6650761370068833799L;
	
	//公告id
	private String id;
	//公告类型
	private Integer type;
	
	private String building_id;
	
	private String house_dong;
	//公告标题
	private String title;
	//公告内容
	private String content;
	//是否定时发送
	private Integer is_send;
	//发送时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date send_time;
	//优先级
	private Integer priority;
	//状态
	private Integer status;
	//公告创建时间
	private Date createtime;
	//
	private String building_name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getIs_send() {
		return is_send;
	}
	public void setIs_send(Integer is_send) {
		this.is_send = is_send;
	}
	public Date getSend_time() {
		return send_time;
	}
	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Announcement other = (Announcement) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public String getBuilding_id() {
		return building_id;
	}
	public void setBuilding_id(String building_id) {
		this.building_id = building_id;
	}
	public String getHouse_dong() {
		return house_dong;
	}
	public void setHouse_dong(String house_dong) {
		this.house_dong = house_dong;
	}
	public String getBuilding_name() {
		return building_name;
	}
	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
	@Override
	public String toString() {
		return "Announcement [id=" + id + ", type=" + type + ", building_id="
				+ building_id + ", house_dong=" + house_dong + ", title="
				+ title + ", content=" + content + ", is_send=" + is_send
				+ ", send_time=" + send_time + ", priority=" + priority
				+ ", status=" + status + ", createtime=" + createtime
				+ ", building_name=" + building_name + "]";
	}
	
	

}
