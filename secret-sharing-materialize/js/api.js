var serverUrl = "http://localhost:8080/secret-sharing";
//var serverUrl = "http://192.168.2.192:8080/secret-sharing";

function restSplitSecret(splitRequest, successFn, errorFn) {
    doAjaxPost(
        "/split",
        splitRequest,
        successFn,
        errorFn
    )
}

function restReconstructShamirSecret(shamirReconstructRequest, successFn) {
    doAjaxPost(
        "/reconstruct/shamir",
        shamirReconstructRequest,
        successFn,
        null
    )
}

function doAjaxPost(url, request, successFn, errorFn) {
    $.ajax({
        headers: {
            "Content-Type": "application/json"
        },
        url: serverUrl + url,
        type: "POST",
        data: JSON.stringify(request),
        timeout: 3000,
        success: successFn,
        error: errorFn
    });
}