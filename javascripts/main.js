$(document).ready(function() {
	$('#btnCalculate').click(function(){
		
	});
	var url = 'https://math-evaluator.herokuapp.com/me?f='+$('#txtFunction').val();
	$.ajax({
	  type: 'GET',
	  url: url,
	  success: function (data) {
		$('#rest-content').append(data.status);
	  }
	});
});