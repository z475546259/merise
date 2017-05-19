package net.merise.platform.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @ClassName: GenEntityMysql 
 * @Description: MySQL实体类生成
 * @author SunXiaoYong.Inc
 * @date 2016年12月29日 下午12:38:21
 */
public class GenEntityMysql {

	//指定实体生成所在包的路径
	private String packageOutPath = "net.merise.platform.orm.base";
	
	private String tableName; //表名
	private String[] colNames; // 列名数组
	private String[] colTypes; //列名类型数组
	private int[] colSizes; //列名大小数组
	private boolean f_util = false; // 是否需要导入包java.util.*
	private boolean f_sql = false; // 是否需要导入包java.sql.*
	
	public GenEntityMysql(String tableName) {
		this.tableName = tableName;
		Connection con = null;
		ResultSet rs = null;
		ResultSetMetaData data = null;
		PreparedStatement stmt = null;
		String path = System.getProperty("user.dir")+"/src/main/java/"+this.packageOutPath.replace(".", "/")+"/"+initcap(tableName)+".java";
		try {
			String query = "select * from "+tableName;
			con = JDBC.getInstance().getConnection();
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery(query);
			data = rs.getMetaData();
			int size = data.getColumnCount();
			colNames = new String[size];
			colTypes = new String[size];
			colSizes = new int[size];
			for (int i = 0; i < size; i++) {
				colNames[i] = data.getColumnName(i + 1);
				colTypes[i] = data.getColumnTypeName(i + 1);
				if(colTypes[i].equalsIgnoreCase("datetime") || colTypes[i].equalsIgnoreCase("date")){
					f_util = true;
				}
				if(colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")){
					f_sql = true;
				}
				colSizes[i] = data.getColumnDisplaySize(i + 1);
			}
			String content = parse(colNames, colTypes, colSizes);
			FileWriter fw = new FileWriter(new File(path));
			PrintWriter pw = new PrintWriter(fw);
			pw.write(content);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			Console.i("Gen Entity ["+tableName+"] file is finish", GenEntityMysql.class);
		}
	}
	
	private String parse(String[] colnames, String[] colTypes, int[] colSizes) {
		StringBuilder sb = new StringBuilder();
		sb.append("package " + this.packageOutPath + ";\r\n");
		if(f_util){
			sb.append("import java.util.Date;\r\n");
		}
		if(f_sql){
			sb.append("import java.sql.*;\r\n");
		}
		// 实体部分
		sb.append("\r\npublic class " + initcap(tableName) + " {\r\n");
		this.processAllAttrs(sb);
		this.processAllMethod(sb);
		sb.append("}\r\n");
		return sb.toString();
	}
	
	private String initcap(String str) {
		char[] ch = str.toCharArray();
		if(ch[0] >= 'a' && ch[0] <= 'z'){
			ch[0] = (char)(ch[0] - 32);
		}
		return this.getCamelStr(new String(ch));
	}
	
	private String initcap2(String str) {
		char[] ch = str.toCharArray();
		if(ch[0] >= 'a' && ch[0] <= 'z'){
			ch[0] = (char)(ch[0] - 32);
		}
		return new String(ch);
	}
	
	private String getCamelStr(String s){
		while(s.indexOf("_") > 0){
			int index = s.indexOf("_");
			s = s.substring(0, index) + s.substring(index+1, index+2).toUpperCase() + s.substring(index+2);
		}
		return s;
	}
	
	private void processAllAttrs(StringBuilder sb) {
		sb.append("\n");
		for (int i = 0; i < colNames.length; i++) {
			sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + colNames[i] + ";\r\n");
		}
	}
	
	private void processAllMethod(StringBuilder sb) {
		sb.append("\n");
		for (int i = 0; i < colNames.length; i++) {
			sb.append("\tpublic void set" + initcap2(colNames[i]) + "(" + sqlType2JavaType(colTypes[i]) + " " +colNames[i] + ") {\r\n");
			sb.append("\t\tthis." + colNames[i] + " = " + colNames[i] + ";\r\n");
			sb.append("\t}\r\n");
			sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap2(colNames[i]) + "() {\r\n");
			sb.append("\t\treturn " + colNames[i] + ";\r\n");
			sb.append("\t}\r\n");
		}
	}
	
	private String sqlType2JavaType(String sqlType) {
		if(sqlType.equalsIgnoreCase("bit")){
			return "boolean";
		} else if(sqlType.equalsIgnoreCase("tinyint")) {
			return "byte";
		} else if(sqlType.equalsIgnoreCase("smallint")) {
			return "short";
		} else if(sqlType.equalsIgnoreCase("int")) {
			return "int";
		} else if(sqlType.equalsIgnoreCase("bigint")) {
			return "long";
		} else if(sqlType.equalsIgnoreCase("float")) {
			return "float";
		} else if(sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric") || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money") || sqlType.equalsIgnoreCase("smallmoney")) {
			return "double";
		} else if(sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar") || sqlType.equalsIgnoreCase("text")) {
			return "String";
		} else if(sqlType.equalsIgnoreCase("datetime")){
			return "Date";
		} else if(sqlType.equalsIgnoreCase("image")){
			return "Blod";
		}
		return null;
	}
}
