package com.example.demo.model;

public class TransportationInfo {
	
	private String fare;
	private String time;
	private String transfer;
	private String station;
	
	@Override
	public String toString() {
		return "TransportationInfo [fare=" + fare + ", time=" + time + ", transfer=" + transfer + ", station=" + station
				+ "]";
	}
	
	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTransfer() {
		return transfer;
	}
	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}
	
	
}
