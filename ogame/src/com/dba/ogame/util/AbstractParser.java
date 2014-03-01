/*
 * Created on 08-Apr-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dba.ogame.util;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class AbstractParser {
	String data;
	
	public AbstractParser(String data){
		setData(data);
		init();
		parse();
	}
	protected abstract void init();
	protected abstract void parse();

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	protected String searchData(String searchData,String startFlag,String endFlag){
		
		
		int start=searchData.indexOf(startFlag);
		int end=searchData.indexOf(endFlag,start);
		String str=searchData.substring(start+startFlag.length(),end);
		return str;
		
		
	}

	

	
}
