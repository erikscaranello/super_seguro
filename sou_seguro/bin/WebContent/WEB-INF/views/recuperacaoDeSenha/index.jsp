<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
			
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap-theme.min.css" />" />
		
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui-1.10.4.custom.min.css" />" />	
		
		<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.10.2.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.10.4.custom.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/recuperacao_de_senha.js" />"></script>	
		
		<title>Sou Super Seguro</title>
	</head>
	<body>
	
		<header>
			<figure>
				<img alt="logo" src="<c:url value="/resources/images/logo_super_seguro.jpg" />">
			</figure>
		</header>
		
		<div class="quadrado_verde">
<%-- 		<form role="form" action="<c:url value="/recuperacao_de_senha/recuperar" />" method="post"> --%>
		  <div class="form-group">
		    <label for="exampleInputEmail1">Digite seu e-mail:</label>
		   	<input type="email" class="form-control" placeholder="E-mail" name="email">
		  </div>
		  <button type="submit" id="enviar-senha" class="btn btn-default">Enviar senha</button>
<!-- 		</form> -->
		</div>
		
	</body>