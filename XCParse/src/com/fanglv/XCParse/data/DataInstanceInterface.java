package com.fanglv.XCParse.data;

import java.util.LinkedHashMap;

/**
 * 自定义数据对象接口，解析器解析对象必须实现该接口
 * @Description
 * @file_name DataInstanceInterface.java
 * @time 上午11:39:46
 * @author muxiaocao {https://github.com/MuXiaoCao/XCParse}
 * @个人主页 www.muxiaocao.cn
 */
public interface DataInstanceInterface {

	/**
	 * 自定义解析顺序，string表示属性名称，boolean表示是否可以为空
	 * @return
	 * @Description
	 * @author muxiaocao
	 */
	public LinkedHashMap<String, Boolean> getDataName();
	
	
}
