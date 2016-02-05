var CRTShare = (function () {
    function CRTShare(value, modulo) {
        this.value = value;
        this.modulo = modulo;
    }
    CRTShare.fromJsonString = function (jsonString) {
        var json = JSON.parse(jsonString);
        return new CRTShare(json['value'], json['modulo']);
    };
    return CRTShare;
})();
