package net.merise.platform.utils;

import java.text.SimpleDateFormat;

import net.merise.platform.dao.estate.pojo.TableParams;
import net.merise.platform.encrypt.AES;
import net.merise.platform.encrypt.MD5;

public class Test {

	public static void main(String[] args) {
		// SQLMAP文件处理
		TableParams params = new TableParams("t_paycost_copy", "user_id", new String[]{"user_id"}, new String[]{"#{user_id}"});
		String path = System.getProperty("user.dir")+"/src/main/java/net/merise/platform/orm/sql/";
		AutoGenerateSQLMAP.excute(path, "t_user_employees", "net.merise.platform.orm.mapper.UserEmployeesDAO", "net.merise.platform.orm.base.TUserEmployees");
		// 实体类处理
//		new GenEntityMysql("t_user_employees");
		System.out.println(MD5.encode("3a4e936cd3cdbe3d5a40e04e91a883cb"));
	}
}
