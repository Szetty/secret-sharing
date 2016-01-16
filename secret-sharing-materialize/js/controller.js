$(document).ready(function() {
    hello(function(response){
        $('#hello').text(response);
    });
});