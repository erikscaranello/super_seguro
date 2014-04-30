$(document).ready(function(){
	$('#enviar-senha').click(function(){
		
		var url = window.location.toString();
		var urlArray = url.split("/");
		 
		var novaUrl = "";
		for(var i = 0; i < urlArray.length - 1; i++) {
			novaUrl = novaUrl + urlArray[i] + '/';
		}
		
		$.getJSON( novaUrl + "recuperacao_de_senha/recuperar" , { email: $('input[name="email"]').val() })
		.done(function(data){
			if(data == false) {
				
				$('body').append('<div id="dialog" title="Erro!">'+
			  			'<p>Ocorreu um erro no envio do e-mail</p>'+
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
				$('body').append('<div id="dialog" title="E-mail enviado!">'+
			  			'<p>Seu e-mail de recadastramento foi enviado. Confira sua caixa de entrada</p>'+
			  		'</div>');
				
				$( "#dialog" ).dialog({
				      modal: true,
				      buttons: {
				        Ok: function() {
				          $( this ).dialog( "close" );
				        }
				      }
				});
				
			}
			
			
		}).fail(function(erro){
			
			$('body').append('<div id="dialog" title="Erro no e-mail!">'+
		  			'<p>Ocorreu um erro interno. Por favor tente mais tarde</p>'+
		  		'</div>');
			
			$( "#dialog" ).dialog({
			      modal: true,
			      buttons: {
			        Ok: function() {
			          $( this ).dialog( "close" );
			        }
			      }
			});
			
		});
	});
	
	
	
	$('#nova-senha').click(function(){
		
		if($("input[name='confirm_password']").val() != $("input[name='password']").val()) {
			$('body').append('<div id="dialog" title="Senhas diferentes">'+
		  			'<p>As senhas n&atilde;o conferem</p>'+
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
			var url = window.location.toString();
			var urlArray = url.split("/");
			 
			var novaUrl = "";
			for(var i = 0; i < urlArray.length - 1; i++) {
				novaUrl = novaUrl + urlArray[i] + '/';
			}
			
			$.getJSON( novaUrl + "escrever_nova_senha" , { username: $('input[name="login"]').val() , senha: $('input[name="password"]').val() })
			.done(function(data){
				if(data == false) {
					
					$('body').append('<div id="dialog" title="Erro!">'+
				  			'<p>Ocorreu um erro interno. Por favor tente mais tarde</p>'+
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
					$('body').append('<div id="dialog" title="Senha cadastrada">'+
				  			'<p>Senha cadastrada com sucesso!</p>'+
				  		'</div>');
					
					$( "#dialog" ).dialog({
					      modal: true,
					      buttons: {
					        Ok: function() {
					          $( this ).dialog( "close" );
					        }
					      }
					});
					
				}
				
				
			}).fail(function(erro){
				$('body').append('<div id="dialog" title="Erro">'+
			  			'<p>N&atilde;o foi poss&iacute;vel enviar a senha! Tente de novo.</p>'+
			  		'</div>');
				
				$( "#dialog" ).dialog({
				      modal: true,
				      buttons: {
				        Ok: function() {
				          $( this ).dialog( "close" );
				        }
				      }
				});
			});
			
		}
		
	
		
	});



	$("input[name='confirm_password']").bind("keyup blur", function(){	
		verificarPasswords();
	});
	
	$("input[name='password']").bind("keyup blur", function(){		
		verificarPasswords();
	});
	
});




function verificarPasswords() {
	if($("input[name='confirm_password']").val() == $("input[name='password']").val()) {

		$("input[name='confirm_password']").css({"border-color": "#31708f", "background": "#d9edf7"});
		$("input[name='password']").css({"border-color": "#31708f", "background": "#d9edf7"});
		
	} else {
		$("input[name='confirm_password']").css({"border-color": "#a94442", "background": "#f2dede"});
		$("input[name='password']").css({"border-color": "#a94442", "background": "#f2dede"});
	}
}