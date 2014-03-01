
package com.dba.ogame.webforms;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import au.id.jericho.lib.html.FormField;
import au.id.jericho.lib.html.FormFields;
import au.id.jericho.lib.html.Source;

public abstract  class AbstractForm  {

	private String url;
	private PostMethod postmethod;
	private HttpClient httpclient;
	private Map formParameters;
	private Map formReturnValues;
	private String response;
	
	
	
	
	public AbstractForm(String url,HttpClient client) {
		super();
		formParameters=new ListOrderedMap ();
		formReturnValues=new ListOrderedMap();
		setUrl(url);
		setHttpclient(client);
		
	}
	public void execute() throws HttpException, IOException{
	
		createFormParameters();
		postmethod=new PostMethod(getUrl());
		prepareFormArgumentsForPost();
		postmethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));
		httpclient.executeMethod(postmethod);
		response = new String(postmethod.getResponseBody());
		//System.out.println(this.getClass().getName() + "->" +response);
		parseFormReturnValues();
		setFormReturnParameters();
		
		
	}
	private void prepareFormArgumentsForPost(){
		Iterator iter=formParameters.keySet().iterator();
		while (iter.hasNext()) {
			String  parameter = (String ) iter.next();
			String value=(String)formParameters.get(parameter);
			postmethod.addParameter(parameter,value);
		}
		
	}
	private void parseFormReturnValues(){
		Source source=new Source(getResponse());
		FormFields formFields = source.findFormFields();
		if (formFields != null) {
			for (Iterator i = formFields.iterator(); i.hasNext();) {
				FormField formField = (FormField) i.next();
				Iterator iter = formField.getValues().iterator();
				String value = "";
				if (iter.hasNext())
					value = (String) iter.next();
				formReturnValues.put(formField.getName(), value);
			}
		}
	}
	protected abstract void createFormParameters();
	protected abstract void setFormReturnParameters();
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url=url;
	}
	
	public Map getFormReturnValues() {
		return formReturnValues;
	}
	public String getResponse() {
		return response;
	}
	public Map getFormParameters() {
		return formParameters;
	}
	
	private HttpClient getHttpclient() {
		return httpclient;
	}
	
	private void setHttpclient(HttpClient httpclient) {
		this.httpclient = httpclient;
	}
}
