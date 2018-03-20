package com.nico.testcases;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.Map;
//import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;
import com.nico.utils.ToLoggerPrintStream;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

/**
 * 出金管理站页面url
 * @author huangfucf
 *
 */
public class RestClient {
	public Map<String, String> cookie=this.登录();
	public 支付管理 支付管理=new 支付管理();
	public 备付金系统 备付金系统=new 备付金系统();
	public Logger logger;
	
	public RestClient() {
		
	}
	
	public RestClient(Logger logger) {
		this.logger=logger;
		this.logger.info("初始化RestClient...");
	}
	
	//获取rest assured的打印输出
	public RestAssuredConfig getRestAssuredLogConfig ( ) {
		return RestAssured.config().logConfig(new LogConfig().defaultStream(new ToLoggerPrintStream(this.logger).getPrintStream()));
	}
	
	public Map<String, String> 登录() {
		RestAssured.baseURI="http://192.168.110.22:8080/YINTONG_MNG_exc";
		Response res1=given().
				config(this.getRestAssuredLogConfig()).
		        param("username", "huangfucf").
		        formParam("password", "test1234").
		    when().
		    	post("/login.action").andReturn();
		return res1.getCookies();
	}
	
/*	@SuppressWarnings("null")
	public Response 支付管理自动打包(String string) {
		if (string.contains("::")) {
			String[] array=string.split("::");
			NameValuePair[] pnos = null;
			for (int i=0;i<array.length;i++) {
				pnos[i]=new NameValuePair("pnos",array[i]);
			}
			return given().
					config(this.getRestAssuredLogConfig()).
					cookies(this.cookie).
					formParams("","",(Object)pnos).
					log().all().
			    when().
			    	post(this.支付管理.自动打包).andReturn();
		} else {
			Response res= given().
					config(this.getRestAssuredLogConfig()).
					cookies(this.cookie).
					formParam("pnos",string).
				    formParam("payBill.dt_start", "2017-12-07 00:00:00").
				    formParam("payBill.dt_end", "2017-12-07 20:05:10").
				    formParam("isSelectAll", "0").
				    formParam("queryList", "1").
				    formParam("count", "20").
				    log().all().
			    when().
			    	post(this.支付管理.自动打包).andReturn();
			res.then().log().all();
			return res;
		}
		
	}*/
	
	public Response 支付管理提交审核(String string) {
		Response res= given().
				cookies(this.cookie).
				param("pno", string).
		    when().
		    	post(this.支付管理.提交审核).andReturn();
		res.then().log().all();
		return res;
	}
	
	public Response 支付管理审核通过() {
		Response res= given().
				cookies(this.cookie).
		    when().
		    	post(this.支付管理.审核通过).andReturn();
		res.then().log().all();
		return res;		
	}
	
	public Response 支付管理提现批次下载() {
		Response res= given().
				cookies(this.cookie).
		    when().
		    	post(this.支付管理.提现批次下载).andReturn();
		res.then().log().all();
		return res;		
	}
	
	public Response 支付管理确认结果() {
		Response res= given().
				cookies(this.cookie).
		    when().
		    	post(this.支付管理.确认结果).andReturn();
		res.then().log().all();
		return res;
	}
	
	public  class 支付管理 {
		//支付管理 Table页面
		//支付管理 提现批次管理 自动打包
		public  String 自动打包="/pay/autoBuilderPack.action";
		public  String 自动打包成功返回信息="提现单自动打包触发成功，请稍后查询批次信息！";

		//支付管理 汇总批次管理 提交审核
		public  String 提交审核="/pay/submitCashSummaryPno.action";
		public  String 提交审核成功返回信息="汇总批次号[201712063299987]提交审核成功。";
		//支付管理 汇总批次管理 审核通过
		public  String 审核通过="/pay/agreeCashSummaryPno.action";
		/*{"result":"true","msg":"汇总批次[201712063299987]复核通过"}*/
		
		//支付管理 提现批次下载 提现批次下载
		public  String 提现批次下载="/pay/submitCashOutPno.action";
		
		//支付管理 提现结果确认
		public  String 确认结果="";
	}
	
	public class 备付金系统 {
		
	}
}
