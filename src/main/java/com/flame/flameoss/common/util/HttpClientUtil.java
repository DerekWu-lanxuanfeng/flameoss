package com.flame.flameoss.common.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

public class HttpClientUtil
{
	public final static int CONNECT_TIMEOUT = 15000;
	
	public static String loadJSON(String url)
	{
		StringBuilder json = new StringBuilder();
		try {
			url = url.replace(" ", "").trim();
			URL oracle = new URL(url);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "utf-8"));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		}
		catch (MalformedURLException e) {
		}
		catch (IOException e) {
		}
		return json.toString();
	}

	// get请求
	public static String loadJson(String targetURL)
	{
		targetURL = targetURL.trim().replace(" ", "");
		GetMethod filePost = new GetMethod(targetURL);
		String json = null;
		HttpClient client = null;
		try {
			client = new HttpClient();
			HttpConnectionParams httpConnectionParams = client.getHttpConnectionManager().getParams();
			httpConnectionParams.setConnectionTimeout(CONNECT_TIMEOUT);// 设置链接服务器链接超时
			// httpConnectionParams.setSoTimeout(4000);//这定义了Socket读数据的超时时间，即从服务器获取响应数据需要等待的时间，此处设置为4秒。
			int status = client.executeMethod(filePost);
			if (status == HttpStatus.SC_OK) {
				json = filePost.getResponseBodyAsString();
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			filePost.releaseConnection();// 该方法并未关闭链接，只是将链接返回给connection manager
			((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown(); // 真正关闭链接
		}
		return json;
	}

	// post请求
	public static String loadJsonPost(String targetURL, Map<String, Object> paramMap)
	{
		PostMethod filePost = new PostMethod(targetURL);
		String json = null;
		HttpClient client = null;
		try {
			client = new HttpClient();
			filePost.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");// 解决中文乱码问题
			/*
			 * 下面是给post请求传参
			 */
			NameValuePair[] param = new NameValuePair[paramMap.size()];
			Set<String> keySet = paramMap.keySet();
			int i = 0;
			for (String key : keySet) {
				param[i] = new NameValuePair(key, paramMap.get(key).toString());
				i++;
			}
			filePost.setRequestBody(param);

			HttpConnectionParams httpConnectionParams = client.getHttpConnectionManager().getParams();
			httpConnectionParams.setConnectionTimeout(CONNECT_TIMEOUT);// 设置链接服务器链接超时
			// httpConnectionParams.setSoTimeout(4000);//这定义了Socket读数据的超时时间，即从服务器获取响应数据需要等待的时间，此处设置为4秒。
			int status = client.executeMethod(filePost);
			if (status == HttpStatus.SC_OK) {
				json = filePost.getResponseBodyAsString();
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			filePost.releaseConnection();// 该方法并未关闭链接，只是将链接返回给connection manager
			((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown(); // 真正关闭链接
		}
		return json;
	}

	// 微信请求
	public static String loadJsonHTTS(String targetURL, String paraXml)
	{
		PostMethod filePost = new PostMethod(targetURL);
		String json = null;
		HttpClient client = null;
		try {
			client = new HttpClient();
			filePost.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");// 解决中文乱码问题
			// filePost.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
			// "UTF-8");//解决中文乱码问题
			/*
			 * 下面是给post请求传参
			 */
			// filePost.setRequestBody(paraXml);

			HttpConnectionParams httpConnectionParams = client.getHttpConnectionManager().getParams();
			httpConnectionParams.setConnectionTimeout(CONNECT_TIMEOUT);// 设置链接服务器链接超时
			// httpConnectionParams.setSoTimeout(4000);//这定义了Socket读数据的超时时间，即从服务器获取响应数据需要等待的时间，此处设置为4秒。
			int status = client.executeMethod(filePost);
			if (status == HttpStatus.SC_OK) {
				json = filePost.getResponseBodyAsString();
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			filePost.releaseConnection();// 该方法并未关闭链接，只是将链接返回给connection manager
			((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown(); // 真正关闭链接
		}
		return json;
	}
}
