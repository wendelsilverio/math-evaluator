$(document).ready(function() {
	$('#btnCalculate').click(function(){
		var url = 'https://math-evaluator.herokuapp.com/me?f=1+1';
		$.ajax({
		  type: 'GET',
		  url: url,
		  success: function (data) {
			$('#rest-content').append(data.status);
		  }
		});	
	});

});
