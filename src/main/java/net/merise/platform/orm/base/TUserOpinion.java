package net.merise.platform.orm.base;
import java.util.Date;

public class TUserOpinion {

	private String opinion_id;
	private String user_id;
	private String opinion_content;
	private Date opinion_date;
	private String opinion_reply;
	private String opinion_type;

	public void setOpinion_id(String opinion_id) {
		this.opinion_id = opinion_id;
	}
	public String getOpinion_id() {
		return opinion_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setOpinion_content(String opinion_content) {
		this.opinion_content = opinion_content;
	}
	public String getOpinion_content() {
		return opinion_content;
	}
	public void setOpinion_date(Date opinion_date) {
		this.opinion_date = opinion_date;
	}
	public Date getOpinion_date() {
		return opinion_date;
	}
	public void setOpinion_reply(String opinion_reply) {
		this.opinion_reply = opinion_reply;
	}
	public String getOpinion_reply() {
		return opinion_reply;
	}
	public void setOpinion_type(String opinion_type) {
		this.opinion_type = opinion_type;
	}
	public String getOpinion_type() {
		return opinion_type;
	}
}
