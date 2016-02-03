var serverUrl = "http://localhost:8080/api";
//var serverUrl = "http://192.168.2.193:8080/api";

function hello(success) {
    $.ajax({
        url: serverUrl + "/hello" ,
        type: "GET",
        success: success
    });
}

function getShares(splitRequest) {
    $.ajax({
        headers: {
            "Content-Type": "application/json"
        },
        url: serverUrl + "/split",
        type: "POST",
        data: JSON.stringify(splitRequest),
        success: function(res) {
            alert(res);
        }
    });
}