/*
 * Created on 13-Apr-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dba.ogame.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.dba.ogame.job.Job;
import com.dba.ogame.util.PropertiesReader;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceFactory {

	Map serviceRepository=new HashMap();
	PropertiesReader  reader;
	
	private ServiceFactory(){
		try {
			reader=new PropertiesReader("application.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static ServiceFactory _instance;
	
	public static ServiceFactory getInstance(){
		if (_instance==null)
			_instance=new ServiceFactory();
		return _instance;
	}
	
	public AbstractService getServiceForJob(Job j)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		AbstractService service = null;
		String className = j.getClass().getName();
		if (serviceRepository.get(className) == null) {
			service = (AbstractService) Class.forName(
					reader.getProperty(className)).newInstance();
			serviceRepository.put(className, service);
		} else
			service = (AbstractService) serviceRepository.get(className);
		return service;
	}
	
}
