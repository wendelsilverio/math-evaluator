$(document).ready(function() {
    $.ajax({
        url: "https://math-evaluator.herokuapp.com/health"
    }).then(function(data) {
       $('#rest-content').append(data.content);
    });
});
