package edu.crypto.secretsharing.transformer;

import edu.crypto.secretsharing.exception.ServerException;

import java.util.ArrayList;
import java.util.List;

public class SecretTransformer {

    public static List<Double> transformSecret(int type, String secret) throws ServerException {
        List<Double> secretList = new ArrayList<>();
        switch (type) {
            case 1:
                secretList.add(Double.parseDouble(secret));
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                throw new ServerException("Unknown type "+type);
        }
        return secretList;
    }
}
