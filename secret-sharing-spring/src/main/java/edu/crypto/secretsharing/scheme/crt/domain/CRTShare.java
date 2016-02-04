package edu.crypto.secretsharing.scheme.crt.domain;

import edu.crypto.secretsharing.scheme.domain.Share;

import java.util.List;

public class CRTShare implements Share {

    public List<Long> value;

    public CRTShare() {}

    public CRTShare(List<Long> value) {
        this.value = value;
    }

    public List<Long> getValue() {
        return value;
    }

    public void setValue(List<Long> value) {
        this.value = value;
    }
}
