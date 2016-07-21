package com.fanglv.XCParse.util;

/**
 * ʱ����Թ�����
 * @Description
 * @file_name TestTimeUtil.java
 * @time ����11:39:13
 * @author muxiaocao {https://github.com/MuXiaoCao/XCParse}
 * @������ҳ www.muxiaocao.cn
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
