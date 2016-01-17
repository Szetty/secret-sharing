package edu.crypto.secretsharing.domain

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
            if(i != value.size()-1) {
                result += number + ", "
            } else {
                result += number + ")"
            }
        }
        return result
    }
}
