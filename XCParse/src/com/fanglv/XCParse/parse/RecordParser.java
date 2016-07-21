package com.fanglv.XCParse.parse;

import com.fanglv.XCParse.data.DataInstanceInterface;

/**
 * 文本解析器处理抽象类
 * @Description
 * @file_name RecordParser.java
 * @time 上午11:38:34
 * @author muxiaocao {https://github.com/MuXiaoCao/XCParse}
 * @个人主页 www.muxiaocao.cn
 */
public interface RecordParser<E extends DataInstanceInterface> {
	/**
	 * 初始化文本解析器
	 * @param dataName
	 * 			字段名称
	 */
	public void init(Class<E> cla,Class<DataInstanceInterface> interf);
	/**
	 * 解析text数据
	 * @param value
	 * 		文本数据
	 * @param
	 * 		分隔符
	 */
	public E parse(String value,String split,CallBack callBack);
	
	/**
	 * 获取对应字段的value值
	 * @param dataName
	 * @return
	 */
	public String getDataValue(String dataName);
	
	/**
	 * 自定义处理数据
	 * @author muxiaocao
	 *
	 */
	public static abstract class CallBack {
		/**
		 * 对于指定顺序的数据进行处理
		 * @param itemId
		 * @param data
		 * @return
		 * @Description
		 * @author muxiaocao
		 */
		public abstract String DataDeal(int itemId,String data);
	}

}
