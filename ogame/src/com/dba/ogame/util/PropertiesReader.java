package com.dba.ogame.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

public class PropertiesReader {

	Properties prp = new Properties();

	public PropertiesReader(String filename) throws IOException {
		InputStream is = new FileInputStream(filename);
		prp.load(is);

	}

	public String getProperty(String key) {

		return prp.getProperty(key);

	}

	public String searchKeyFromValue(String value) {

		Iterator iter = prp.keySet().iterator();
		while (iter.hasNext()) {
			String key=(String)iter.next();
			String v=getProperty(key);
			if(value.equals(v))
				return key;
			
		}
		return "";

	}
}
