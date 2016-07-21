
# XCParse
小草解析开源框架，作者：[木小草](http:www.muxiaocao.cn)

## 使用场景：
	解析从文本文件中的读取的内容，转化为javaBean对象。
	
	** 例如 **：需要将字符串“12，xiaocao”解析成有name和age属性的java对象。

示例代码：
	
```
	String parseString = "25,muxiaocao";

	RecordParserImpl<Bean> parser = new RecordParserImpl<Bean>(Bean.class);

	Bean person = parser.parse(parseString, ",",null);
```
如果希望将muxiaocao改成xiaocao：
```
	String parseString = "25,muxiaocao";
	RecordParserImpl<Bean> parser = new RecordParserImpl<Bean>(Bean.class);
	Bean person = parser.parse(parseString, ",",new CallBack() {

			@Override
			public String DataDeal(int itemId, String data) {
				if (data.equals("muxiaocao")) {
					return "xiaocao";
				}else {
					return data;
				}
			}
					
	});
```
