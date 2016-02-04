package edu.crypto.secretsharing.scheme;

import edu.crypto.secretsharing.exception.ServerException;
import edu.crypto.secretsharing.scheme.crt.CRTSecretSplitter;
import edu.crypto.secretsharing.scheme.domain.Share;
import edu.crypto.secretsharing.scheme.dto.SplitRequestDTO;
import edu.crypto.secretsharing.scheme.shamir.ShamirSecretSplitter;

import java.util.List;

public class SecretSplitter {

    public static List<Share> splitSecret(SplitRequestDTO request) throws ServerException {
        switch (request.getScheme()) {
            case 1:
                return ShamirSecretSplitter.splitSecret(request);
            case 2:
                return CRTSecretSplitter.splitSecret(request);
            default:
                throw new ServerException("Scheme not recognized");
        }
    }

}
