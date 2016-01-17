package edu.crypto.secretsharing.domain

class Polynomial {

    int degree;
    List<Coefficient> coefficients;

    Polynomial(int degree) {
        this.degree = degree
        coefficients = new ArrayList<>();
    }

    void add(Coefficient coefficient) {
        coefficients.add(coefficient)
    }


    @Override
    public String toString() {
        String result = coefficients.get(0).toString();
        for (int i = 1; i < degree; i++) {
            result += "+" + coefficients.get(i) + "X^" + i;
        }
        return result
    }
}
