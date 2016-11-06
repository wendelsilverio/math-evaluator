$(document).ready(function() {
	
	//var host = 'https://math-evaluator.herokuapp.com';
	var host = 'http://localhost:8080';
	
	$('#btnCalculate').click(function(){
		$('#rest-result').empty();
		$('#calc-error').empty();
		
		var url = host+'/me?f='+encodeURIComponent($('#txtFunction').val());
		$.ajax({
		  type: 'GET',
		  url: url,
		  success: function (data) {
			  if(data.r) {
				$('#rest-result').append(data.r);
			  } else {
				$('.error').show();
				$('#calc-error').append(data.error);
			  }
		  }
		});
	});
});
