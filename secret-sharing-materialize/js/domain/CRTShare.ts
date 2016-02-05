class CRTShare {

    value: Array<number>;
    modulo: number;

    constructor(value: Array<number>, modulo:number) {
        this.value = value;
        this.modulo = modulo;
    }

    public static fromJsonString(jsonString: string): CRTShare {
        var json = JSON.parse(jsonString);
        return new CRTShare(
            json['value'],
            json['modulo']
        );
    }

}