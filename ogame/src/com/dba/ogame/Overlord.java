package com.dba.ogame;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.httpclient.HttpClient;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

import com.dba.ogame.model.Planet;
import com.dba.ogame.model.PlanetsList;
import com.dba.ogame.service.PlanetListFactory;
import com.dba.ogame.service.ServiceConsolidator;
import com.dba.ogame.util.PropertiesReader;
import com.dba.ogame.webforms.LoginForm;
public class Overlord {
	private HttpClient client;

	private String sessionId;

	private PropertiesReader reader;

	

	private Map planetList = new TreeMap();

	public Overlord() throws MarshalException, ValidationException,
			FileNotFoundException, IOException, MappingException,
			CloneNotSupportedException {
		super();

		init();

	}

	private void init() throws MarshalException, ValidationException,
			FileNotFoundException, IOException, MappingException,
			CloneNotSupportedException {
		client = new HttpClient();
		
		System.out.println("Initializing...");
		reader = new PropertiesReader("application.properties");
		System.out.println("Loading missions from file...");
		System.out.println("Loading Planets from file...");
		loadPlanetsFromFile();

	}

	private void loadPlanetsFromFile() throws MarshalException, ValidationException, FileNotFoundException, IOException, MappingException, CloneNotSupportedException {

		PlanetsList pl=PlanetListFactory.getInstance().getPlanetsList("planetsOf33");
		Iterator iter=pl.getArlPlanets().iterator();
		while (iter.hasNext()){
			Planet p=(Planet)iter.next();
			getPlanetList().put(p.getPlanetId(), p);
		}
	}


	public void run() throws MarshalException, ValidationException,
			FileNotFoundException, InstantiationException,
			IllegalAccessException, ClassNotFoundException, IOException,
			MappingException, CloneNotSupportedException {

		loginUniverse("33");

		Iterator iter = planetList.values().iterator();
		while (iter.hasNext()) {

			Planet p = (Planet) iter.next();
			System.out.println("Overlord->Processing planet:" + p.getName());
			
			if (p.getMissionName() != "") {
				ServiceConsolidator src = new ServiceConsolidator(getPlanetList());
				src.service(p, getClient(), getSessionId());
			}

		}
		System.out.println("Overlord->Cycle completed");

	}

	public void loginUniverse(String strUniverse) throws IOException {

		PropertiesReader pReader = new PropertiesReader(
				"application.properties");
		String strLogin = pReader.getProperty("login.login");
		String strPass = pReader.getProperty("login.pass");

		
		System.out.println("Log stage 1...");
		LoginForm form1 = new LoginForm(pReader.getProperty("url.login1"),
				client, strLogin, strPass, strUniverse);
		form1.execute();

		form1.setUrl(pReader.getProperty("url.login2"));
		System.out.println("Log stage 2...");
		form1.execute();
		sessionId = form1.getSessionId();
	}



	private Map getPlanetList() {
		return planetList;
	}

	public HttpClient getClient() {
		return client;
	}

	private String getSessionId() {
		return sessionId;
	}
	
	
	
}