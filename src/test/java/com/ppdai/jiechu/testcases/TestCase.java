package com.ppdai.jiechu.testcases;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.HeaderConfig;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class TestCase {

	@Test(groups="test1")
	public void test1 () {
		System.out.println(Thread.currentThread().getId());
		System.out.println("##test1");
	}
	
	@Test(groups="test2")
	public void test2 () {
		System.out.println(Thread.currentThread().getId());
		System.out.println("##test2");
	}

	@Test(groups="test3")
	public void test3 () throws FileNotFoundException {
		System.out.println(Thread.currentThread().getId());
		System.out.println("##test3");
		PrintStream fileOutPutStream = new PrintStream(new File("./restassred.txt"));
		given().
			proxy("localhost", 8888).
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
	}
	
	
}
