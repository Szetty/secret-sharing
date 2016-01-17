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
            return value.get(0).toString();
        }
        String result = "(";
        for (int i = 0; i < value.size() ; i++) {
            Double number = value.get(i);
            String numberString = "";
            if(MathUtils.isApproximatelyLong(number)) {
                numberString += number.longValue();
            } else {
                numberString += number;
            }
            if(i != value.size()-1) {
                result += numberString + ", ";
            } else {
                result += numberString + ")";
            }
        }
        return result;
    }
}
