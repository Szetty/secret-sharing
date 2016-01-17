class SplitRequest {
    type: number;
    secret: string;
    n: number;
    t: number;

    constructor(type:number, secret:string, n:number, t:number) {
        this.type = type;
        this.secret = secret;
        this.n = n;
        this.t = t;
    }

}