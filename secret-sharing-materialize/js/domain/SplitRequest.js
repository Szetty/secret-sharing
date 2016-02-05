var SplitRequest = (function () {
    function SplitRequest(scheme, type, secret, n, t) {
        this.scheme = scheme;
        this.type = type;
        this.secret = secret;
        this.n = n;
        this.t = t;
    }
    return SplitRequest;
})();
