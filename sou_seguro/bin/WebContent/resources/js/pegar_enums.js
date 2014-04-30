$(document).ready(function(){
	$.getJSON("/sou_seguro/status", function(resultado){
		
		$(resultado).each(function(){
			var string = new String($(this)[0]);
			
			if(string == "I") {
				$('select[name="cStatus"]').append('<option value="'+ string + '">Inclus&atilde;o</option>');
			}
			
			if(string == "A") {
				$('select[name="cStatus"]').append('<option value="'+ string + '">Altera&ccedil;&atilde;o</option>');
			}
			
			if(string == "E") {
				$('select[name="cStatus"]').append('<option value="'+ string + '">Exclus&atilde;o</option>');
			}
		});
			
	});
	
	
	$.getJSON("/super_seguro/categoria", function(resultado){
		
		$(resultado).each(function(){
			$(this).each(function(){
				var string = new String($(this));
				
				$('select[name="cCategoria"]').append('<option value="'+ string + '">' + string + '</option>');
			});
			
			
		});
	});


});