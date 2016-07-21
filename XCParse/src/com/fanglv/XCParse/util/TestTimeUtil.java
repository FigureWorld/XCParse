package com.fanglv.XCParse.util;

/**
 * 时间测试工具类
 * @Description
 * @file_name TestTimeUtil.java
 * @time 上午11:39:13
 * @author muxiaocao {https://github.com/MuXiaoCao/XCParse}
 * @个人主页 www.muxiaocao.cn
 */
public abstract class TestTimeUtil {
	
	public abstract void myCode();
	
	public long getTime(long number) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < number; i++) {
			myCode();
		}
		long end = System.currentTimeMillis();
		return end - start;
	}
}
