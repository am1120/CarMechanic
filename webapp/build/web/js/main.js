function editField(divId) {

    var textStr = divId + 'Text';
    var inputStr = divId + 'Input';
    $('#' + textStr).remove();
    $('#' + inputStr).attr("type", "text");

}

function removeProfilePic() {

}

function fetchModels(value) {
    var requestParams = 'UpdateSearchPageServlet?fetch=models&maker_id=' + value;
    $.get(requestParams, function (response) {
        
        $('#modelSelect').empty();
        $('#modelSelect').append("<option id=\"modelTip\" value=\"%:%\">Επιλέξτε μοντέλο</option>");
        $('#modelSelect').append(response);
        $('#yearSelect').empty();
        $('#yearSelect').append("<option id=\"yearTip\" value=\"%:%\">Επιλέξτε μοντέλο</option>");
        $('#engineSelect').empty();
        $('#engineSelect').append("<option id=\"engineTip\" value=\"%:%\">Επιλέξτε μοντέλο</option>");
       
       
    });
}

function fetchYears(value) {
    requestParams = 'UpdateSearchPageServlet?fetch=years&model_id=' + value;
    $.get(requestParams, function (response) {
        $('#yearSelect').empty();
        $('#yearSelect').append("<option id=\"yearTip\" value=\"%:%\">Επιλέξτε έτος</option>");
        $('#yearSelect').append(response);
        $('#engineSelect').empty();
        $('#engineSelect').append("<option id=\"engineTip\" value=\"%:%\">Επιλέξτε έτος</option>");
    });
}

function fetchEngine(value) {
    requestParams = 'UpdateSearchPageServlet?fetch=engine&model_id=' + value;
    $.get(requestParams, function (response) {
       $('#engineTip').empty();
       $('#engineTip').append("<option id=\"engineTip\" value=\"%:%\">Επιλέξτε κινητήρα</option>");
        $('#engineSelect').append(response);
    });
}

function openSolution(){
    console.log("test");
    $('#solutionArea').empty();
    $('#solutionArea').append("<textarea id=\"solutionText\" name=\"solutiontext\" class=\"form-control\" rows=\"4\"></textarea>");
    $('#solutionArea').append("<button onclick=\"addSolution()\" type=\"button\" class=\"btn btn-default\">Προσθήκη Λύσης</button>");
    
}

function addSolution(){
    
    var solutionText = $('#solutionText').val();
    var problemId = $('#p_id').val();
    console.log(solutionText + problemId);
    
    $.post('AddSolution?solution='+ solutionText + '&p_id=' + $('#p_id').val() ,function(response){
        console.log("Result of adding solution: " +response);
         $('#solutionArea').empty();
         $('#solutionArea').append("<p class=\"bg-info\"> Result of adding solution: " + response + "</p>");
                 $('#solutionArea').append("<p class=\"bg-warning\">" + solutionText + "</p>");
                                    
    });
    
    
}

