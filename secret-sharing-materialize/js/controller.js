function addError(error) {
    $('#errors').append(
        "<div class='row red-text'>" + error +"</div>"
    )
}

function validateSecretComponent() {
    var typeComponent = $('#secret-type');
    var secretComponent = $('#secret');
    var pattern;
    switch(typeComponent.val()) {
        case "1":
            pattern = /^\d+$/;
            break;
        case "2":
            pattern = /^\d+(,\d+)*$/;
            break;
        case "3":
            pattern = /.+/;
            break;
        default:
            pattern = null;
            break;
    }
    if(pattern === null) {
        addError("You have to select a secret type!");
        return false;
    } else if(!pattern.test(secretComponent.val())) {
        addError("Secret not matching it's format enforced by the type!");
        return false;
    }
    return true;
}

function validateNumberComponents() {
    var n = parseInt($('#n').val());
    var t = parseInt($('#t').val());
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

function validateMainForm() {
    clearErrors();
    if(validateNumberComponents() & validateSecretComponent()) {
        alert("succes");
    }
}

$(document).ready(function() {
    init();
    $('#share-secret-button').click(validateMainForm);
});