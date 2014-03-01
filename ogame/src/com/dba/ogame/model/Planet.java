package com.dba.ogame.model;


public class Planet {

	String planetId;
	String name;
	String galaksi;
	


	String sistem;
	String gezegen;
	BuildingStatus buildingStatus;
	DefenseStatus defenseStatus;
	ResearchStatus researchStatus;
	ResourceStatus resourceStatus;
	String missionName="";
		
	public Planet() {
		super();
	}
	public Planet(String planetId) {
		setPlanetId(planetId);

	}
	public Planet(String planetId, String missionName,String name, String galaksi, String sistem,
			String gezegen) {
		super();
		this.planetId = planetId;
		this.missionName=missionName;
		this.name = name;
		this.galaksi = galaksi;
		this.sistem = sistem;
		this.gezegen = gezegen;
	}
	


	public BuildingStatus getBuildingStatus() {
		return buildingStatus;
	}

	public void setBuildingStatus(BuildingStatus buildingStatus) {
		this.buildingStatus = buildingStatus;
	}

	public DefenseStatus getDefenseStatus() {
		return defenseStatus;
	}

	public void setDefenseStatus(DefenseStatus defenseStatus) {
		this.defenseStatus = defenseStatus;
	}

	public String getPlanetId() {
		return planetId;
	}

	public void setPlanetId(String planetId) {
		this.planetId = planetId;
	}

	public ResearchStatus getResearchStatus() {
		return researchStatus;
	}

	public void setResearchStatus(ResearchStatus researchStatus) {
		this.researchStatus = researchStatus;
	}

	public ResourceStatus getResourceStatus() {
		return resourceStatus;
	}

	public void setResourceStatus(ResourceStatus resourceStatus) {
		this.resourceStatus = resourceStatus;
	}
	public String toString(){
		return getName()+ "(" +
		getGalaksi() + ":" + 
		getSistem() + ":" +
		getGezegen()+")";
		
	}
	public String getMissionName() {
		return missionName;
	}
	public void setMissionName(String missionName) {
		this.missionName = missionName;
	}
	public String getGalaksi() {
		return galaksi;
	}
	public void setGalaksi(String galaksi) {
		this.galaksi = galaksi;
	}
	public String getGezegen() {
		return gezegen;
	}
	public void setGezegen(String gezegen) {
		this.gezegen = gezegen;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSistem() {
		return sistem;
	}
	public void setSistem(String sistem) {
		this.sistem = sistem;
	}
}
