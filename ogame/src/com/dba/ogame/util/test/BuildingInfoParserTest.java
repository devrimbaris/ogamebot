
package com.dba.ogame.util.test;
import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;

import com.dba.ogame.model.BuildingStatus;
import com.dba.ogame.util.BuildingInfoHtmlParser;

import junit.framework.TestCase;


public class BuildingInfoParserTest extends TestCase {

	String parseData;
	
	protected void setUp() throws Exception {
		super.setUp();
		StringWriter sw=new StringWriter();
		FileReader fr=new FileReader(new File("buildingstatus.txt"));
		char[] buff=new char[100];
		int len=0;
		int offset=0;
		while((len=fr.read(buff))> 0){
			sw.write(buff,0,len);
			
		}
		parseData=sw.toString();
			
		
		
	}

	public void testInit() {
		BuildingInfoHtmlParser parser=new BuildingInfoHtmlParser(parseData);
		
		assertNotNull(parser.getBuildingStatus());
	}

	public void testParse() {
		BuildingInfoHtmlParser parser=new BuildingInfoHtmlParser(parseData);
		BuildingStatus status=parser.getBuildingStatus();
		
	}

	

	

}
