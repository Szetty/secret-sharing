var serverUrl = "http://localhost:8080/api";

function hello(success) {
    $.ajax({
        url: serverUrl + "/hello" ,
        type: "GET",
        success: success
    });
}