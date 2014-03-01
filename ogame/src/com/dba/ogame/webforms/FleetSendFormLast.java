package com.dba.ogame.webforms;

import java.lang.reflect.Field;

import org.apache.commons.httpclient.HttpClient;

import com.dba.ogame.model.Planet;

public class FleetSendFormLast extends AbstractForm {
	
	String thisgalaxy="2";
	String thissystem="74";
	String thisplanet="9";
	String thisplanettype="1";
	String speedfactor="1";
	String thisresource1="20517";
	String thisresource2="7819";
	String thisresource3="13434";
	String galaxy="2";
	String system="74";
	String planet="10";
	String planettype="1";
	String ship202="1";
	String consumption202="10";
	String speed202="8000";
	String capacity202="5000";
	String speed="10";
	String order="3";
	String resource1="8000";
	String resource2="3000";
	String resource3="600";
		
	public FleetSendFormLast(String url, HttpClient client, Planet source,
			Planet target, String order, String metal, String kristal,
			String deuterium) {
		super(url, client);
		thisgalaxy = source.getGalaksi();
		thissystem = source.getSistem();
		thisplanet = source.getGezegen();
		thisresource1 = "" + source.getResourceStatus().getIntMetal();
		thisresource2 = "" + source.getResourceStatus().getIntKristal();
		thisresource3 = "" + source.getResourceStatus().getIntDeuterium();
		galaxy = target.getGalaksi();
		system = target.getSistem();
		planet = target.getGezegen();
		this.order=order;
		resource1=metal;
		resource2=kristal;
		resource3=deuterium;

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


/*
 * String thisgalaxy="2";
	String thissystem="74";
	String thisplanet="9";
	String thisplanettype="1";
	String speedfactor="1";
	String thisresource1="20517";
	String thisresource2="7819";
	String thisresource3="13434";
	String galaxy="2";
	String system="74";
	String planet="10";
	String planettype="1";
	String ship202="1";
	String consumption202="10";
	String speed202="8000";
	String capacity202="5000";
	String speed="10";
	String order="3";
	String resource1="8000";
	String resource2="3000";
	String resource3="600";
	
	*/
