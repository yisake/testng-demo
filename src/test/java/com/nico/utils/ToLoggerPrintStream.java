package com.nico.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import org.apache.log4j.Logger;

public class ToLoggerPrintStream {
	/** Logger for this class */
	private Logger myLog;
	private PrintStream myPrintStream;
	   
	/**
	 * Constructor
	 *
	 * @param aLogger
	 */
	public ToLoggerPrintStream( Logger aLogger )
	{
	    super();
	    myLog = aLogger;
	}
	    
	/**
	 * @return printStream
	 */
	public PrintStream getPrintStream()
	{
	    if ( myPrintStream == null ) {
	        OutputStream output = new OutputStream() {
	        	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            
	            @Override
	            public void write(int b) throws IOException {
	            	baos.write(b);
	            }
	            
	            @Override
	            public void flush() throws UnsupportedEncodingException
	            {	
	                myLog.info(baos.toString());
	                //System.out.println("####StringBuilder:"+baos.toString());
	                baos = new ByteArrayOutputStream();	                
	            } 
	        };
	        myPrintStream = new PrintStream(output, true);// true: autoflush must be set!
	    }
	    return myPrintStream;
	}
}