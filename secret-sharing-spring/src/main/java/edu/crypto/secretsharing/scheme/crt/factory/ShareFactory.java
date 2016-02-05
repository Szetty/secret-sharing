package edu.crypto.secretsharing.scheme.crt.factory;

import edu.crypto.secretsharing.scheme.crt.domain.CRTShare;
import edu.crypto.secretsharing.scheme.domain.Share;
import edu.crypto.secretsharing.exception.ServerException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShareFactory {


    public static List<Share> generateShares(List<Long> modulos, List<Long> secret) throws ServerException {
        List<Share> shares = new ArrayList<>();
        for (Long modulo : modulos) {
            CRTShare share = new CRTShare();
            share.setValue(secret
                    .parallelStream()
                    .map(value -> value % modulo)
                    .collect(Collectors.toList())
            );
            share.setModulo(modulo);
            shares.add(share);
        }
        return shares;
    }


}
