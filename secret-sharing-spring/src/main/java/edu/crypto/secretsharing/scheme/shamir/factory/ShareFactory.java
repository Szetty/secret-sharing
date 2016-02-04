package edu.crypto.secretsharing.scheme.shamir.factory;

import edu.crypto.secretsharing.scheme.domain.Share;
import edu.crypto.secretsharing.scheme.shamir.domain.Polynomial;
import edu.crypto.secretsharing.scheme.shamir.domain.ShamirShare;
import edu.crypto.secretsharing.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class ShareFactory {

    public static List<Share> generateShares(Polynomial polynomial, int shareNr, boolean isString) {
        List<Share> shares = new ArrayList<>();
        for (int i = 0; i < shareNr; i++) {
            double x;
            if(isString) {
                x = RandomGenerator.generateX(polynomial.getInterval());
            } else {
                x = RandomGenerator.generateLongX(polynomial.getInterval());
            }
            List<? extends Number> value = polynomial.calculatePolynomialValue(x);
            shares.add(new ShamirShare(x, value));
        }
        return shares;
    }

}
