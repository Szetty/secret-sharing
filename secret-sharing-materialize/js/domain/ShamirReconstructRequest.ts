class ShamirReconstructRequest {
    type:number;
    shares:Array<ShamirShare>;

    constructor(type: number, shares:Array<ShamirShare>) {
        this.type = type;
        this.shares = shares;
    }
}