function editField(divId){
    
    var textStr = divId + 'Text';
    var inputStr = divId + 'Input';
    $('#' + textStr).remove();
    $('#' + inputStr).attr("type","text");
    
}

function removeProfilePic(){

}
