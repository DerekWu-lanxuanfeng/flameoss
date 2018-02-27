package com.flame.flameoss.common.util;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * xml工具类
 * 
 * @author miklchen
 *
 */
public class XMLUtil
{
	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * 
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map doXMLParse(String strxml) throws JDOMException, IOException
	{
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if (null == strxml || "".equals(strxml)) {
			return null;
		}

		Map m = new HashMap();

		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			}
			else {
				v = XMLUtil.getChildrenText(children);
			}

			m.put(k, v);
		}

		// 关闭流
		in.close();

		return m;
	}

	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * 
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Object doXMLParseRetrunObj(String strxml, Class clazz) throws JDOMException, IOException
	{
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if (null == strxml || "".equals(strxml)) {
			return null;
		}

		Object instance = null;
		try {
			// 创建对象;
			instance = clazz.newInstance();
		}
		catch (InstantiationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		catch (IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			}
			else {
				v = XMLUtil.getChildrenText(children);
			}

			Field field = null;
			try {

				field = clazz.getDeclaredField(k);
				field.setAccessible(true);// 设置可访问

				if (field.getType() == String.class) {
					field.set(instance, v);// 给对象成员变量赋值
				}
				else if (field.getType() == Integer.class) {
					field.set(instance, Integer.parseInt(v));// 给对象成员变量赋值
				}

			}
			catch (NoSuchFieldException e1) {
				e1.printStackTrace();
			}
			catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		// 关闭流
		in.close();

		return instance;
	}

	/**
	 * 获取子结点的xml
	 * 
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children)
	{
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(XMLUtil.getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}

		return sb.toString();
	}

	/**
	 * 获取xml编码字符集
	 * 
	 * @param strxml
	 * @return
	 * @throws IOException
	 * @throws JDOMException
	 */
	public static String getXMLEncoding(String strxml) throws JDOMException, IOException
	{
		InputStream in = HttpsClientUtil.String2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		in.close();
		return (String) doc.getProperty("encoding");
	}

	public static String getRequestXml(SortedMap<Object, Object> parameters)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			}
			else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	public static String getRequestXml(Object bean)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Class clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true); // 设置些属性是可以访问的
			String k = field.getName();
			String v = null;
			try {
				// 得到此属性的值
				Object object = field.get(bean);
				if (object == null) {
					continue;
				}
				else if (object instanceof String) {
					v = (String) object;
				}
				else {
					v = object + "";
				}

				if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
					sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
				}
				else {
					sb.append("<" + k + ">" + v + "</" + k + ">");
				}
			}
			catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		sb.append("</xml>");
		return sb.toString();
	}

	public static String setXML(String return_code, String return_msg)
	{
		return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
	}
}
