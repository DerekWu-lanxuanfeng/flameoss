package com.flame.flameoss.common.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuLiang
 *
 */
public class HttpUtil
{
	/** 获取客户端ip */
	public static String getClientIpAddress(HttpServletRequest request)
	{
		String clientIp = request.getHeader("x-forwarded-for");
		if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("Proxy-Client-IP");
		}
		if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("WL-Proxy-Client-IP");
		}
		if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getRemoteAddr();
		}

		if ("0:0:0:0:0:0:0:1".equals(clientIp)) {
			clientIp = "127.0.0.1";
		}

		// 这里有可能获取到2个IP
		if (StringUtils.length(clientIp) > 0) {
			clientIp = StringUtils.trim(clientIp);
			String[] items = StringUtils.split(clientIp, ',');
			if (items.length > 0) {
				clientIp = items[0];
			}
		}
		return clientIp;
	}

}
