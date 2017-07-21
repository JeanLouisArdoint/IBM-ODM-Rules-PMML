/*
Copyright (c) Jean-Louis Ardoint 2017. All Rights Reserved.
Project name: IBM-ODM-Rules-PMML
This project is licensed under the Apache License 2.0, see LICENSE.
*/

package com.test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Util {
	private Util() {}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

}
