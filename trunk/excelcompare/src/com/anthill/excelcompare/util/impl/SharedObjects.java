package com.anthill.excelcompare.util.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.anthill.excelcompare.util.api.ISharedObjects;

public final class SharedObjects implements ISharedObjects {
	
	private static SharedObjects instance = null;
	
	private String file1 = null;
	private String file2 = null;
	private String logFileName = null;
	
	public SharedObjects() throws IOException {
		readFromPropertyFile();
		makeLogFileName();
	}
	
	public static SharedObjects getInstance() throws IOException {
		instance = ( null != instance )? instance: new SharedObjects();
		return instance;
	}
	
	public String getFile1() {
		return file1;
	}
	
	public String getFile2() {
		return file2;
	}

	private void makeLogFileName() {
		DateFormat dateFormat = new SimpleDateFormat(FOLDER_NAME_FORMAT);
		Date date = new Date();
		logFileName = dateFormat.format(date);
	}
	
	private void readFromPropertyFile() throws IOException {
		final Properties properties = new Properties();
		properties.load(new FileInputStream(new File( PROPERTIES_FILE_NAME ) ) );
		file1 = properties.getProperty( PROP_NAME_FILE1 ) ;
		file2 = properties.getProperty( PROP_NAME_FILE2);
	}

	public String getLogFileName() {
		return logFileName;
	}
	
}
