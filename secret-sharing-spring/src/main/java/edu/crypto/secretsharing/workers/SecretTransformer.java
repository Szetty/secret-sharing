package edu.crypto.secretsharing.workers;

import edu.crypto.secretsharing.exception.ServerException;
import edu.crypto.secretsharing.util.MathUtils;
import edu.crypto.secretsharing.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SecretTransformer {

    public static List<Double> transformSecretToDoubleList(int type, String secret) throws ServerException {
        List<Double> secretList = new ArrayList<>();
        switch (type) {
            case 1:
                secretList.add(Double.parseDouble(secret));
                break;
            case 2:
                String[] tokens = secret.split(",");
                for (String token : tokens) {
                    secretList.add(Double.parseDouble(token));
                }
                break;
            case 3:
                for (int i = 0; i < secret.length(); i++) {
                    secretList.add((double)secret.charAt(i));
                }
                break;
            default:
                throw new ServerException("Unknown type "+type);
        }
        return secretList;
    }

    public static String transformToSecret(int type, List<Number> secret) throws ServerException {
        switch (type) {
            case 1:
                return MathUtils.numberToString(secret.get(0));
            case 2:
                return StringUtils.vectorToString(secret);
            case 3:
                return secret
                        .parallelStream()
                        .map(val -> Character.toString((char) val.shortValue()))
                        .reduce(String::concat)
                        .get();
            default:
                throw new ServerException("Unknown type "+type);
        }
    }
}
