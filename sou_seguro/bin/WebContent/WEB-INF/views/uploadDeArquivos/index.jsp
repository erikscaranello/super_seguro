<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<c:import url="../layout/header.jsp"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/upload_de_arquivos.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/uploader/uploadifive.css" />" />

<script src="<c:url value="/resources/js/uploader/jquery.uploadifive.js" />" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/resources/js/upload_de_arquivos.js" />"></script>
	
	<div class="panel panel-success quarenta-por-cento-margin-cinco">
		<div class="panel-heading">
			<h3 class="panel-title">Upload de arquivos</h3>
		</div>
		<div class="panel-body"> 
			<input id="file_upload" type="file" name="file_upload" />
		</div>
	</div>
	
<c:import url="../layout/footer.jsp"/>	