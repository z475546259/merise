package net.merise.platform.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: AES 
 * @Description: aes加解密工具类
 * @author SunXiaoYong.Inc
 * @date 2016年9月5日 下午2:37:06
 */
public class AES {

	private static Logger logger = LoggerFactory.getLogger(AES.class);
	
	public static byte[] encryptToByte(String content) {
		try {
			SecretKeySpec key = new SecretKeySpec(new byte[] { 121, 100, 77, 52, 43, 127, 99, 73, 85, 39, 27, 103, 6, 48, 87, 68}, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] decryptToByte(byte[] content) {
		try {
			SecretKeySpec key = new SecretKeySpec(new byte[] { 121, 100, 77, 52, 43, 127, 99, 73, 85, 39, 27, 103, 6, 48, 87, 68}, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//解密并替换url中会被转义的"+"等等
	public static String decrypt(String str){
		try {
			return new String(decryptToByte(Base64.decodeBase64(str.replace("-", "+").replace("_", "/"))));
		} catch (Exception e) {
			logger.info("解密失败"+str,e);
		}
		return "";
	}
	
	//加密并替换url中会被转义的"+"等等
	public static String encrypt(String str){
		try {
			byte[] encryptResult = encryptToByte(str);  
			String af_str = Base64.encodeBase64String(encryptResult);
			return af_str==null?null:af_str.replace("+", "-").replace("/", "_");
		} catch (Exception e) {
			logger.info("加密失败"+str,e);
		}
		return "";
	}
	
}
