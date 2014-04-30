$(document).ready(function(){
	
	$.getJSON("/sou_seguro/verificar_admin", function(resultado){
		if(resultado == true) {
			$('#usuarios').css("display", "block");
		} else {
			$('#usuarios').css("display", "none");
		}
		
	});
	
	
	var url = window.location;
	var urlString = url.toString();
	var urlArray = urlString.split("/");
	
//	class="active"
	
	$("#" + urlArray[urlArray.length - 1]).addClass('active');
});


function validadorEmail(email) {
	var emailRegex = new RegExp(/^([\w\.\-]+)@([\w\-]+)((\.(\w){2,3})+)$/i);
	var valid = emailRegex.test(email);
	 if (!valid) {
	   return false;
	 } else
	   return true;
}

