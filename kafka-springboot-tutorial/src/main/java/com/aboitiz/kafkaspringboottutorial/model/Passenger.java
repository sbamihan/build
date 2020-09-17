package com.aboitiz.kafkaspringboottutorial.model;

import java.util.Date;

public class Passenger {

	private String uuId;
	private String firstName;
	private String lastName;
	private String age;
	private String gender;
	private double bodyTemperature;
	private boolean overseasTravelHistory;
	private Date eventTimestamp;

	public Passenger() {
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getBodyTemperature() {
		return bodyTemperature;
	}

	public void setBodyTemperature(double bodyTemperature) {
		this.bodyTemperature = bodyTemperature;
	}

	public boolean isOverseasTravelHistory() {
		return overseasTravelHistory;
	}

	public void setOverseasTravelHistory(boolean overseasTravelHistory) {
		this.overseasTravelHistory = overseasTravelHistory;
	}

	public Date getEventTimestamp() {
		return eventTimestamp;
	}

	public void setEventTimestamp(Date eventTimestamp) {
		this.eventTimestamp = eventTimestamp;
	}

}
