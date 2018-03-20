package com.nico.demo;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nico.utils.ExcelProvider2;

/**
 * <h1>Demo for provider</h1>
 * @author huangfucf (◕ܫ◕) 
 * 
 */
public class ExcelProvider2Demo {
	
	@Test(dataProvider = "provider1")
	public void test1(LinkedHashMap<String, LinkedHashMap<String,String>> dt,String test) {
		System.out.println("Get userInfo sheet name:"+dt.get("userInfo").get("name"));
		System.out.println("Get getJob sheet name:"+dt.get("getJob").get("job"));
		System.out.println("Get test sheet name:"+dt.get("test").get("job"));
		System.out.println("Get the string:"+test);
	}
	
	@DataProvider(name = "provider1")
	public Iterator<Object[]> provider1() throws IOException {
		return new ExcelProvider2(this);
	}
	
	@DataProvider(name = "provider2")
	public Iterator<Object[]> provider2() throws IOException {
		return new ExcelProvider2(this,4,7);
	}
	
	@DataProvider(name = "provider3")
	public Iterator<Object[]> provider3() throws IOException {
		return new ExcelProvider2(this,4);
	}
}
