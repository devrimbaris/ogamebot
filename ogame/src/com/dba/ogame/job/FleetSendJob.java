package com.dba.ogame.job;


public class FleetSendJob extends Job {


	String planetId;
	public FleetSendJob(){
		
	}
	
	public FleetSendJob(String planetId) {
		super();
		this.planetId = planetId;
	}
	

	public String getPlanetId() {
		return planetId;
	}
	public void setPlanetId(String planetId) {
		this.planetId = planetId;
	}
}
