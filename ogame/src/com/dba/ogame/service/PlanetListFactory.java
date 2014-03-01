package com.dba.ogame.service;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.InputSource;

import com.dba.ogame.model.PlanetsList;


public class PlanetListFactory {


	    private static PlanetListFactory instance;
	    private static Map repository = new HashMap();

	    private static Unmarshaller unmarsaller;

	    public static PlanetListFactory getInstance() {
	        if (instance == null) {
	            instance = new PlanetListFactory();
	        }
	        return instance;
	    }

	    private PlanetListFactory() {
	        super();
	    }

	    
	    public PlanetsList getPlanetsList(String name) throws IOException, MappingException, MarshalException, ValidationException, FileNotFoundException, CloneNotSupportedException {
	    	PlanetsList planetList = (PlanetsList) repository.get(name);
	        if (planetList == null) {
	            Unmarshaller unmar = getUnmarshaller();
	            InputSource is=getInputSource(name);
	            planetList = (PlanetsList) unmar.unmarshal(is);
	            repository.put(name, planetList);
	        }
	        TreeMap map=new TreeMap();
	        return (PlanetsList) planetList.clone();
	    }

	   
	    private Unmarshaller getUnmarshaller() throws IOException, MappingException {
	        if (unmarsaller == null) {
	            Mapping mapping = getMapping();
	            unmarsaller = new Unmarshaller(mapping);
	        }

	        return unmarsaller;
	    }

	  
	    private InputSource getInputSource(String name) throws FileNotFoundException {
	        return new InputSource(new FileReader(getRelativePath(name)));
	    }

	    private String getRelativePath(String name) {
	        return "missions/" + name + ".xml";
	    }

	    public void saveMission(String name, PlanetsList planetList) throws Exception  {
	        try {
	            Mapping mapping = getMapping();
	            Marshaller marsaller = new Marshaller(new FileWriter(getRelativePath(name)));
	            marsaller.setMapping(mapping);
	            marsaller.marshal(planetList);
	       } catch (Exception e) {
	            throw e;
	        }
	    }

	   
	    private Mapping getMapping() throws IOException, MappingException {
	    	Mapping mapping = new Mapping();
	        InputStream stream=this.getClass().getResourceAsStream("mapping.xml");
	        InputSource is=new InputSource(stream);
	        mapping.loadMapping(is);
	        return mapping;
	    }
	}
