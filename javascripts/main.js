$(document).ready(function() {
	$('#btnCalculate').click(function(){
		var url = 'https://math-evaluator.herokuapp.com/me?f='+encodeURIComponent($('#txtFunction').val());
		$.ajax({
		  type: 'GET',
		  url: url,
		  success: function (data) {
			$('#rest-result').append(data);
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
=======
			$('#rest-content').append(data.status);
		  }
		});	
	});

});
>>>>>>> c337974f92a328fef52a81d41f400e00170fdbf2
