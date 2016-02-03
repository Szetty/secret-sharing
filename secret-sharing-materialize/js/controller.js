function addError(error) {
    $('#errors').append(
        "<div class='row red-text text-darken-4'>" + error +"</div>"
    )
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
    } else if(t <= 0 || t > n) {
        addError("The number t must be higher than 0 and less than n!");
        return false;
    }
    return true;
}

function clearErrors() {
    $('#errors').html("");
}

function init() {
    $('select').material_select();
    $('.parallax').parallax();
}

function loadShares(n) {
    var inlineDiv = "<div style='display: flex;'>"
    $('#shares').html('');
    var icon = '<div class="material-icons">play_for_work</div>';
    var html = '';
    for (i = 0; i < n; i++) {
        if(i%7==0) {
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
    html += '</div>'
    $('#shares').html(html)
    $('#shares').show();
}

function validateMainForm() {
    clearErrors();
    var type = parseInt($('#secret-type').val());
    var secret = $('#secret').val();
    var n = parseInt($('#n').val());
    var t = parseInt($('#t').val());
    if(validateNumberComponents(n, t) & validateSecretComponent(type, secret)) {
        loadShares(n);
        $('#shares-title').show();
        getShares(new SplitRequest(type, secret, n, t));
    }
}

$(document).ready(function() {
    init();
    $('#share-secret-button').click(validateMainForm);
});