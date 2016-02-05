class ShamirShare {

    x: number;
    value: Array<number>;

    constructor(x: number, value:Array<number>) {
        this.x = x;
        this.value = value;
    }

    public static fromJsonString(jsonString:string):ShamirShare {
        var json = JSON.parse(jsonString);
        return new ShamirShare(
            json['x'],
            json['value']
        )
    }

}