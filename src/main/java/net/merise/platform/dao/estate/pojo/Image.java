package net.merise.platform.dao.estate.pojo;

import java.util.Date;

/**
 * 图片表
 * @author Administrator
 *
 */
public class Image {

	private String image_id;
	private String image_name;
	private String image_remark;
	private String image_url;
	private Date image_date;
	private String image_owner_table;
	private String image_owner_id;
	private String image_order;

	public String getImage_id() {
		return image_id;
	}

	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public String getImage_remark() {
		return image_remark;
	}

	public void setImage_remark(String image_remark) {
		this.image_remark = image_remark;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public Date getImage_date() {
		return image_date;
	}

	public void setImage_date(Date image_date) {
		this.image_date = image_date;
	}

	public String getImage_owner_table() {
		return image_owner_table;
	}

	public void setImage_owner_table(String image_owner_table) {
		this.image_owner_table = image_owner_table;
	}

	public String getImage_owner_id() {
		return image_owner_id;
	}

	public void setImage_owner_id(String image_owner_id) {
		this.image_owner_id = image_owner_id;
	}

	public String getImage_order() {
		return image_order;
	}

	public void setImage_order(String image_order) {
		this.image_order = image_order;
	}

}
