package net.merise.platform.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @ClassName: JDBC 
 * @Description: JDBC数据库连接获取
 * @author SunXiaoYong.Inc
 * @date 2016年9月5日 下午2:28:13
 */
public class JDBC {

	private JDBC() {}
	
	private static final JDBC jdbc = new JDBC();
	
	public static JDBC getInstance() {
		return jdbc;
	}
	
	/**
	 * 获取数据库连接
	 * @return connection
	 */
	public Connection getConnection() {
		Connection con = null;
		String URL = "";
		String USER = "";
		String PASSWORD = "";
		InputStream is = getClass().getResourceAsStream("/jdbc.properties");
		Properties prop = new Properties();
		 try {
				prop.load(is);
				URL = prop.getProperty("jdbc.url").trim();
				USER = prop.getProperty("jdbc.username").trim();
				PASSWORD = prop.getProperty("jdbc.password").trim();
			} catch (IOException e) {
				e.printStackTrace();
			}
		try {
			Class.forName(prop.getProperty("jdbc.driverClassName").trim());
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
