package edu.crypto.secretsharing.domain

import edu.crypto.secretsharing.util.MathUtils

class Coefficient {

    List<Double> value

    Coefficient(List<Double> value) {
        this.value = value
    }

    @Override
    public String toString() {
        if(value.size() == 1) {
            return value.get(0)
        }
        String result = "(";
        for (int i = 0; i < value.size() ; i++) {
            def number = value.get(i)
            def numberString = "";
            if(MathUtils.isApproximatelyLong(number)) {
                numberString += (long) number;
            } else {
                numberString += number;
            }
            if(i != value.size()-1) {
                result += numberString + ", "
            } else {
                result += numberString + ")"
            }
        }
        return result
    }
}
