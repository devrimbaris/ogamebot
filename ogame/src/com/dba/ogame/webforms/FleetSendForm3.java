/*
 * Created on 14-Apr-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dba.ogame.webforms;

import java.lang.reflect.Field;

import org.apache.commons.httpclient.HttpClient;

import com.dba.ogame.model.Planet;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FleetSendForm3 extends AbstractForm {

	String thisgalaxy="2";
	String thissystem="74";
	String thisplanet="9";
	String thisplanettype="1";
	String speedfactor="1";
	String thisresource1="25382";
	String thisresource2="11053";
	String thisresource3="14897";
	String ship202="1";
	String consumption202="10";
	String speed202="8000";
	String capacity202="5000";
	String galaxy="2";
	String system="76";
	String planet="13";
	String planettype="1";
	String speed="10";

	public FleetSendForm3(String url, HttpClient client,Planet source,Planet target) {
		super(url, client);
		thisgalaxy=source.getGalaksi();
		thissystem=source.getSistem();
		thisplanet=source.getGezegen();
		thisresource1="" + source.getResourceStatus().getIntMetal() ;
		thisresource2="" + source.getResourceStatus().getIntKristal() ;
		thisresource3="" + source.getResourceStatus().getIntDeuterium() ;
		galaxy=target.getGalaksi();
		system=target.getSistem();
		planet=target.getGezegen();
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
