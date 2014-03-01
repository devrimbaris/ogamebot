
package com.dba.ogame.service;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import com.dba.ogame.model.BuildingStatus;
import com.dba.ogame.model.DefenseStatus;
import com.dba.ogame.model.ResearchStatus;
import com.dba.ogame.model.ResourceStatus;
import com.dba.ogame.util.BuildingInfoHtmlParser;
import com.dba.ogame.util.ResourceHtmlParser;
import com.dba.ogame.webforms.GenericGetForm;


public class InfoGathererService extends AbstractService{

	public ResourceStatus getResourceStatus() throws HttpException, IOException {
		
			String url=formUrl(getReader().getProperty("url.resourceinfo"));
			GenericGetForm getform = new GenericGetForm(url,getClient());
			getform.execute();
			ResourceHtmlParser parser = new ResourceHtmlParser(getform
					.getResponse());
			ResourceStatus info = parser.getResourceStatus();
			return info;
		}

	public BuildingStatus getBuildingStatus() throws IOException {
		String rootUrl=getReader().getProperty("login.siteurl");
		String url=formUrl(getReader().getProperty("url.buildinginfo"));
		GenericGetForm getform = new GenericGetForm(url,getClient());
		getform.execute();
		BuildingInfoHtmlParser parser = new BuildingInfoHtmlParser(getform
				.getResponse());
		BuildingStatus info = parser.getBuildingStatus();
		return info;
		
		
	}

	
	public DefenseStatus getDefenseStatus() {
		return null;
	}

	
	public ResearchStatus getResearchStatus() {
		return null;
	}
	

	public void execute() throws IOException {
		
		getPlanet().setBuildingStatus(this.getBuildingStatus());
		getPlanet().setDefenseStatus(this.getDefenseStatus());
		getPlanet().setResearchStatus(this.getResearchStatus());
		getPlanet().setResourceStatus(this.getResourceStatus());
	}

	}

	

