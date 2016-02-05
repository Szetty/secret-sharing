function getErrorMessage(error) {
    if(error.statusText == "timeout") {
        return "Connection to the server timed out!";
    } else {
        return error.statusText;
    }
}

function clearErrors() {
    $('#errors').html("");
}

function clearMessages() {
    $('#messages').html("");
}

function addError(error) {
    $('#errors').append(
        "<div class='row red-text text-darken-4'>" + error +"</div>"
    )
}

function addMessage(message, isError) {
    if(isError) {
        $('#messages').append(
             "<div class='col'>"
            +   "<div class='row red-text text-darken-4'>" + message +"</div>"
            +"</div>"
        )
    } else {
        $('#messages').append(
             "<div class='col green-text text-accent-4 s6 offset-s4'>"
             +  "<h3>The secret is: </h3>"
             +"</div>"
             +"<div class='col s6 offset-s3'>"
             +  "<textarea class='grey lighten-4' style='height:50px;'>"+ message +"</textarea>"
             +"</div>"
        )
    }

}

function validateSecretComponent(type, secret) {
    var pattern;
    switch(type) {
        case 1:
            pattern = /^\d+$/;
            break;
        case 2:
            pattern = /^\d+(,\d+)*$/;
            break;
        case 3:
            pattern = /.+/;
            break;
        default:
            pattern = null;
            break;
    }
    if(pattern === null) {
        addError("You have to select a secret type!");
        return false;
    } else if(!pattern.test(secret)) {
        addError("Secret not matching it's format enforced by the type!");
        return false;
    }
    return true;
}

function validateNumberComponents(n, t) {
    if(isNaN(n) || isNaN(t)) {
        addError("All the number inputs should be filled in!");
        return false;
    } else if(n <= 0) {
        addError("The number n must be higher than 0!");
        return false;
    } else if(t <= 1 || t > n) {
        addError("The number t must be higher than 0 and less than n!");
        return false;
    }
    return true;
}

function init() {
    $('select').material_select();
    $('.parallax').parallax();
}

function loadShares(n) {
    var inlineDiv = "<div style='display: flex;'>";
    $('#shares').html('');
    var icon = '<div class="material-icons">play_for_work</div>';
    var html = '';
    for (i = 0; i < n; i++) {
        if(i%10==0) {
            if(html != '') {
                html+='</div>';
            }
            html+=inlineDiv;
        }
        html += '<div id="share_'+i+'">';
        html += icon;
        if(i<9) {
            html += '<a href="'+serverUrl+'/shares/'+i+'" target="_blank">Share 0'+(i+1)+'</a>';
        } else {
            html += '<a href="'+serverUrl+'/shares/'+i+'" target="_blank">Share '+(i+1)+'</a>';
        }
        html += '</div>';
        $('#shares').append(html);
    }
    html += '</div>';
    $('#shares').html(html);
    $('#shares').show();
}

function splitSecret() {
    clearErrors();
    var scheme = parseInt($('#schemes').val());
    var type = parseInt($('#secret-type').val());
    var secret = $('#secret').val();
    var n = parseInt($('#n').val());
    var t = parseInt($('#t').val());
    if(validateNumberComponents(n, t) & validateSecretComponent(type, secret)) {
        if(scheme=='2' && t < n/2) {
            Materialize.toast('Warning: t is too small relative to n, the shares will be generated for bigger t!', 6000, 'rounded toast');
        }
         restSplitSecret(new SplitRequest(scheme, type, secret, n, t),
            function(){
                $('#shares-title').show();
                loadShares(n);
            },
            function(error) {
                addError(getErrorMessage(error));
            }
        );
    }
}

function changeNumberOfShares() {
    var html = '';
    var shareNr = parseInt($('#share-nr').val());
    for(var i = 0; i < shareNr; i++) {
        html += getShareTemplate(i);
    }
    $('#shares-reconstruct').html(html);
}

function reconstructSecret() {
    clearMessages();
    var shares = [];
    var shareNr = parseInt($('#share-nr').val());
    for(var i = 0; i < shareNr; i++) {
        var share = transformStringIntoShare($('#share-'+i).val());
        shares.push(share);
    }
    if(shares==[]){
        addMessage('You must add some shares!', true);
        return;
    }
    var scheme = $('#reconstruct-schemes').val();
    var type = parseInt($('#reconstruct-secret-type').val());
    if(type=='1' || type=='2' || type=='3') {
        if(scheme=='1') {
            restReconstructShamirSecret(new ShamirReconstructRequest(
                type,
                shares
            ), function(res) {
                addMessage(res, false);
            }, function(error) {
                addMessage(getErrorMessage(error), true);
            })
        } else if(scheme=='2') {
            restReconstructCRTSecret(new CRTReconstructRequest(
                type,
                shares
            ), function(res) {
                addMessage(res, false);
            }, function(error) {
                addMessage(getErrorMessage(error), true);
            })
        } else {
            addMessage('You must select a scheme!', true);
        }
    } else {
        addMessage('You must select the secret type!', true);
    }
}

function transformStringIntoShare(string) {
    var scheme = parseInt($('#reconstruct-schemes').val());
    switch (scheme) {
        case 1:
            return ShamirShare.fromJsonString(string);
        case 2:
            return CRTShare.fromJsonString(string);
        default:
            return ""
    }
}

$(document).ready(function() {
    init();
    $('#share-secret-button').click(splitSecret);
    $('#share-nr').change(changeNumberOfShares);
    $('#reconstruct-secret-button').click(reconstructSecret);
});