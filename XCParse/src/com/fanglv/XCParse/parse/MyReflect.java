package com.fanglv.XCParse.parse;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * 反射工具类
 * @Description
 * @file_name MyReflect.java
 * @time 上午11:25:13
 * @author muxiaocao {https://github.com/MuXiaoCao/XCParse}
 * @个人主页 www.muxiaocao.cn
 */
public class MyReflect {

	@SuppressWarnings("deprecation")
	public static Object myPropertyEditor(Field field,String context) throws Exception{
		context = context.trim();
		String type = field.getType().getName();
		if (type.equals(String.class.getName())) {
			return new String(context);
		}else if (type.equals(Integer.class.getName())) {
			if (context.contains(".")) {
				return Integer.parseInt(context.substring(0, context.indexOf(".")));
			}
			return Integer.parseInt(context);
		}else if (type.equals(int.class.getName())) {
			return new Integer(context);
		}else if (type.equals(Long.class.getName())) {
			return Long.parseLong(context);
		}else if (type.equals(Date.class.getName())) {
			return new Date(context);
		}else if (type.equals(float.class.getName())) {
			return new Float(context);
		}else if (type.equals(Boolean.class.getName())) {
			return context.equals("1");
		}else if (type.equals(Double.class.getName())) {
			return Double.parseDouble(context);
		}else if (type.equals("double")) {
			return Double.parseDouble(context);
		}else {
			return context;
		}
		
	}

	// 把一个字符串的第一个字母大写、效率是最高的方法
	public static String getSetMethod(String fildeName) throws Exception {
		byte[] items = fildeName.getBytes();
		if (items[0] < 'a') {
			return "set" + fildeName;
		}
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return "set" + new String(items);
	}
}
