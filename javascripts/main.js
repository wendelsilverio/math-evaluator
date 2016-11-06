$(document).ready(function() {
	$('#btnCalculate').click(function(){
		
		$('#rest-result').empty();
		
		var url = 'https://math-evaluator.herokuapp.com/me?f='+encodeURIComponent($('#txtFunction').val());
		$.ajax({
		  type: 'GET',
		  url: url,
		  success: function (data) {
			$('#rest-result').append(data.r);
		  }
		});
	});
	
	var url = 'https://math-evaluator.herokuapp.com/health';
	$.ajax({
	  type: 'GET',
	  url: url,
	  success: function (data) {
		$('#rest-hb').append(data.status);
	  }
	});
});
