package com.fanglv.XCParse.otherparse;

import com.alibaba.fastjson.JSON;
import com.fanglv.XCParse.parse.RecordParser.CallBack;
import com.fanglv.XCParse.parse.impl.RecordParserImpl;
import com.fanglv.XCParse.util.TestTimeUtil;

/**
 * fastjson解析与xcparse解析对比
 * @Description
 * @file_name FastJSONTest.java
 * @time 上午11:45:26
 * @author muxiaocao {https://github.com/MuXiaoCao/XCParse}
 * @个人主页 www.muxiaocao.cn
 */
public class FastJSONTest {
	
	public static void main(String[] args) {
		
		TestTimeUtil test1 = new TestTimeUtil() {
			
			@Override
			public void myCode() {
				String json = "{\"age\":25,\"name\":\"muxiaocao\"}";
				JSON.parseObject(json, Bean.class);
			}
		};
		
		TestTimeUtil test2 = new TestTimeUtil() {
			
			@Override
			public void myCode() {
				String parseString = "25,muxiaocao";
				RecordParserImpl<Bean> parser = new RecordParserImpl<Bean>(Bean.class);
				parser.parse(parseString, ",",new CallBack() {

					@Override
					public String DataDeal(int itemId, String data) {
						if (data.equals("muxiaocao")) {
							return "xiaocao";
						}else {
							return data;
						}
					}
					
				});
			}
		};
		
		System.out.println(test1.getTime(1000) - test2.getTime(1000));
		
		//String json = "{\"address\":{\"city\":\"taian\",\"province\":\"shandong\"},\"age\":25,\"name\":\"muxiaocao\"}";
		
		/*json = JSON.toJSONString(obj);
		System.out.println(json);*/
		
		/*Bean bean = new Bean();
		bean.setAddress(new InBean("shandong", "taian"));
		bean.setAge(25);
		bean.setName("muxiaocao");
		System.out.println(JSON.toJSONString(bean));*/
	}
	
}
