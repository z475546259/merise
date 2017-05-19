package net.merise.platform.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName: Coder 
 * @Description: 日常工具类
 * @author SunXiaoYong.Inc
 * @date 2016年9月5日 下午2:27:20
 */
public class Coder {
	
	/**
	 * 获取当前时间+4位随机数
	 * @return 字符串
	 */
	public static String getSerialCode20() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date())+(int)((Math.random()*9+1)*100000);
	}
	
	/**
	 * 生成4位随机数字
	 * @return 数字
	 */
	public static String getSerialCode4() {
		return (int)((Math.random()*9+1)*1000)+"";
	}
	
	/**
	 * 自定义格式化时间
	 * @param format 格式
	 * @return 格式化后的时间
	 */
	public static String getTime(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
	/**
	 * 获取格式化日期，秒自定义(可增减)
	 * @param format 日期格式
	 * @param second 描述
	 * @return 格式化之后的时间
	 */
	public static String getTimeAdd(String format, int second) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + second);
		return sdf.format(cal.getTime());
	}
	
	/**
	 * 根据出生日期计算年龄
	 * @param dateBirth 出生日期
	 * @return 年龄
	 */
	public static int getAge(Date dateBirth) {
		Calendar cal = Calendar.getInstance();
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH)+1;
		int dayNow = cal.get(Calendar.DAY_OF_MONTH);
		
		cal.setTime(dateBirth);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH)+1;
		int dayBirth = cal.get(Calendar.DAY_OF_MONTH);
		int age = yearNow - yearBirth;
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayNow < dayBirth) {
					age -- ;
				}
			} else {
				age -- ;
			}
		}
		return age;
	}
	
	public static String NullToBlank(String value) {
		if (value == null || "".equals(value) || "null".equals(value)) {
			return "";
		} else {
			return value.trim();
		}
	}
	
	public static String NullToZero(String value) {
		if (value == null || "".equals(value) || "null".equals(value)) {
			return "0";
		} else {
			return value.trim();
		}
	}
	
	/**
	 * 四舍五入取整
	 * @param temp
	 * @return
	 */
	public static String random(double temp) {
		return new BigDecimal(temp).setScale(0, BigDecimal.ROUND_HALF_UP)+"";
	}
	
	
	/**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String random(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    
    /**
     * @Title: RemoveLast 
     * @Description: 移除字符串的最后一位
     * @param param 字符串
     * @return 字符串
     * @author SunXiaoYONG.Inc
     * @date 2016-7-11 上午11:24:06
     */
    public static String RemoveLast(String param) {
    	return param.substring(0, param.length() - 1);
    }
    
    /**
     * 获取自定义个数随机数字串
     * @param length 数字串长度
     * @return 随机生成的数字串
     */
    public static String randomNumber(int length) {
    	String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
