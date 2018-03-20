package com.nico.testcases;

import java.lang.reflect.Method;
import org.apache.log4j.Logger;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.nico.utils.FileUtil;
import com.nico.utils.LoggerUtil;
import com.nico.utils.ToLoggerPrintStream;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;

public class RestBase extends AbstractTestNGSpringContextTests {
	public String seprator = System.getProperty("file.separator");
	public String userDir =  System.getProperty("user.dir")+seprator+"target"+seprator;
	public String logDir =  userDir+"testing-logs"+seprator;
	public RestClient restClient;
	public Logger loger;
	public String logerName;
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		FileUtil.createPath(logDir);
	}
	
	
	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		
	}
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass () {
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass () {
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod (Method method) throws Exception {
		this.getLogger(method);
		this.loger.info("####Before method");
		this.restClient=new RestClient(this.loger);
		this.restClient.logger.info("################################");
	}
	
	@AfterMethod (alwaysRun = true)
	public void afterMethod () {
		
	}
	
	//获取当前用例的logger
	public void getLogger ( Method method ) throws Exception {
		String packageName=this.getClass().getPackage().getName();
		String fullName = packageName+"."+this.getClass().getSimpleName()+"_"+method.getName();
		this.logerName=fullName;
		this.loger= LoggerUtil.getLogger(logDir+fullName+".txt", fullName);	
		this.loger.info("####Start to test: "+fullName);
	}
	
	//获取rest assured的打印输出
	public RestAssuredConfig getRestAssuredLogConfig ( ) {
		return RestAssured.config().logConfig(new LogConfig().defaultStream(new ToLoggerPrintStream(Logger.getLogger(this.logerName)).getPrintStream()));
	}
	
	
}
