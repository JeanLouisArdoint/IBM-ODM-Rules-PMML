/*
Copyright (c) Jean-Louis Ardoint 2017. All Rights Reserved.
Project name: IBM-ODM-Rules-PMML
This project is licensed under the Apache License 2.0, see LICENSE.
*/

package com.test;

import javax.xml.bind.annotation.XmlElement;

public class Car {
	int numberOfClaims;
	int age;

	// for JAXB
	@SuppressWarnings("unused")
	private Car() {}
	
	public Car(int numberOfClaims, int age) {
		this.numberOfClaims = numberOfClaims;
		this.age = age;
	}

	@XmlElement(required = true)
	public int getNumberOfClaims() {
		return numberOfClaims;
	}

	public void setNumberOfClaims(int numberOfClaims) {
		this.numberOfClaims = numberOfClaims;
	}
	
	@XmlElement(required = true)
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
