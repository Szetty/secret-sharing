package edu.crypto.secretsharing.factory;

import edu.crypto.secretsharing.domain.Coefficient;
import edu.crypto.secretsharing.domain.Polynomial;
import edu.crypto.secretsharing.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class PolynomialFactory {

    private static Coefficient buildCoefficient(List<Double> value) {
        return new Coefficient(value);
    }

    public static Polynomial buildPolynomial(List<Double> secret, int degree) {
        int size = secret.size();
        Polynomial polynomial = new Polynomial(degree);
        polynomial.add(buildCoefficient(secret));
        for (int i = 1; i < degree; i++) {
            List<Double> value = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                Double number = RandomGenerator.generateRandomDouble();
                value.add(number);
            }
            polynomial.add(buildCoefficient(value));
        }
        return polynomial;
    }

}
