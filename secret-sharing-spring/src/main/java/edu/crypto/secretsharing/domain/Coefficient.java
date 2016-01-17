package edu.crypto.secretsharing.domain;

import edu.crypto.secretsharing.util.MathUtils;

import java.util.List;

public class Coefficient {

    private List<Double> value;

    public Coefficient(List<Double> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if(value.size() == 1) {
            return MathUtils.numberToString(value.get(0));
        }
        String result = "(";
        for (int i = 0; i < value.size() ; i++) {
            Double number = value.get(i);
            String numberString = MathUtils.numberToString(number);
            if(i != value.size()-1) {
                result += numberString + ", ";
            } else {
                result += numberString + ")";
            }
        }
        return result;
    }
}
