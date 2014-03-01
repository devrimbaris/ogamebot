package com.dba.ogame.model;


public class Building {

	int currentLevel;
	String  buildingId;
	String buildingName;
	boolean availableForUpgrade;
	
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public int getCurrentLevel() {
		return currentLevel;
	}
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	
	public String toString() {
		
		return "Name:"+ getBuildingName()+
			" Id:" + getBuildingId() +
			" CurrentLevel:" + getCurrentLevel() +
			" Available for Upgrade:" +isAvailableForUpgrade();
		
	}
	public boolean isAvailableForUpgrade() {
		return availableForUpgrade;
	}
	public void setAvailableForUpgrade(boolean availableForUpgrade) {
		this.availableForUpgrade = availableForUpgrade;
	}
}
