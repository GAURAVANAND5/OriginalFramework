package com.crm.GenericLibaray;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class is used to read the data from Property file
 * @author GAURAV
 *
 */
public class FileUtility {
	/**
	 * This method is used to read the data from Property file based on key which we pass as an argument
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String getFileProperty(String key) throws IOException
	{
	
	FileInputStream fis=new FileInputStream(IpathConstants.FilePath);
	Properties p=new Properties();
	p.load(fis);
	String value = p.getProperty(key);
	return value;
	}
	

}
