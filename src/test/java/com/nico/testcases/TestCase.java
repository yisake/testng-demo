package com.nico.testcases;

import java.io.IOException;
import java.util.Iterator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nico.utils.ExcelProvider;

@Test(groups="test4")
public class TestCase {
	String result=null;

	@Test(groups="test1")
	public void test1 () {
		System.out.println(Thread.currentThread().getId());
		System.out.println("##test1");
		result="abc";
	}
	
	@Test(groups="test2",dependsOnMethods="test1")
	public void test2 () {
		System.out.println(Thread.currentThread().getId());
		System.out.println("##test2");
		System.out.println(this.result);
	}

	@Test(groups="test3")
	public void test3 () {
		System.out.println(Thread.currentThread().getId());
		System.out.println("##test3");
		/*
		PrintStream fileOutPutStream = new PrintStream(new File("./restassred.txt"));
		given().
			//proxy("localhost", 8888).
			header("header1", "value1").
			header("header1", "value2").
			config(RestAssured.config().logConfig(new LogConfig().defaultStream(fileOutPutStream))).
			config(RestAssured.config().headerConfig(new HeaderConfig().overwriteHeadersWithName("header1"))).
			log().all().
		when().
			get("http://127.0.0.1:5000/hello").
		then().
			log().all().
			assertThat().statusCode(200).
			assertThat();
		*/
	}	
	
	@DataProvider(name = "userAdd")
	public Iterator<Object[]> data4userAdd() throws IOException {
		//excel名称跟类名一致，sheet名称跟方法名一致
		//执行指定数据时，使用该方法
		//return new ExcelProvider(this, "userAdd",runRow);
		return new ExcelProvider(this, "create");
	}
}
