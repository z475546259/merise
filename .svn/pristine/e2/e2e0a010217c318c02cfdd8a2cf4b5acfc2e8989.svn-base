package net.merise.platform.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * @ClassName: HttpUtils
 * @Description: http请求类 
 * @Package: net.merise.platform.utils
 * @author: SunXiaoYONG.Inc
 * @version: v1.0 
 * @date: 2015-12-4 下午4:38:24
 */
public class HttpUtils {
	
	private HttpUtils(){}
	
	private static final HttpUtils httpHandle = new HttpUtils();
	
	public static HttpUtils getInstance() {
		return httpHandle;
	}

	/**
	 * @Title: send 
	 * @Description: http数据发送
	 * @param sendurl 链接
	 * @param params 参数类型为xx=12&yy=33
	 * @param postType 请求类型(POST,GET)
	 * @return 返回响应的数据
	 * @author SunXiaoYONG.Inc
	 * @date 2015-4-16 下午5:41:11
	 */
	public String send(String sendurl, String params, String postType) {
		String res = "";
	    try {
	    	URL url = new URL(sendurl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod(postType);
			conn.setDoOutput(true);
			conn.setRequestProperty("content-type", "text/html");
			byte[] bytes = params.toString().getBytes("utf-8");
			conn.getOutputStream().write(bytes);
			InputStream is = conn.getInputStream();
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		    byte[] buffer = new byte[1024];
		    int len = 0;
		    while( (len = is.read(buffer)) !=-1 ){
		        outStream.write(buffer, 0, len);
		    }
		    byte[] data = outStream.toByteArray();//网页的二进制数据
		    outStream.close();
		    is.close();
		    conn.disconnect();
		    res = new String(data, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return res;
	}
	
	/**
	 * @Title: httpsPost 
	 * @Description: http SSL请求
	 * @param certFile 证书文件
	 * @param password 证书密码
	 * @param url 请求链接
	 * @param data 请求数据
	 * @return 返回数据
	 * @throws IOException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws UnrecoverableKeyException
	 * @throws CertificateException
	 * @throws KeyManagementException
	 * @throws CertificateException
	 * @author SunXiaoYONG.Inc
	 * @date 2015-12-4 下午4:30:17
	 */
	public String httpSSLPost(File certFile, String password, String url, String data) throws IOException, KeyStoreException,
			NoSuchAlgorithmException, UnrecoverableKeyException,
			CertificateException, KeyManagementException, CertificateException {
		HttpsURLConnection conn = (HttpsURLConnection) new URL(url).openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		KeyStore ks = KeyStore.getInstance("PKCS12");
		InputStream fin = null;
		try {
			fin = new FileInputStream(certFile);
			ks.load(fin, password.toCharArray());
		} finally {
			fin.close();
		}
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		kmf.init(ks, password.toCharArray());
		KeyManager[] kms = kmf.getKeyManagers();
		KeyStore caks = KeyStore.getInstance("JKS");
		try {
			fin = new FileInputStream(new File(System.getProperty("java.home")+ File.separatorChar + "lib" + File.separatorChar+ "security" + File.separatorChar + "cacerts"));
			caks.load(fin, "changeit".toCharArray());
		} finally {
			fin.close();
		}
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(caks);
		TrustManager[] tms = tmf.getTrustManagers();
		SSLContext ssl = SSLContext.getInstance("TLSv1");
		ssl.init(kms, tms, new SecureRandom());
		conn.setSSLSocketFactory(ssl.getSocketFactory());
		OutputStream out = conn.getOutputStream();
		out.write(data.getBytes("utf-8"));
		out.flush();
		out.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		conn.disconnect();
		return sb.toString();
	}
	
}
