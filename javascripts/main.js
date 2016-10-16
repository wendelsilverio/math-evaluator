$(document).ready(function() {
	var url = 'https://math-evaluator.herokuapp.com/health';
	$.ajax({
		type: 'GET',
	  url: url,
	  dataType: "jsonp",
	  success: function (data) {
		$('#rest-content').append(data.content);
	  }
	});
});