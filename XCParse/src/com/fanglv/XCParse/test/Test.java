package com.fanglv.XCParse.test;

import java.util.List;

import com.fanglv.XCParse.data.DataInstanceInterface;
import com.fanglv.XCParse.test.excel.ExcelParse;
import com.fanglv.XCParse.test.excel.TfOrderInfoDto;

public class Test {
	
	public static void main(String[] args) {
		List<TfOrderInfoDto> result = ExcelParse.getObjectFormExcel(new String[]{"E:\\muxiaocao\\����\\�Է��й�����\\�Ա��й����ж���20160718.xlsx"}, TfOrderInfoDto.class,null);
		for (TfOrderInfoDto tfOrderInfoDto : result) {
			System.out.println(tfOrderInfoDto);
		}
	}
	
}
