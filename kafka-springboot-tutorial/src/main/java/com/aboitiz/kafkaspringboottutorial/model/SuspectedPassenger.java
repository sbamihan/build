package com.aboitiz.kafkaspringboottutorial.model;

public class SuspectedPassenger {

	private String uuId;
	private String age;
	private String gender;
	private double bodyTemperature;

	public SuspectedPassenger() {
	}

	public SuspectedPassenger(String uuId, String age, String gender, double bodyTemperature) {
		this.uuId = uuId;
		this.age = age;
		this.gender = gender;
		this.bodyTemperature = bodyTemperature;
	}

	public String getUuId() {
		return uuId;
	}

	public String getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public double getBodyTemperature() {
		return bodyTemperature;
	}

}