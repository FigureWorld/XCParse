package com.fanglv.XCParse.test.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fanglv.XCParse.data.DataInstanceInterface;
import com.fanglv.XCParse.parse.RecordParser;
import com.fanglv.XCParse.parse.impl.RecordParserImpl;

/**
 * 解析excel
 * 
 * @author muxiaocao
 *
 */
public class ExcelParse {

	private Logger logger = LoggerFactory.getLogger(ExcelParse.class);
	private Workbook wb;
	private Sheet sheet;
	private Row row;

	private String filePath;
	private List<String> sheetList = new ArrayList<String>();
	private Integer sheetNumb = 0;

	public List<String> getSheetList() {
		return sheetList;
	}

	public void setSheetList(List<String> sheetList) {
		this.sheetList = sheetList;
	}

	public ExcelParse() {

	}

	public ExcelParse(String filepath) {
		if (filepath == null) {
			return;
		}
		this.filePath = filepath;
		String ext = filepath.substring(filepath.lastIndexOf("."));
		try {
			InputStream is = new FileInputStream(filepath);
			if (".xls".equals(ext)) {
				wb = new HSSFWorkbook(is);
			} else if (".xlsx".equals(ext)) {
				wb = new XSSFWorkbook(is);
				for (int i = 0; i < wb.getNumberOfSheets(); i++) {
					sheetList.add(wb.getSheetName(i));
				}
			} else {
				wb = null;
			}
			sheetNumb = sheetList.size();
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
	}

	/**
	 * 读取Excel表格表头的内容
	 * 
	 * @param InputStream
	 * @return String 表头内容的数组
	 * @author muxiaocao
	 */
	public String[] readExcelTitle() throws Exception {
		if (wb == null) {
			throw new Exception("Workbook对象为空！");
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		System.out.println("colNum:" + colNum);
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			// title[i] = getStringCellValue(row.getCell((short) i));
			title[i] = row.getCell(i).getCellFormula();
		}
		return title;
	}

	/**
	 * 读取Excel数据内容，转化为json数据
	 * 
	 * @param InputStream
	 * @return Map 包含单元格数据内容的Map对象
	 * @author muxiaocao
	 */
	public Map<Integer, Map<Integer, Object>> readExcelContentToJson(
			Integer sheetId) throws Exception {
		if (wb == null) {
			throw new Exception("Workbook对象为空！");
		}
		Map<Integer, Map<Integer, Object>> content = new HashMap<Integer, Map<Integer, Object>>();

		sheet = wb.getSheetAt(sheetId);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			Map<Integer, Object> cellValue = new HashMap<Integer, Object>();
			while (j < colNum) {
				Object obj = getCellFormatValue(row.getCell(j));
				cellValue.put(j, obj);
				j++;
			}
			content.put(i, cellValue);
		}
		return content;
	}

	/**
	 * 读取Excel数据内容，转化为string数据，按指定分隔符
	 * 
	 * @return
	 * @throws Exception
	 * @Description
	 * @author muxiaocao
	 */
	public Map<Integer, String> readExcelContentToString(String splitString,
			Integer sheetID) throws Exception {
		if (wb == null) {
			throw new Exception("Workbook对象为空！");
		}
		Map<Integer, String> content = new HashMap<Integer, String>();

		sheet = wb.getSheetAt(sheetID);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		if (row == null) {
			return content;
		}
		int colNum = row.getPhysicalNumberOfCells();

		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			StringBuilder cellValue = new StringBuilder();
			while (j < colNum) {
				Object obj = getCellFormatValue(row.getCell(j));
				if (cellValue.length() != 0) {
					cellValue.append(splitString);
				}
				cellValue.append(obj);
				j++;
			}
			cellValue.append(splitString + sheetList.get(sheetID));
			content.put(i, cellValue.toString());
		}
		return content;
	}
	
	/**
	 * 读取Excel数据内容，转化为string数据，按指定分隔符
	 * 
	 * @return
	 * @throws Exception
	 * @Description
	 * @author muxiaocao
	 */
	public Map<Integer, String> readExcelContentToString(String splitString,
			String sheetName) throws Exception {
		int sheetIndex = wb.getSheetIndex(wb.getSheet(sheetName));
		return readExcelContentToString(splitString, sheetIndex,false);
	}
	private Map<Integer, String> readExcelContentToString(String splitString,
			int sheetID, boolean b) throws Exception {
		if (wb == null) {
			throw new Exception("Workbook对象为空！");
		}
		Map<Integer, String> content = new HashMap<Integer, String>();

		sheet = wb.getSheetAt(sheetID);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		if (row == null) {
			return content;
		}
		int colNum = row.getPhysicalNumberOfCells();

		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			StringBuilder cellValue = new StringBuilder();
			while (j < colNum) {
				Object obj = getCellFormatValue(row.getCell(j));
				if (cellValue.length() != 0) {
					cellValue.append(splitString);
				}
				cellValue.append(obj);
				j++;
			}
			content.put(i, cellValue.toString());
		}
		return content;
	}

	/**
	 * 
	 * 根据Cell类型设置数据
	 * 
	 * @param cell
	 * @return
	 * @author muxiaocao
	 */
	private Object getCellFormatValue(Cell cell) {
		Object cellvalue = "";
		DecimalFormat a = new DecimalFormat("##0");
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
			case Cell.CELL_TYPE_FORMULA: {

				// 判断当前的cell是否为Date
				if (DateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式
					// data格式是带时分秒的：2013-7-10 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();
					// data格式是不带带时分秒的：2013-7-10

					Date date = cell.getDateCellValue();
					cellvalue = date;
				} else {// 如果是纯数字

					// 取得当前Cell的数值
					if ((cell.getNumericCellValue() + "").contains(".")) {
						cellvalue = cell.getNumericCellValue();
					} else {
						cellvalue = a.format(cell.getNumericCellValue());
					}
					// cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			default:// 默认的Cell值
				cellvalue = cell.getStringCellValue();
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}

	/**
	 * 按行改变文件内容
	 * @param sheetID
	 * @param splitString
	 * @param callBack
	 * @throws Exception 
	 */
	public boolean changedContentByRow(String sheetName,String splitString,CallBack callBack) throws Exception {
		if (callBack == null) {
			return false;
		}
		Map<Integer, String> map = readExcelContentToString(splitString, sheetName);
		
		Set<Integer> keySet = map.keySet();
		Map<Integer, String> newMap = new HashMap<Integer, String>();
		for (Integer rowID : keySet) {
			String result = callBack.DataDeal(map.get(rowID), rowID);
			if (result == null) {
				continue;
			}
			newMap.put(rowID, result);
		}
		
		sheet = wb.getSheet(sheetName);
		
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			
			if (!newMap.containsKey(i)) {
				if (sheet.getRow(i) != null) {
					sheet.removeRow(sheet.getRow(i));
				}
				continue;
			}
			
			if (map.get(i).equals(newMap.get(i))) {
				continue;
			}
			
			Row row = sheet.getRow(i);
			String[] values = newMap.get(i).split(splitString);
			for (int j = 0; j < values.length; j++) {
				Cell cell = row.getCell(j);
				if (cell == null) {
					cell = row.createCell(j);
				}
				cell.setCellValue(values[j]);
			}
        }
		
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(filePath);
			wb.write(out);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 将指定excel文件读取成指定object
	 * 
	 * @param fileName
	 * @param clz
	 * @return
	 * @Description
	 * @author muxiaocao
	 * @throws Exception
	 */
	public static <E extends DataInstanceInterface> List<E> getObjectFormExcel(
			String[] fileNames, Class<E> clz, CallBack callBack)
			throws Exception {

		List<E> result = new ArrayList<>();
		RecordParser<E> parser = new RecordParserImpl<E>(clz);

		for (int k = 0; k < fileNames.length; k++) {
			String filepath = fileNames[k];
			ExcelParse excelReader = new ExcelParse(filepath);
			// 对读取Excel表格内容测试
			for (int i = 0; i < excelReader.sheetNumb; i++) {
				Map<Integer, String> map = excelReader
						.readExcelContentToString(",", i);

				System.out.println("获得Excel表格的内容:");
				for (int j = 1; j <= map.size(); j++) {
					// System.out.println(map.get(j));
					E item = null;
					if (callBack == null) {
						item = parser.parse(map.get(j), ",", null);
					} else {
						item = parser.parse(callBack.DataDeal(map.get(j), k),
								",", null);
					}
					if (item != null) {
						result.add(item);
					}
				}
			}
		}

		return result;
	}

	/**
	 * 数据自定义处理回调
	 * 
	 * @author muxiaocao
	 *
	 */
	public static abstract class CallBack {

		/**
		 * 自定义处理
		 * 
		 * @return
		 * @Description
		 * @author muxiaocao
		 */
		public abstract String DataDeal(String data, int fileIndex);
	}

	public static void main(String[] args) {
		try {
			String filepath = "E:\\muxiaocao\\资料\\淘房中国数据\\淘宝中国所有订单20160718.xlsx";
			ExcelParse excelReader = new ExcelParse(filepath);
			// 对读取Excel表格标题测试
			/*
			 * String[] title = excelReader.readExcelTitle();
			 * System.out.println("获得Excel表格的标题:"); for (String s : title) {
			 * System.out.print(s + " "); }
			 */

			List<String> sheets = excelReader.getSheetList();
			for (String string : sheets) {
				System.out.println(string);
			}
			// 对读取Excel表格内容测试
			// Map<Integer, Map<Integer,Object>> map =
			// excelReader.readExcelContentToJson();
			Map<Integer, String> map = excelReader.readExcelContentToString(
					",", 0);
			System.out.println("获得Excel表格的内容:");
			for (int i = 1; i <= map.size(); i++) {
				System.out.println(map.get(i));
			}
		} catch (FileNotFoundException e) {
			System.out.println("未找到指定路径的文件!");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
