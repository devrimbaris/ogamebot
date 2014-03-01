
package com.dba.ogame.model;

import java.util.ArrayList;

public class PlanetsList implements Cloneable {

	private ArrayList arlPlanets=new ArrayList();
	
	public PlanetsList() {
		super();
	}
	public PlanetsList(ArrayList arlPlanets) {
		super();
		this.arlPlanets = arlPlanets;
	}

	public ArrayList getArlPlanets() {
		return arlPlanets;
	}
	public void setArlPlanets(ArrayList arlPlanets) {
		this.arlPlanets = arlPlanets;
	}
	public Object clone() throws CloneNotSupportedException {
		
		return super.clone();
	}
}
