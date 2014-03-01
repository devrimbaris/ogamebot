package com.dba.ogame.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

import com.dba.ogame.job.Job;
import com.dba.ogame.job.Mission;
import com.dba.ogame.model.Planet;

public class ServiceConsolidator {
	
	Map planetList;
	public ServiceConsolidator(Map planetList){
		this.planetList=planetList;
	}
	
	
	public void service(Planet p,HttpClient client,String sessionId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, MarshalException, ValidationException, FileNotFoundException, IOException, MappingException, CloneNotSupportedException{
		
		if (p.getMissionName() == null || p.getMissionName().equals("")){
			System.out.println("No missions found for planet:"+p);
			return;
		}
		Mission m=MissionFactory.getInstance().getMission(p.getMissionName());
		Iterator iter=m.getJobList().iterator();
		while (iter.hasNext()) {

			Job j=(Job)iter.next();
			AbstractService service = ServiceFactory.getInstance()
					.getServiceForJob(j);
			service.setPlanetRepository(getPlanetList());
			service.setPlanet(p);
			service.setJob(j);
			service.setClient(client);
			service.setSessionId(sessionId);
			try{
				service.execute();
			}
			catch(BuildingQueueFullException exc){
				break;
			}
		}

	}
	private Map getPlanetList() {
		return planetList;
	}
	private void setPlanetList(Map planetList) {
		this.planetList = planetList;
	}

}
