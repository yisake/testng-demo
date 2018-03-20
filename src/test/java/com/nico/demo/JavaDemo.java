package com.nico.demo;

public class JavaDemo {
	public int id;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		JavaDemo demo1=new JavaDemo(1);
		JavaDemo demo2=new JavaDemo(2);
		while (true) {
			demo1.print();
		}
	}
	
    public JavaDemo (int id) {
    	this.id=id;
    }
    
	public void print() throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("This is:print test");
	}

	public void printId() throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("This class id is:");
	}
	
}
