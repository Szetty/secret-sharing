var ShamirShare = (function () {
    function ShamirShare(x, value) {
        this.x = x;
        this.value = value;
    }
    ShamirShare.fromJsonString = function (jsonString) {
        var json = JSON.parse(jsonString);
        return new ShamirShare(json['x'], json['value']);
    };
    return ShamirShare;
})();
