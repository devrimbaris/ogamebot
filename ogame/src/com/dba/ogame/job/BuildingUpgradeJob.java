package com.dba.ogame.job;



public class BuildingUpgradeJob extends Job {

	String buildingName;
	int level;
	
	public BuildingUpgradeJob() {
		super();
	}
	
	public BuildingUpgradeJob(String buildingName, int level) {
		super();
		this.buildingName = buildingName;
		this.level = level;
	}

	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}



	public String toString(){
		return " Upgrading " + getBuildingName() + 	
		" to level " + getLevel();
	}
}