package com.dba.ogame.webforms;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import antlr.InputBuffer;

public class GenericGetForm {
	private String url;
	private GetMethod getmethod;
	private HttpClient httpclient;
	private String response;


	
	public GenericGetForm(String url, HttpClient httpclient) {
		super();
		setUrl(url);
		setHttpclient(httpclient);
		
	}
	
	
	
	public void execute() throws HttpException, IOException{
		String uri=getUrl();
		getmethod=new GetMethod(uri);
		getmethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));
		httpclient.executeMethod(getmethod);
		
		

		StringBuffer sbf=new StringBuffer();
		byte[] buf=new byte[200];
		int len=0;
		InputStream is=getmethod.getResponseBodyAsStream();
		while((len=is.read(buf,0,buf.length))> 0){
			String s =new String(buf,0,len);
			sbf.append(s);
		}
		this.setResponse(sbf.toString());
	}
	
	
	
	
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	private GetMethod getGetmethod() {
		return getmethod;
	}
	private void setGetmethod(GetMethod getmethod) {
		this.getmethod = getmethod;
	}
	public HttpClient getHttpclient() {
		return httpclient;
	}
	public void setHttpclient(HttpClient httpclient) {
		this.httpclient = httpclient;
	}
	
}
