/* Javascript hack */
String.prototype.replaceAll = function(search, replacement) {
    var target = this;
    return target.split(search).join(replacement);
};

$(document).ready(function() {
	
	var host = 'https://math-evaluator.herokuapp.com';
	//var host = 'http://localhost:8080';
	
	$('#btnCalculate').click(function(){

		$('#rest-result').empty();
		$('#calc-error').empty();
		$('#result-link').empty();
		$('#result-link').attr('href', '');
		$('.rest-url').hide();

		var url = host+'/?f='+encodeURIComponent($('#txtFunction').val()).replaceAll('%26','&').replaceAll('%3D','=').replace(/ /g,'');
		$.ajax({
		  type: 'GET',
		  url: url,
		  success: function (data) {
			  $('#result-link').append(url);
			  $('#result-link').attr('href', url);
			  $('.rest-url').show();
			  if(data.result) {
				
				$('#rest-result').append(data.result);
			  } else {
				$('.error').show();
				$('#calc-error').append(data.error);
			  }
		  }     	
				
		});
	});
});
