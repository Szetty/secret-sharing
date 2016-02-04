package edu.crypto.secretsharing.scheme.crt;

import edu.crypto.secretsharing.scheme.crt.factory.ModuloFactory;
import edu.crypto.secretsharing.scheme.crt.factory.ShareFactory;
import edu.crypto.secretsharing.scheme.domain.Share;
import edu.crypto.secretsharing.scheme.dto.SplitRequestDTO;
import edu.crypto.secretsharing.exception.ServerException;
import edu.crypto.secretsharing.scheme.SecretTransformer;

import java.util.List;

public class CRTSecretSplitter {

    public static List<Share> splitSecret(SplitRequestDTO request) throws ServerException {
        List<Long> transformedSecret = SecretTransformer.transformSecretToLongList(request.getType(), request.getSecret());
        List<Long> modulos = ModuloFactory.generateModulos(request.getN(), request.getT(), transformedSecret);
        System.out.println("(" + modulos
                .parallelStream()
                .map(Object::toString)
                .reduce((r, r2) -> r + "," + r2).get() + ")"
        );
        return ShareFactory.generateShares(modulos, transformedSecret);
    }

}
