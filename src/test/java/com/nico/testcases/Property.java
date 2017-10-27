package com.nico.testcases;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;

public class Property {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		GetAllProperties("D:/Git/testng-demo/src/test/java/com/ppdai/jiechu/testcases/invest_ppdai_com/sanbiao/test.properties");
	}
	
	//读取Properties的全部信息
	public static void GetAllProperties(String filePath) throws IOException {
	    Properties pps = new Properties();
	    InputStream in = new BufferedInputStream(new FileInputStream(filePath));
	    pps.load(new InputStreamReader(in, "utf-8"));
	    Enumeration<?> en = pps.propertyNames(); //得到配置文件的名字
	    
	    while(en.hasMoreElements()) {
	        String strKey = (String) en.nextElement();
	        String strValue = pps.getProperty(strKey);
	        System.out.println(strKey + "=" + strValue);
	    }
	    
	}	
}
