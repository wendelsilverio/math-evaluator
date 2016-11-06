$(document).ready(function() {
	
	//var host = 'https://math-evaluator.herokuapp.com';
	var host = 'http://localhost:8080';
	
	$('#btnCalculate').click(function(){
<<<<<<< HEAD
		$('#rest-result').empty();
		$('#calc-error').empty();
		
		var url = host+'/me?f='+encodeURIComponent($('#txtFunction').val());
=======
		
		$('#rest-result').empty();
		
		var url = 'https://math-evaluator.herokuapp.com/me?f='+encodeURIComponent($('#txtFunction').val());
>>>>>>> c6c3cdb821fe280ff6fbd360aaaf3550b1d03d0f
		$.ajax({
		  type: 'GET',
		  url: url,
		  success: function (data) {
<<<<<<< HEAD
			  if(data.r) {
				$('#rest-result').append(data.r);
			  } else {
				$('.error').show();
				$('#calc-error').append(data.error);
			  }
=======
			$('#rest-result').append(data.r);
>>>>>>> c6c3cdb821fe280ff6fbd360aaaf3550b1d03d0f
		  }
		});
	});
});
