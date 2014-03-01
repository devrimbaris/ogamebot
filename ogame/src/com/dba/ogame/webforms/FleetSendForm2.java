package com.dba.ogame.webforms;
import java.lang.reflect.Field;

import org.apache.commons.httpclient.HttpClient;

public class FleetSendForm2 extends AbstractForm {

	String maxship202 = "2";
	String consumption202 = "10";
	String speed202 = "8000";
	String capacity202 = "5000";
	String ship202 = "1";
	
	

	public FleetSendForm2(String url, HttpClient client) {
		super(url, client);
		
	}
	protected void createFormParameters() {
		Field[] arrFields=this.getClass().getDeclaredFields();
		try {
			for(int i=0;i<arrFields.length;i++){
				String value = (String) arrFields[i].get(this);
				getFormParameters().put(arrFields[i].getName(),value);
			}
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		}
		
	}
	
	protected void setFormReturnParameters() {
		
		
	}
	
	
}
