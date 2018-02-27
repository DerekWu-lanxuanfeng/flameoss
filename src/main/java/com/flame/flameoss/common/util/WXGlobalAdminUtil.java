package com.flame.flameoss.common.util;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Random;

/**
 * 微信中心系统工具类
 * 
 * @author DerekWu
 * 
 */
public class WXGlobalAdminUtil {
	
	// 生成接口校验的Token
	public static String getToken(String key, String appId, String timeStamp, String nonceStr) {
		String[] arr = new String[] { key, appId, timeStamp, nonceStr };// 生成token需要的参数
		Arrays.sort(arr);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			buf.append(arr[i]);
		}
		String temp = getSha1(buf.toString());
//		System.out.println(temp);
		return temp;
	}

	public static String getSha1(String str) {
		if (null == str || str.length() == 0) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char[] buf = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byTemp = md[i];
				buf[k++] = hexDigits[byTemp >>> 4 & 0xf];
				buf[k++] = hexDigits[byTemp & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}

	// 生成32位随机字符串
	public static String getRandomString() {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 32; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

}
