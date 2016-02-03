package edu.crypto.secretsharing.shamir;

import edu.crypto.secretsharing.exception.ServerException;
import edu.crypto.secretsharing.shamir.domain.ShamirShare;
import edu.crypto.secretsharing.shamir.dto.ShamirReconstructRequestDTO;
import edu.crypto.secretsharing.util.MathUtils;
import edu.crypto.secretsharing.workers.SecretTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShamirSecretReconstructor {

    private static List<? extends Number> x;
    private static List<List<? extends Number>> y;
    private static long k;


    public static String reconstruct(ShamirReconstructRequestDTO reconstructRequest) throws ServerException {
        List<Number> secret = reconstruct(reconstructRequest.getShares(), reconstructRequest.getType() == 3);
        return SecretTransformer.transformToSecret(reconstructRequest.getType(), secret);
    }

    public static List<Number> reconstruct(List<ShamirShare> shares, boolean isString) {
        k = shares.size();
        x = buildXVector(shares, isString);
        y = buildYVector(shares, isString);
        List<Number> secret = getCurrentVector(0);
        for (int i = 1; i < k; i++) {
            secret = MathUtils.addVectors(secret, getCurrentVector(i));
        }
        return secret;
    }

    private static List<Number> getCurrentVector(int i) {
        List<? extends Number> currentY = y.get(i);
        double product = 1.0;
        for (int j = 0; j < k; j++) {
            if(j != i) {
                product *= (-x.get(j).doubleValue()) / (x.get(i).doubleValue() - x.get(j).doubleValue());
            }
        }
        return MathUtils.multiplyByScalar(product, currentY);
    }

    private static List<List<? extends Number>> buildYVector(List<ShamirShare> shares, boolean isString) {
        List<List<? extends Number>> result = new ArrayList<>();
        if(isString) {
            for (ShamirShare share : shares) {
                List<Long> longs = new ArrayList<>();
                for (Number value : share.getValue()) {
                    longs.add(value.longValue());
                }
                result.add(longs);
            }
        } else {
            for (ShamirShare share : shares) {
                result.add(share.getValue());
            }
        }
        return result;
    }

    private static List<? extends Number> buildXVector(List<ShamirShare> shares, boolean isString) {
        if(isString) {
            return shares.stream().map(share -> Math.round(share.getX())).collect(Collectors.toList());
        } else {
            List<Double> result = new ArrayList<>();
            for (ShamirShare share : shares) {
                result.add(share.getX());
            }
            return result;
        }
    }

}
