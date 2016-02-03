//var serverUrl = "http://localhost:8080/api";
var serverUrl = "http://192.168.2.192:8080/secret-sharing";

function getShares(splitRequest) {
    $.ajax({
        headers: {
            "Content-Type": "application/json"
        },
        url: serverUrl + "/split",
        type: "POST",
        data: JSON.stringify(splitRequest),
        success: function(res) {
            alert("Success");
        }
    });
}