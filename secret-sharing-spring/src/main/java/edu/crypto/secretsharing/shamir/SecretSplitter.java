package edu.crypto.secretsharing.shamir;

import edu.crypto.secretsharing.domain.Share;
import edu.crypto.secretsharing.dto.SplitRequestDTO;
import edu.crypto.secretsharing.exception.ServerException;
import edu.crypto.secretsharing.shamir.domain.Polynomial;
import edu.crypto.secretsharing.shamir.factory.PolynomialFactory;
import edu.crypto.secretsharing.shamir.factory.ShareFactory;
import edu.crypto.secretsharing.workers.SecretTransformer;

import java.util.List;

public class SecretSplitter {

    public static List<Share> generateShares(SplitRequestDTO splitRequest) throws ServerException {
        List<Double> secret = SecretTransformer.transformSecretToDoubleList(splitRequest.getType(), splitRequest.getSecret());
        Polynomial polynomial = PolynomialFactory.buildPolynomial(secret, splitRequest.getT());
        System.out.println("The polynomial built is: " + polynomial);
        return ShareFactory.generateShares(polynomial, splitRequest.getN(), splitRequest.getType() == 2);
    }

}
