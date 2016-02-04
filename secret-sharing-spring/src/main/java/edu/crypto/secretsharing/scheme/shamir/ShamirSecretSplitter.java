package edu.crypto.secretsharing.scheme.shamir;

import edu.crypto.secretsharing.scheme.domain.Share;
import edu.crypto.secretsharing.scheme.dto.SplitRequestDTO;
import edu.crypto.secretsharing.exception.ServerException;
import edu.crypto.secretsharing.scheme.shamir.domain.Polynomial;
import edu.crypto.secretsharing.scheme.shamir.factory.PolynomialFactory;
import edu.crypto.secretsharing.scheme.shamir.factory.ShareFactory;
import edu.crypto.secretsharing.scheme.SecretTransformer;

import java.util.List;

public class ShamirSecretSplitter {

    public static List<Share> splitSecret(SplitRequestDTO splitRequest) throws ServerException {
        List<Double> secret = SecretTransformer.transformSecretToDoubleList(splitRequest.getType(), splitRequest.getSecret());
        Polynomial polynomial = PolynomialFactory.buildPolynomial(secret, splitRequest.getT());
        System.out.println("The polynomial built is: " + polynomial);
        return ShareFactory.generateShares(polynomial, splitRequest.getN(), splitRequest.getType() == 2);
    }

}
