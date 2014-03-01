package com.dba.ogame.service;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;

import com.dba.ogame.job.Job;
import com.dba.ogame.model.Planet;
import com.dba.ogame.util.PropertiesReader;

public abstract class AbstractService {

	private HttpClient client;
	private String sessionId;
	private Planet planet;
	private Map PlanetRepository;
	public Map getPlanetRepository() {
		return PlanetRepository;
	}
	public void setPlanetRepository(Map planetRepository) {
		PlanetRepository = planetRepository;
	}
	private PropertiesReader reader;
	Job job;
	
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public abstract void execute() throws IOException,BuildingQueueFullException;
	
	public AbstractService(){
		
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void init() throws IOException {
		reader=new PropertiesReader("application.properties");
	}
	
	public HttpClient getClient() {
		return client;
	}
	
	public void setClient(HttpClient client) {
		this.client = client;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	protected String formUrl(String targetUrl) throws IOException {
		
		String rootUrl=reader.getProperty("login.siteurl");
		
		targetUrl=targetUrl.replaceAll("\\{\\$login\\.siteurl\\}",rootUrl);
		targetUrl=targetUrl.replaceAll("\\{\\$sessionId\\}",getSessionId());
		targetUrl=targetUrl.replaceAll("\\{\\$planetId\\}",planet.getPlanetId());
		return targetUrl;
	}
	
	protected PropertiesReader getReader(){
		return this.reader;
	}
	
	public Planet getPlanet() {
		return planet;
	}
	
	public void setPlanet(Planet planet) {
		this.planet = planet;
	}
	
		
}
