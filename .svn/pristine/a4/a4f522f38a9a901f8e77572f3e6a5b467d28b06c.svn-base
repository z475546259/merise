package net.merise.platform.utils;

import java.io.InputStream;
import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @ClassName: MailUtils 
 * @Description: 邮件工具类, 支持邮件单发和群发, 支持Properties配置文件和参数方式
 * @author SunXiaoYong.Inc
 * @date 2017年1月13日 上午11:03:32
 */
public class MailUtils {
	
	private String HOST,ACCOUNT,PASSWORD,NICKNAME,ENCODING;
	
	/**
	 * 配置文件方式初始化
	 */
	public MailUtils() {
		try {
			InputStream is = getClass().getResourceAsStream("/mail.properties");
			Properties prop = new Properties();
			prop.load(is);
			this.HOST = prop.getProperty("mail.smtp.host");
			this.ACCOUNT = prop.getProperty("mail.login.account");
			this.PASSWORD = prop.getProperty("mail.login.password");
			this.NICKNAME = prop.getProperty("mail.nickname");
			this.ENCODING = prop.getProperty("mail.encoding", "utf-8");
			Console.i("邮件配置 "+prop.toString(), MailUtils.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 参数传递方式初始化
	 * @param host smtp服务器地址, 支持QQ邮箱smtp服务器
	 * @param account 邮箱登录账号
	 * @param password 邮箱登录密码
	 * @param encoding 邮件编码格式
	 * @param nickname 对方邮件显示的发件人昵称
	 */
	public MailUtils(String host, String account, String password, String encoding, String nickname) {
		this.HOST = host;
		this.ACCOUNT = account;
		this.PASSWORD = password;
		this.NICKNAME = encoding;
		this.ENCODING = nickname;
	}
	
	/**
	 * 邮件发送参数
	 * @return Properties
	 */
	private Properties getMailProperties() {
		Properties properties = new Properties();
		properties.put("mail.host", "smtp.qq.com");
		properties.put("mail.smtp.timeout","25000");
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.port", 465);
		properties.put("mail.smtp.socketFactory.port", 465);
		return properties;
	}
	
	/**
	 * 邮件单发
	 * @param subject 邮件标题
	 * @param text 邮件内容, 采用html方式, 文本格式容易被拦截
	 * @param to 收件人
	 */
	public void sendTextMail(String subject, String text, String to) {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		// 设置邮件服务器
		javaMailSender.setHost(this.HOST);
		javaMailSender.setUsername(this.ACCOUNT);
		javaMailSender.setPassword(this.PASSWORD);
		javaMailSender.setDefaultEncoding(this.ENCODING);
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
		try {
			// 设置收件人
			messageHelper.setTo(to);
			InternetAddress from = new InternetAddress(this.ACCOUNT, this.NICKNAME);
			messageHelper.setFrom(from);
			messageHelper.setSubject(subject);
			messageHelper.setText(text, true);
			javaMailSender.setJavaMailProperties(getMailProperties());
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 邮件群发
	 * @param subject 邮件标题
	 * @param text 邮件内容, 采用html方式, 文本格式容易被拦截
	 * @param to 收件人数组
	 */
	public void sendTextMail(String subject, String text, String[] to) {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		// 设置邮件服务器
		javaMailSender.setHost(this.HOST);
		javaMailSender.setUsername(this.ACCOUNT);
		javaMailSender.setPassword(this.PASSWORD);
		javaMailSender.setDefaultEncoding(this.ENCODING);
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
		try {
			// 设置收件人
			messageHelper.setTo(to);
			InternetAddress from = new InternetAddress(this.ACCOUNT, this.NICKNAME);
			messageHelper.setFrom(from);
			messageHelper.setSubject(subject);
			messageHelper.setText(text, true);
			javaMailSender.setJavaMailProperties(getMailProperties());
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		MailUtils utils = new MailUtils();
		StringBuilder sb = new StringBuilder();
		sb.append("<html><head></head><body><h4>");
		sb.append("用以煎煮润肺杀虫的药剂，或把治疗疥癣、虫癞的散剂调成外敷药，可以增强疗效。<br/>");
		sb.append("白花露：止消渴。<br/>");
		sb.append("</h4></body></html>");
		// 单个邮件发送
		utils.sendTextMail("在秋露重的时候，早晨去花草间收取", sb.toString(), "lffx1988@aliyun.com");
		// 多个邮件发送
		String[] to = new String[]{"lffx1988@aliyun.com","123455@qq.com","5254521@qq.com"};
		utils.sendTextMail("在秋露重的时候，早晨去花草间收取", sb.toString(), to);
	}
}
