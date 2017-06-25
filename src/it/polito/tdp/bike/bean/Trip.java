package it.polito.tdp.bike.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Trip {
	
	private int tripId ; //numeric ID of bike trip
	private int duration ; //time of trip in seconds
	private LocalDateTime startDate ; //start date of trip with date and time, in PST
	private String startStation ; //station name of start station
	private int startTerminal ; //numeric reference for start station
	private LocalDateTime endDate ; //end date of trip with date and time, in PST
	private String endStation ; //station name for end station
	private int endTerminal ; //numeric reference for end station
	private int bikeNum ; //ID of bike used
	private String subscriptionType ; //Subscriber = annual member; Customer = 24-hour or 3-day member
	private int zipCode ; //Home zip code of user (only available for annual members)
	
	public Trip(int tripId, int duration, LocalDateTime startDate, String startStation,
			int startTerminal, LocalDateTime endDate, String endStation,
			int endTerminal, int bikeNum, String subscriptionType, int zipCode) {
		super();
		this.tripId = tripId;
		this.duration = duration;
		this.startDate = startDate;
		this.startStation = startStation;
		this.startTerminal = startTerminal;
		this.endDate = endDate;
		this.endStation = endStation;
		this.endTerminal = endTerminal;
		this.bikeNum = bikeNum;
		this.subscriptionType = subscriptionType;
		this.zipCode = zipCode;
	}

	public int getTripId() {
		return tripId;
	}

	public void setTripId(int tripId) {
		this.tripId = tripId;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public String getStartStation() {
		return startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	public int getStartTerminal() {
		return startTerminal;
	}

	public void setStartTerminal(int startTerminal) {
		this.startTerminal = startTerminal;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getEndStation() {
		return endStation;
	}

	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}

	public int getEndTerminal() {
		return endTerminal;
	}

	public void setEndTerminal(int endTerminal) {
		this.endTerminal = endTerminal;
	}

	public int getBikeNum() {
		return bikeNum;
	}

	public void setBikeNum(int bikeNum) {
		this.bikeNum = bikeNum;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	
	

}
