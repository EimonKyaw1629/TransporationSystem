package com.example.demo.model;

public class FareInfo extends DutyPersonInfo{

	private int FareID;
	private int PersonID;
	private String UseDate;
	private String Departure_Station;
	private String Arrival_Station;
	private String Purpose;
	private int Fare;
	
	public FareInfo()
	{
	
	}
	
	public FareInfo(int pid,String usedate,String starteki,String endeki,String purpose,int cost)
	{
		super();
		this.PersonID = pid;
		this.UseDate = usedate;
		this.Departure_Station =starteki;
		this.Arrival_Station = endeki;
		this.Purpose = purpose;
		this.Fare = cost;
	}
	
	public int getFareID()
	{
		return this.FareID;
	}
	
	public void setFareID(int i)
	{
		this.FareID = i;
	}
	
	public int getPersonID()
	{
		return this.PersonID;
	}
	
	public void setPersonID(int pid)
	{
		this.PersonID = pid;
	}
	
	public String getUseDate()
	{
		return this.UseDate;
	}
	
	public void setUseDate(String udate)
	{
		this.UseDate = udate;
	}
	
	public String getDepartureStation()
	{
		return this.Departure_Station;
	}
	
	public void setDepartureStation(String starteki)
	{
		this.Departure_Station = starteki;
	}
	
	public String getArrivalStation()
	{
		return this.Arrival_Station;
	}
	
	public void setArrivalStation(String endeki)
	{
		this.Arrival_Station =endeki;
	}
	
	public String getPurpose()
	{
		return this.Purpose;
	}
	
	public void setPurpose(String purpose)
	{
		this.Purpose = purpose;
	}
	
	public int getFare()
	{
		return this.Fare;
	}
	
	public void setFare(int cost)
	{
		this.Fare = cost;
	}
	
}
