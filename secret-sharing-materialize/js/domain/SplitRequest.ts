class SplitRequest {
    scheme: number;
    type: number;
    secret: string;
    n: number;
    t: number;

    constructor(scheme: number, type:number, secret:string, n:number, t:number) {
        this.scheme = scheme;
        this.type = type;
        this.secret = secret;
        this.n = n;
        this.t = t;
    }

}