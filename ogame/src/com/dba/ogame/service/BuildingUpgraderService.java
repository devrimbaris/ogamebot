package com.dba.ogame.service;

import java.io.IOException;

import org.apache.commons.httpclient.CircularRedirectException;

import com.dba.ogame.job.BuildingUpgradeJob;
import com.dba.ogame.model.Building;
import com.dba.ogame.model.Planet;
import com.dba.ogame.webforms.GenericGetForm;

public class BuildingUpgraderService extends AbstractService {

	public BuildingUpgraderService() {

		super();
	}

	public void execute() throws IOException, BuildingQueueFullException {
		BuildingUpgradeJob buJob = (BuildingUpgradeJob) job;
		Planet p = getPlanet();

		Building buildingTobeUpgraded = (Building) p.getBuildingStatus()
				.getBuildings().get(buJob.getBuildingName());

		if (buildingTobeUpgraded != null) {
			if (buildingTobeUpgraded.getCurrentLevel() < buJob.getLevel()) {
				if (buildingTobeUpgraded.isAvailableForUpgrade()) {
					String url = formUrl(getReader().getProperty(
							"url.buildingupgrade"));
					GenericGetForm getform = new GenericGetForm(url,
							getClient());
					try {
						getform.execute();
					} catch (CircularRedirectException exc) {

					}
					System.out.println("BuildingUpgrade->"+p+ " is " +buJob.toString().toUpperCase());
				}
				else
				{
					System.out.println("BuildingUpgrade->:"+p+ " is waiting for "+buJob);
				}
				
				throw new BuildingQueueFullException();
			}
		}

	}

	protected String formUrl(String targetUrl)
			throws IOException {
		BuildingUpgradeJob buJob = (BuildingUpgradeJob) job;
		String s = super.formUrl(targetUrl);
		s = s.replaceAll("\\{\\$buildingId\\}", getReader().getProperty(
				buJob.getBuildingName()));
		return s;
	}
}
