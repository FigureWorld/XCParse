package com.fanglv.XCParse.parse.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.fanglv.XCParse.data.DataInstanceInterface;
import com.fanglv.XCParse.parse.MyReflect;
import com.fanglv.XCParse.parse.RecordParser;


public class RecordParserImpl<E extends DataInstanceInterface> implements RecordParser<E> {
	/**
	 * 数据对象接口
	 */
	private E dataInstance;
	private Class<E> clazz;
	/**
	 * 数据元存放列表
	 */
	private List<Data> dataInfo;
	/**
	 * 原始数据
	 */
	private String line;

	public RecordParserImpl(Class<E> clz) {
		init(clz, DataInstanceInterface.class);
	}

	/**
	 * 解析器初始化 1. 判断是否实现数据接口 2. 初始化数据元列表
	 */
	public void init(Class<E> cla, Class<DataInstanceInterface> xface) {
		if (!xface.isAssignableFrom(cla)) {
			throw new RuntimeException(cla.getName() + " not "
					+ xface.getName());
		}
		try {
			if (dataInstance == null) {
				dataInstance = (E) cla.newInstance();
			}
			clazz = cla;
			HashMap<String, Boolean> dataNames = dataInstance.getDataName();
			dataInfo = new ArrayList<RecordParserImpl<E>.Data>();
			Set<String> keySet = dataNames.keySet();
			Field[] fields = clazz.getDeclaredFields();
			for (String string : keySet) {
				Data data = new Data();
				for (int i = 0; i < fields.length; i++) {
					if (fields[i].getName().equals(string)) {
						data.setDataName(fields[i]);
						break;
					}
				}
				data.setType(dataNames.get(string));
				dataInfo.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 根据原始数据，填写数据元列表内容
	 */
	public E parse(String value, String split,CallBack callBack) {
		if (value == null) {
			return null;
		}
		line = value;
		try {
			dataInstance = clazz.newInstance();
			String[] dataValues = StringUtils.splitPreserveAllTokens(line,
					split);
			for (int i = 0; i < dataValues.length && i < dataInfo.size(); i++) {
				if (!dataInfo.get(i).isType() && dataValues[i] == null) {
					return null;
				}
				Field field = dataInfo.get(i).getDataName();
				Method method = clazz.getMethod(MyReflect.getSetMethod(field.getName()),
						new Class[] {field.getType()});
				field.setAccessible(true);
				if (callBack != null) {
					method.invoke(dataInstance, MyReflect.myPropertyEditor(field, callBack.DataDeal(i, dataValues[i])));
				}else {
					method.invoke(dataInstance, MyReflect.myPropertyEditor(field, dataValues[i]));
				}
			}

		} catch (Exception e) {
			new Throwable("分割效果与自定义类型不符");
			e.printStackTrace();
		}
		return dataInstance;
	}


	/**
	 * 根据指定的字段，返回其内容
	 */
	public String getDataValue(String dataName) {
		return dataName;
	}

	/**
	 * 数据元
	 * 
	 * @author muxiaocao
	 * 
	 */
	public class Data {
		/**
		 * 字段名
		 */
		Field dataName;
		/**
		 * 是否可以为空 false 表示不可以为空 true 表示可以为空
		 */
		boolean type;


		public Field getDataName() {
			return dataName;
		}

		public void setDataName(Field dataName) {
			this.dataName = dataName;
		}

		public boolean isType() {
			return type;
		}

		public void setType(boolean type) {
			this.type = type;
		}

		@Override
		public int hashCode() {
			return 0;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Data other = (Data) obj;

			if (dataName == null) {
				if (other.dataName != null)
					return false;
			} else if (!dataName.getName().equals(other.dataName.getName()))
				return false;
			return true;
		}

	}

}
