package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.transform.sax.SAXSource;

import org.dmg.pmml.FieldName;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.FieldValue;
import org.jpmml.evaluator.InputField;
import org.jpmml.evaluator.neural_network.NeuralNetworkEvaluator;
import org.jpmml.model.ImportFilter;
import org.jpmml.model.JAXBUtil;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 */
public class NeuralInsuranceEvaluator {

	Evaluator evaluator;

	public NeuralInsuranceEvaluator(String pmmlFileName) throws IOException,
			SAXException, JAXBException {
		PMML pmml = createPMMLfromFile(pmmlFileName);
		evaluator =  new NeuralNetworkEvaluator(pmml);
	}

	public double evaluate(Gender gender,
			int numberOfClaims,
			Domicile domicile,
			double ageOfCar) {
		Object[] args = new Object[4];
		switch (gender) {
		case FEMALE:
			args[0] = "female";
			break;
		case MALE:
			args[0] = "male";
		}
		assert numberOfClaims >= 0;
		switch (numberOfClaims) {
		case 0:
		case 1:
		case 2:
		case 3:
			args[1] = "" + numberOfClaims;
			break;
		default:
			args[1] = ">3";
		}
		switch (domicile) {
		case SUBURBAN:
			args[2] = "suburban";
			break;
		case URBAN:
			args[2] = "urban";
			break;
		case RURAL:
			args[2] = "rural";
			break;
		}
		args[3] = ageOfCar;
		Map<FieldName, ?> output = callPMMLEvaluate(args);

		double predictedClaimAmount = (double) output.get(new FieldName(
				"amount of claims"));
		return predictedClaimAmount;
	}

	protected Map<FieldName, ?> callPMMLEvaluate(Object[] arg_values) {
		Map<FieldName, Object> arguments = new LinkedHashMap<>();
		List<InputField> inputFields = evaluator.getInputFields();
		int i = 0;
		for (InputField inputField : inputFields) {
			try {
				FieldValue inputFieldValue = inputField
						.prepare(arg_values[i++]);
				arguments.put(inputField.getName(), inputFieldValue);
			} catch (Exception e) {
				System.err.println("Error while preparing "
						+ inputField.getName().getValue());
				throw e;
			}
		}

		Map<FieldName, ?> output = evaluator.evaluate(arguments);
		return output;
	}

	public static void main(String... args) throws Exception {
		NeuralInsuranceEvaluator app = new NeuralInsuranceEvaluator("neural_insurance.pmml");
		double result = app.evaluate(Gender.MALE, 3, Domicile.URBAN, 3.0);
		System.out.println("Predicted claim amount: " + result);

		result = app.evaluate(Gender.FEMALE, 5, Domicile.RURAL, 13.0);
		System.out.println("Predicted claim amount: " + result);
	}

	protected PMML createPMMLfromFile(String fileName) throws IOException,
			SAXException, JAXBException {
		try (InputStream stream = getClass().getResourceAsStream(fileName)) {
			InputSource source = new InputSource(stream);
			source.setPublicId(fileName);
			SAXSource transformedSource = ImportFilter.apply(source);
			return JAXBUtil.unmarshalPMML(transformedSource);
		}
	}
}
