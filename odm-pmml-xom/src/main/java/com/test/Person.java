/*
Copyright (c) Jean-Louis Ardoint 2017. All Rights Reserved.
Project name: IBM-ODM-Rules-PMML
This project is licensed under the Apache License 2.0, see LICENSE.
*/

package com.test;

import javax.xml.bind.annotation.XmlElement;

public class Person {
	@XmlElement(name="gender", required=true)
	private Gender gender;
	@XmlElement(name="name", required=true)
	private String name;
	@XmlElement(name="domicile", required=true)
	private Domicile domicile;
	
	// for JAXB
	@SuppressWarnings("unused")
	private Person() {}
	
	public Person(Gender gender, String name, Domicile domicile) {
		this.gender = gender;
		this.name = name;
		this.domicile = domicile;
	}

	public Domicile getDomicile() {
		return domicile;
	}

	public Gender getGender() {
		return gender;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return gender == Gender.FEMALE ? "Mrs" : "Mr" + "" + name + ", from "
				+ domicile;
	}
	
}
