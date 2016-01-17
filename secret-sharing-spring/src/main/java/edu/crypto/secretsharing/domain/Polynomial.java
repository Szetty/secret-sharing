package edu.crypto.secretsharing.domain;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {

    private int degree;
    private List<Coefficient> coefficients;

    public Polynomial(int degree) {
        this.degree = degree;
        coefficients = new ArrayList<>();
    }

    public void add(Coefficient coefficient) {
        coefficients.add(coefficient);
    }


    @Override
    public String toString() {
        String result = coefficients.get(0).toString() + "+" + coefficients.get(1) + "X";
        for (int i = 2; i < degree; i++) {
            result += "+" + coefficients.get(i) + "X^" + i;
        }
        return result;
    }
}
