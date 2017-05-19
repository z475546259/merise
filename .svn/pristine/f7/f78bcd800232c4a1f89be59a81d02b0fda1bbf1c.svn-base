package net.merise.platform.orm.base;

import java.io.Serializable;

/**
 * @ClassName: TableParams 
 * @Description: 自动生成SQLMAP实体类
 * @author SunXiaoYong.Inc
 * @date 2016年12月29日 上午11:16:52
 */
public class TableParams implements Serializable {

	private static final long serialVersionUID = 1143531572499135435L;
	private String tableName; // 表名
	private String orderColumn; // 排序字段
	private String orderType = "desc"; // 排序方式，默认为desc
	private String[] likeColumn; // 模糊查询字段
	private String[] likeValue; // 模糊查询参数
	
	public TableParams(String tableName, String orderColumn) {
		this.tableName = tableName;
		this.orderColumn = orderColumn;
	}
	
	public TableParams(String tableName, String orderColumn, String likeColumn[], String likeValue[]) {
		this.tableName = tableName;
		this.orderColumn = orderColumn;
		this.likeColumn = likeColumn;
		this.likeValue = likeValue;
	}
	
	public TableParams(String tableName, String orderColumn, String orderType, String[] likeColumn, String[] likeValue) {
		this.tableName = tableName;
		this.orderColumn = orderColumn;
		this.orderType = orderType;
		this.likeColumn = likeColumn;
		this.likeValue = likeValue;
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getOrderColumn() {
		return orderColumn;
	}
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String[] getLikeColumn() {
		return likeColumn;
	}

	public void setLikeColumn(String[] likeColumn) {
		this.likeColumn = likeColumn;
	}

	public String[] getLikeValue() {
		return likeValue;
	}

	public void setLikeValue(String[] likeValue) {
		this.likeValue = likeValue;
	}
	
}
