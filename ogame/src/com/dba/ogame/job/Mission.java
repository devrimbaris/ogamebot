package com.dba.ogame.job;

import java.util.ArrayList;
import java.util.List;

public class Mission implements Cloneable{
	
	private String name;
	private int priority;
	private List jobList=new ArrayList();
	
	public Mission() {
		super();
	}

	
	public Mission(String name) {
		super();
		this.name = name;
		
	}
	public Mission(String name, int priority, ArrayList joblist) {
		super();
		this.name = name;
		this.priority = priority;
		this.jobList = joblist;
	}

	public List getJobList() {
		return jobList;
	}
	public void setJobList(List joblist) {
		this.jobList = joblist;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public Object clone() throws CloneNotSupportedException {
		
		return super.clone();
	}
}
