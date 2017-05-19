package net.merise.platform.dao.estate.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告记录
 * @author Administrator
 *
 */
public class AnnouncementLog implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4585351486778137681L;

	private String id;
	
	private Integer type;
	
	private String title;
	
	private String content;
	
	private Integer status;
	
	private Date send_time;

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getSend_time() {
		return send_time;
	}

	public void setSend_time(Date send_time) {
		this.send_time = send_time;
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
		AnnouncementLog other = (AnnouncementLog) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AnnouncementLog [id=" + id + ", type=" + type + ", title="
				+ title + ", content=" + content + ", status=" + status
				+ ", send_time=" + send_time + "]";
	}
	
	

}
