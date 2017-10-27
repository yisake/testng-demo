package com.nico.testcases;

import org.testng.annotations.Test;
import java.util.List;
import java.io.FileNotFoundException;

@Test(groups="test4")
public class TestCase2 {
	
	public static void main(String[] args) {
		List<?> a=demo();
		if (a==null) { 
			System.out.println("==null");//打印==null
		}		
		if (a.equals(null)) { //Exception in thread "main" java.lang.NullPointerException
			System.out.println("equals null");
		}
		System.out.println(a.get(0));
	}
	
	public static List<String> demo() {		
		return null;
	}
	
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
	}

	
}
