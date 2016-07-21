package com.fanglv.XCParse.test;

import java.util.List;

import com.fanglv.XCParse.data.DataInstanceInterface;
import com.fanglv.XCParse.test.excel.ExcelParse;
import com.fanglv.XCParse.test.excel.TfOrderInfoDto;

public class Test {
	
	public static void main(String[] args) {
		List<TfOrderInfoDto> result = ExcelParse.getObjectFormExcel(new String[]{"E:\\muxiaocao\\资料\\淘房中国数据\\淘宝中国所有订单20160718.xlsx"}, TfOrderInfoDto.class,null);
		for (TfOrderInfoDto tfOrderInfoDto : result) {
			System.out.println(tfOrderInfoDto);
		}
	}
	
}
