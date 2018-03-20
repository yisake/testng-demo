package com.nico.utils;

import java.io.File;
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
 * <p>读取所有sheet页的provider 
 * <p>表格开始不能包含空的列  ◑︿◐
 * @author huangfucf
 *
 */
public class ExcelProvider2 implements Iterator<Object[]> {
	public LinkedHashMap<String, LinkedList<LinkedHashMap<String,String>>> sheetMap=new LinkedHashMap<String, LinkedList<LinkedHashMap<String,String>>>();//存放sheet和sheet数据的hashmap
	public LinkedList<String> sheetList=new LinkedList<String>();	//存放excel所有sheet名字的list
	private int numberindex;//当前执行的行数，可以控制是否返回excel前两行的参数名和参数的注释
	public int line2;//获取数据的行数范围
	private int maxRow;//最大行数
	private String flag;//标记执行的行数flag，single单行 multi多行full全部执行
	private String excel;//excel路径
	private Workbook  wb;

	//自定义main方法
	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws IOException {
		ExcelProvider2 t=new ExcelProvider2();
		while(t.hasNext()) {
			Object[] a = t.next();
			LinkedHashMap<String, LinkedHashMap<String,String>> b=(LinkedHashMap<String, LinkedHashMap<String, String>>) a[0];
			System.out.println(b.get("userInfo").get("name"));
			System.out.println(b.get("getJob").get("job"));
		}
		System.out.println("##loop over...");
	}
	
	public boolean hasNext() {
		/*System.out.println("hasNext...");*/
		if (this.flag.equals("full")) {
			if ( this.numberindex < this.maxRow) {
				return true;
			}
		} else if (this.flag.equals("multi")) {
			if ( this.numberindex <= this.line2) {
				return true;
			}
		} else if (this.flag.equals("single")) {
			if ( this.numberindex < this.line2) {
				return true;
			}
		}
		return false;
	}

	public Object[] next() {
		/*System.out.println(this.numberindex);*/		
		if (this.flag.equals("full")) {
			//获取多个sheet中的参数
			LinkedHashMap<String, LinkedHashMap<String,String>> result=new LinkedHashMap<String, LinkedHashMap<String,String>>();
			for (String sheetName:this.sheetList) {				
				result.put(sheetName, this.sheetMap.get(sheetName).get(this.numberindex));
			}
			//返回object对象
			Object[] objresult = new Object[2];
			objresult[0] =result;
			objresult[1] ="test";
			this.numberindex += 1;
			return objresult;
		} else if (this.flag.equals("single")) {
			//获取多个sheet中的参数
			LinkedHashMap<String, LinkedHashMap<String,String>> result=new LinkedHashMap<String, LinkedHashMap<String,String>>();
			for (String sheetName:this.sheetList) {				
				result.put(sheetName, this.sheetMap.get(sheetName).get(this.numberindex));
			}
			//返回object对象
			Object[] objresult = new Object[1];
			objresult[0] = result;
			this.numberindex += 1;
			return objresult;
		} else if (this.flag.equals("multi")) {
			//获取多个sheet中的参数
			LinkedHashMap<String, LinkedHashMap<String,String>> result=new LinkedHashMap<String, LinkedHashMap<String,String>>();
			for (String sheetName:this.sheetList) {				
				result.put(sheetName, this.sheetMap.get(sheetName).get(this.numberindex));
			}
			//返回object对象
			Object[] objresult = new Object[1];
			objresult[0] = result;
			this.numberindex += 1;
			return objresult;
		}
		return null;
	}

	public void remove() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * 获取excel表格中的所有数据
	 * @author huangfucf
	 * @throws IOException 
	 */
	public void getData(String excel) throws IOException {
		FileInputStream fis = new FileInputStream(excel);
		this.wb = null;
		try {
			 this.wb = new HSSFWorkbook(fis);
		} catch (Exception e) {
			 this.wb = new XSSFWorkbook(excel);
		}
		//获取第一页sheet最大行数
		this.maxRow=this.wb.getSheetAt(0).getPhysicalNumberOfRows();		
		//获取所有的sheet名字
		int sheetNum=this.wb.getNumberOfSheets();
		for (int i=0;i<sheetNum;i++) {
			this.sheetList.add(wb.getSheetName(i));
		}
		//获取所有的数据
		for (String sheet:sheetList) {
			sheetMap.put(sheet,this.getSheetDate(wb,sheet));
		}
/*		System.out.println(sheetMap.get("userInfo").get(0).get("order_id").toString());
		//System.out.println(sheetMap.get("userInfo").get(1).get("order_id").toString());
		System.out.println(sheetMap.get("userInfo").get(2).get("order_id").toString());
		System.out.println(sheetMap.get("userInfo").get(3).get("order_id").toString());
*//*		System.out.println(sheetMap.get("userInfo").get(3).get("amt_paybill").toString());
		System.out.println(sheetMap.get("getJob").get(3).get("job").toString());
		System.out.println(sheetMap.get("getJob").get(3).get("address").toString());
*/	}
	
	/**
	 * 获取Excel表格中的所有数据
	 * @param workbook
	 * @param sheetName
	 * @return
	 */
	public LinkedList<LinkedHashMap<String, String>> getSheetDate(Workbook workbook,String sheetName) {
		Sheet sheet=workbook.getSheet(sheetName);
		LinkedList <LinkedHashMap<String,String>>list = new LinkedList <LinkedHashMap<String,String>>();
		//获取最大行数，列数
		int maxRow=sheet.getPhysicalNumberOfRows();
		int maxColumn=sheet.getRow(0).getPhysicalNumberOfCells();
		/*System.out.println("最大行数: "+maxRow);
		System.out.println("最大列数: "+maxColumn);*/
		int intColumn=2;//设置读取json参数初始化列数
		//读取参数
		for (int i=0;i<maxRow;i++) {
			//初始化一个map用来存储每一行的hashmap数据
			LinkedHashMap<String,String> map = new LinkedHashMap<String, String>();
			Row row=sheet.getRow(i);
			int Column;
			Column=intColumn;
			maxColumn=row.getPhysicalNumberOfCells();
			while(Column<maxColumn) {
				String key=sheet.getRow(0).getCell(Column).toString();
				String value=row.getCell(Column).toString();
/*				System.out.println("获取sheet key:"+key);
				System.out.println("获取sheet value:"+value);*/
				map.put(key, value);
				Column++;
			}
			list.add(map);
		}
		return list;
	}

	public ExcelProvider2() throws IOException {
		this.flag="full";
		this.numberindex=2;
		this.excel=System.getProperty("user.dir")+File.separator+"Test.xlsx";
		System.out.println(this.excel);
		this.getData(this.excel);
	}	
	
	/**
	 * 获取所有sheet内的内容方法 ( ´◔ ‸◔`) <br> 全部执行
	 * @author huangfucf
	 * @param aimob
	 * @throws IOException 
	 */
	public ExcelProvider2(Object aimob) throws IOException {
		this.flag="full";
		this.numberindex=2;
		this.excel="D:\\payplat_Auto_test\\Test.xlsx";
		System.out.println(this.excel);
		this.getData(this.excel);		
	}

	/**
	 * 单行执行用例  (o◕∀◕)ﾉ
	 * @param aimob
	 * @param line1
	 * @param line2
	 * @throws IOException
	 */
	public ExcelProvider2(Object aimob,int line1) throws IOException {
		this.flag="single";
		this.numberindex=line1-1;
		this.line2=line1;
		this.excel="D:\\payplat_Auto_test\\Test.xlsx";
		System.out.println(this.excel);
		this.getData(this.excel);
	}
	
	/**
	 * 执行从line1到line2之间的用例  (｀◕‸◕´+)
	 * @param aimob
	 * @param line1
	 * @param line2
	 * @throws IOException
	 */
	public ExcelProvider2(Object aimob,int line1,int line2) throws IOException {
		this.flag="multi";
		this.numberindex=line1-1;
		this.line2=line2-1;
		this.excel="D:\\payplat_Auto_test\\Test.xlsx";
		System.out.println(this.excel);
		this.getData(this.excel);
	}
}