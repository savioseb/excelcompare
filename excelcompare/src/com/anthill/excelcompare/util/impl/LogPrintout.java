package com.anthill.excelcompare.util.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.anthill.excelcompare.util.api.ILogPrintout;

public final class LogPrintout implements ILogPrintout {
	
	private static LogPrintout instance = null;
	private final FileOutputStream fileOutputStream;
	
	public LogPrintout() throws IOException {
		fileOutputStream = new FileOutputStream( new File( SharedObjects.getInstance().getLogFileName() ) );
	}
	
	public static LogPrintout getInstance() throws IOException {
		instance = ( null != instance )? instance: new LogPrintout();
		return instance;
	}
	
	public void print( final String print ) throws IOException {
		fileOutputStream.write( print.getBytes() );
	}
	
	public void printTimeElapsed( final long startTime, final String subject ) throws IOException {
		long endTime = System.currentTimeMillis();
		double totalTime = (double)( endTime - startTime ) / CONVERT_TO_SECONDS;
		print( "\nTime Taken " + subject + ": " + totalTime + " secs." );
	}
	
	@Override
	public void finalize() throws IOException {
		print( "\nClosing Log" );
		fileOutputStream.close();
	}
	
}