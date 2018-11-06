package com.example.demo.model;

public class DutyPersonInfo {
	
	private int PersonID;
	private String PersonName;
	
	
	@Override
	public String toString() {
		return "DutyPersonInfo [PersonID=" + PersonID + ", PersonName=" + PersonName + "]";
	}
	
	public DutyPersonInfo() {
		
	}

	public DutyPersonInfo(int PersonID, String PersonName)
	{
		super();
		this.PersonID = PersonID;
		this.PersonName = PersonName;
	}
	
	public DutyPersonInfo(String name)
	{
		super();
		this.PersonName = name;
	}
	
	public int getPersonID()
	{
		return this.PersonID;
	}
	
	public void setPersonID(int id)
	{
		this.PersonID = id;
	}
	
	public String getPersonName()
	{
		return this.PersonName;
	}
	
	public void setPersonName(String name)
	{
		this.PersonName = name;
	}

}
