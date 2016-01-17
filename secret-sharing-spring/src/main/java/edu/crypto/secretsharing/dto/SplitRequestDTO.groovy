package edu.crypto.secretsharing.dto

class SplitRequestDTO {

    int type
    String secret
    int n
    int t

    SplitRequestDTO() {
    }

    SplitRequestDTO(int type, String secret, int n, int t) {
        this.type = type
        this.secret = secret
        this.n = n
        this.t = t
    }
}
