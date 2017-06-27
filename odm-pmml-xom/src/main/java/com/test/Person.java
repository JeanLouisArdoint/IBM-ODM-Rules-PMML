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
