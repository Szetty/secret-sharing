$(document).ready(function() {
    $('select').material_select();
    $('.parallax').parallax();
    hello(function(response){
        $('#hello').text(response);
    });
});