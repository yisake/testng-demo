package com.nico.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author huangfucf
 *
 */
public class ExcelDateUtils {	
	
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		System.out.println(System.getProperty("user.dir"));
		LinkedList<LinkedHashMap<String,String>> list=getValues("./test.xlsx","userInfo",3,4);
		LinkedHashMap<String,String> map=list.get(3);
		for (Iterator<String> it =  map.keySet().iterator();it.hasNext();) {
			Object key = it.next();
			System.out.println( key+"="+ map.get(key));
		}
		
		LinkedList<LinkedHashMap<String,String>> list2=getValues("./test.xlsx","getJob",3,4);
		LinkedHashMap<String,String> map2=list2.get(3);
		for (Iterator<String> it =  map2.keySet().iterator();it.hasNext();) {
			Object key = it.next();
			System.out.println( key+"="+ map2.get(key));
		}
	}
	
	/**
	 * 根据行数获取excel的参数，key为excel第一行，value是行数之间的内容
	 * 例:取excel中第三行某个，用list.get(3).get(key)
	 * 注：最大列数根据第一行获取，遇到行数不一致
	 * @param filePath Excel路径
	 * @param sheetName sheet名字
	 * @param line1 行数 例：3
	 * @param line2 行数 例：5
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "resource", "serial" })
	public static LinkedList<LinkedHashMap<String, String>> getValues(String filePath,String sheetName,int line1,int line2) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		Workbook wb = null;
		try {
			 wb = new HSSFWorkbook(fis);
		} catch (Exception e) {
			 wb = new XSSFWorkbook(filePath);
		}
		System.out.println("总共sheet数量: "+wb.getNumberOfSheets());
		Sheet sheet=wb.getSheet(sheetName);		
		//最大行数，列数
		int intColumn=2;
		int maxColumn=sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("最大行数: "+sheet.getPhysicalNumberOfRows());
		System.out.println("最大列数: "+maxColumn);
		//行数减1
		line1=line1-1;
		line2=line2-1;
		LinkedList <LinkedHashMap<String,String>>list = new LinkedList <LinkedHashMap<String,String>>();
		//初始化list，让list的长度跟行数一致
		for (int i=0;i<=line1;i++) {
			list.add(new LinkedHashMap<String, String>() {{ 
		        put("",""); 
			}});
		}
		//读取参数
		while (line1<=line2) {
			Row row=sheet.getRow(line1);
			System.out.println("#######"+row.getPhysicalNumberOfCells());
			int Column;
			Column=intColumn;
			//初始化赋值
			LinkedHashMap<String,String> map = new LinkedHashMap<String, String>();
			while(Column<maxColumn) {
				//System.out.println(row.getCell(Column).toString());
				//System.out.println(sheet.getRow(0).getCell(Column).toString()+ row.getCell(Column).toString());
				map.put(sheet.getRow(0).getCell(Column).toString(), row.getCell(Column).toString());
				Column++;
			}
			list.add(map);
			line1++;
		}		
		return list;
	}
}
