$(document).ready(function(){
	$("input[name='login']").bind("keyup blur",function(){
		
		var input = $(this);
		
		if(input.val().length >= 3) {
			$.getJSON("/sou_seguro/usuarios/verificar_login", { login: input.val()}, function(resultado){
				if(resultado == true) {
					input.css({"border-color": "#31708f", "background": "#d9edf7"});
				} else {
					input.css({"border-color": "#a94442", "background": "#f2dede"});
				}
			});
		} 
	});
	
	$("input[name='role']").click(function(){
		var escolhido = $(this);
		
		$("input[name='role']").each(function(){
			$(this).removeAttr("id");
			$(this).checked = false;
		});
		
		$(escolhido).attr("id", "escolhido").prop("checked");
	});
	
	
	$("input[name='email']").bind("keyup blur", function(){
	
		if(validadorEmail($(this).val())) {
			
			var input = $(this);
			$.getJSON("/sou_seguro/usuarios/verificar_email", { email: input.val()}, function(resultado){
				if(resultado == true) {
					input.css({"border-color": "#31708f", "background": "#d9edf7"});
				} else {
					$.getJSON("/sou_seguro/usuarios/verificar_login", { login: $("input[name='login']").val()}, function(retorno){
						if(retorno == false) {
							input.css({"border-color": "#31708f", "background": "#d9edf7"});
						} else {
							input.css({"border-color": "#a94442", "background": "#f2dede"});
						}
					}).fail(function(){
						input.css({"border-color": "#a94442", "background": "#f2dede"});
					});	
				}
			});
		} else {
			$(this).css({"border-color": "#a94442", "background": "#f2dede"});
		}
		
	});
	
	
	$("input[name='confirm_password']").bind("keyup blur", function(){	
		verificarPasswords();
	});
	
	$("input[name='password']").bind("keyup blur", function(){		
		verificarPasswords();
	});
	
	
	$("button[name='enviar']").click(function(){ 
		
		if($("input[name='nome']").val() == '' || $("input[name='nome']").val() == null || 
				$("input[name='sobrenome']").val() == '' || $("input[name='sobrenome']").val() == null ||
				$("input[name='email']").val() == '' || $("input[name='email']").val() == null ||
				$("input[name='login']").val() == '' || $("input[name='login']").val() == null ||
				$("input[name='password']").val() == '' || $("input[name='password']").val() == null) {

			
			$('body').append('<div id="dialog" title="Campos Vazios">'+
					  			'<p>Todos os campos devem ser preenchidos</p>'+
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
				
				var nome = $("input[name='nome']").val();
				var sobrenome = $("input[name='sobrenome']").val();
				var email = $("input[name='email']").val();
				var login = $("input[name='login']").val();
				var password = $("input[name='password']").val();
				var role = $("#escolhido").val();
				
				$.getJSON("/sou_seguro/usuarios/novo_usuario", { nome:nome, sobrenome : sobrenome,
					email : email, username : login, password: password, role: role}, function(retorno){
						if(retorno == true) {
							$('body').append('<div id="dialog" title="Dados inseridos com sucesso!">'+
						  			'<p>Os dados foram inseridos com sucesso</p>'+
						  		'</div>');
				
							$( "#dialog" ).dialog({
						      modal: true,
						      buttons: {
						        Ok: function() {
						          $( this ).dialog( "close" );
						          location.reload();
						        }
						      }
						    });
						} else {
							$('body').append('<div id="dialog" title="Dados incorretos!">'+
						  			'<p>Os dados n&atilde;o foram inseridos corretamente. Por favor tente novamente.</p>'+
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
					});
				
			} 		
		}
		 
	});
	
});


function alterar(dom) {
	var login = $(dom).parent().children(".login").children(".get-login").text();
	
	$.getJSON("/sou_seguro/usuarios/alterar_usuario", { username : login }, function(retorno){
		$("input[name='nome']").val(retorno.infosPessoais.nome);
		$("input[name='sobrenome']").val(retorno.infosPessoais.sobrenome);
		$("input[name='email']").val(retorno.infosPessoais.email);
		
		$("input[name='login']").val(retorno.username).attr("disabled", true);
		$("input[name='password']").val(retorno.password).attr("disabled", true);
		$("input[name='confirm_password']").val(retorno.password).attr("disabled", true);	
		
		$("input[name='alteracao']").val("true");
		
		$("input[name='role']").each(function(){
			$(this).removeAttr("id");
			$(this).checked = false;
		});
		
		$("input[name='role']").val(retorno.role.authority).attr("id", "escolhido").prop("checked");
		
	});

}


function remover (dom) {
	var nome = $(dom).parent().children("h4").text();
	var login = $(dom).parent().children(".login").children(".get-login").text();
	
	$('body').append('<div id="dialog-confirm" title="Excluir '+ login +'?">'+
	  '<p>Essa a&ccedil;&atilde;o excluir&aacute; permanentemente os registro de ' + nome + '. Voc&ecirc; deseja continuar?</p></div>');
	
	$( "#dialog-confirm" ).dialog({
	      resizable: true,
	      modal: true,
	      buttons: {
	        "Excluir registro": function() {
	        	$.getJSON("/sou_seguro/usuarios/excluir_usuario", { username : login }, function(retorno){
						if(retorno == true) {
							$("#dialog-confirm").dialog( "close" );
							
							$('body').append('<div id="dialog-message" title="Registro exclu&iacute;do">'+
									  '<p>Seu registro foi exclu&iacute;do com sucesso!</p></div>');
									
							$( "#dialog-message" ).dialog({
								resizable: true,  
								modal: true,
							    buttons: {
							    	Ok: function() {
							          $( this ).dialog( "close" );
							          location.reload();
							    	}
							      }
							    });
						} else {
							$("#dialog-confirm").dialog( "close" );

							$('body').append('<div id="dialog-message" title="Registro n&atilde;o exclu&iacute;do">'+
							  '<p>N&atilde;o foi poss&iacute;vel excluir o registro!</p></div>');
							
							$( "#dialog-message" ).dialog({
								resizable: true,  
								modal: true,
							    buttons: {
							    	Ok: function() {
							          $( this ).dialog( "close" );
							          location.reload();
							    	}
							      }
							});
						}
	        	});
	        },
	        "Cancelar": function() {
	          $( this ).dialog( "close" );
	        }
	      }
	    });
	
	
}


function verificarPasswords() {
	 if($("input[name='confirm_password']").val() == $("input[name='password']").val()) {
	 
		 $("input[name='confirm_password']").css({"border-color": "#31708f", "background": "#d9edf7"});
		 $("input[name='password']").css({"border-color": "#31708f", "background": "#d9edf7"});
	
	 } else {
		 $("input[name='confirm_password']").css({"border-color": "#a94442", "background": "#f2dede"});
		 $("input[name='password']").css({"border-color": "#a94442", "background": "#f2dede"});
	 }
}

