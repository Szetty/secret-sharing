function getShareTemplate(i) {
    var share =
        '<div class="row" style="display: flex">'
        +   '<div class="input-field col s12">'
        +       '<textarea id="share-'+ i +'" class="grey lighten-4" style="height:50px;"></textarea>'
        +       '<label for="share-textarea" style="padding-bottom: 5px; padding-left: 2px;">'+'Share '+(i+1)+'</label>'
        +   '</div>'
        +'</div>';
    return share;
}