package com.test;

import junit.framework.TestCase;

public class PMMLEvaluateTest extends TestCase {

	public void testUrbanMale() throws Exception {
		NeuralInsuranceEvaluator app = new NeuralInsuranceEvaluator("neural_insurance.pmml");
		double result = app.evaluate(Gender.MALE, 3, Domicile.URBAN, 3.0);
		System.out.println("Predicted claim amount: " + result);

	}
	public void testRuralFemale() throws Exception {
		NeuralInsuranceEvaluator app = new NeuralInsuranceEvaluator("neural_insurance.pmml");
		double result = app.evaluate(Gender.FEMALE, 5, Domicile.RURAL, 13.0);
		System.out.println("Predicted claim amount: " + result);
	}
}
