package com.ppdai.jiechu.testcases;

import org.testng.annotations.Test;

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
	public void test3 () {
		System.out.println(Thread.currentThread().getId());
		System.out.println("##test3");
	}
	
	
}
