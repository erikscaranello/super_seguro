$(document).ready(function(){
	$('.glyphicon-warning-sign').each(function(){
		if ( $(this).parent().children('input').val() != "") {
			$(this).removeClass('glyphicon-warning-sign').addClass('glyphicon-ok');
		} else {
			$(this).parent().addClass('has-error has-feedback');
			$(this).parent().children('input').attr('onkeyup', 'validarDom(this)').attr('onblur', 'validarDom(this)');
			$(this).removeClass('glyphicon-warning-sign').addClass('glyphicon-remove');
		}
		
		
	});
	
	
	
	$('.obrigatorio').bind('keyup blur', function(){
		if($(this).val() != '') {
			
			$(this).parent().removeClass('has-error has-feedback');
			$(this).parent().children('span').removeClass('glyphicon-remove').removeClass('glyphicon-warning-sign').addClass('glyphicon-ok');
			
		} else {
			
			if (! $(this).parent().hasClass('has-error has-feedback') && ! $(this).parent().children('span').hasClass('glyphicon-remove')) {
				
				$(this).parent().addClass('has-error has-feedback');
				$(this).parent().children('span').removeClass('glyphicon-ok').removeClass('glyphicon-warning-sign');
				$(this).parent().children('span').addClass('glyphicon-remove');
			
			}
		
		}
	});
	
	$(".cpf").mask("999.999.999-99");
});

function enviarForm() {
	
	var contagem = 0;
	$('form').find('div').each(function(){
		if ($(this).hasClass('has-error')) {
			contagem ++;
		}
	});
	
	if(contagem > 0) {
	
		$('body').append('<div id="dialog" title="Dados incorretos!">'+
	  			'<p>Existem campos que n&atilde;o podem estar vazios. Por favor, verifique o formul&aacute;rio antes de enviar</p>'+
	  		'</div>');

		$( "#dialog" ).dialog({
	      modal: true,
	      buttons: {
	        Ok: function() {
	          $( this ).dialog( "close" );
	        }
	      }
	    });
		
	} else {
		
		$('form').submit();
	
	}
}



function validarDom(dom) {
	if($(dom).val() != '') {
		
		$(dom).parent().removeClass('has-error has-feedback');
		$(dom).parent().children('span').removeClass('glyphicon-remove').addClass('glyphicon-ok');
	
	} else {
		if (! $(dom).parent().hasClass('has-error has-feedback') && ! $(dom).parent().children('span').hasClass('glyphicon-remove')) {
			
			$(dom).parent().addClass('has-error has-feedback');
			$(dom).parent().children('span').removeClass('glyphicon-ok').removeClass('glyphicon-warning-sign');
			$(dom).parent().children('span').addClass('glyphicon-remove');
		
		}
	}
	
}



