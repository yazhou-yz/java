package com.wisdom.commons;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public class ResourcesUtil implements Serializable {

	private static final long serialVersionUID = 13125360682074078L;
	private static final String FILENAME = "conf.messages";
	/**
	 * 系统语言环境，默认为中文zh
	 */
	public static final String LANGUAGE = "zh";
	/**
	 * 系统国家环境，默认为中国CN
	 */
	public static final String COUNTRY = "CN";

	private static Locale getLocale() {
		Locale locale = new Locale(LANGUAGE, COUNTRY);
		return locale;
	}

	/**
	 * 根据语言、国家、资源文件名和key名字获取资源文件值
	 * 
	 * @param baseName 资源文件名
	 * @param section  key名字
	 * @return 值
	 */
	private static String getProperties(String baseName, String section) {
		try {
			ResourceBundle rb = ResourceBundle.getBundle(baseName, getLocale());
			return (String) rb.getObject(section);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过key从资源文件读取内容
	 * 
	 * @param fileName 资源文件名
	 * @param key      索引
	 * @return 索引对应的内容
	 */
	public static String getValue(String fileName, String key) {
		return getProperties(fileName, key);
	}

	/**
	 * 获取默认
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return getProperties(FILENAME, key);
	}

	public static List<String> getKeyList(String baseName) {
		ResourceBundle rb = ResourceBundle.getBundle(baseName, getLocale());
		List<String> reslist = new ArrayList<String>();
		Set<String> keyset = rb.keySet();
		for (Iterator<String> it = keyset.iterator(); it.hasNext();) {
			String lkey = (String) it.next();
			reslist.add(lkey);
		}
		return reslist;
	}

	/**
	 * 通过key从资源文件读取内容，并格式化
	 * 
	 * @param fileName 资源文件名
	 * @param key      索引
	 * @param objs     格式化参数
	 * @return 格式化后的内容
	 */
	public static String getValue(String fileName, String key, Object[] objs) {
		String pattern = getValue(fileName, key);
		return MessageFormat.format(pattern, objs);
	}
}
