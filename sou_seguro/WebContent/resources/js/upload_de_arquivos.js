$(function() {
    $('#file_upload').uploadifive({
        'uploadScript' : '/sou_seguro/upload_de_arquivos/upload',
        'onUploadComplete' : function(file, data) {
            
        	$('.fotos-subidas section').append('<figure class="upload-fotos">'+ 
        			'<img src="file:///' + data + '" /></figure>');
        } 
    });
});