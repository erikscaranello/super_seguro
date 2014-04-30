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
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui-1.10.4.custom.min.css" />" />
		
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />" />
		
		<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.10.2.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.10.4.custom.min.js" />"></script>	
		<script type="text/javascript" src="<c:url value="/resources/js/main.js" />"></script>	
			
		<title>Sou Super Seguro</title>
	</head>
	<body>
	
		<header>
			<figure class="logo">
				<a href="<c:url value="/" />"><img alt="logo" src="<c:url value="/resources/images/logo_super_seguro.jpg" />"></a>
			</figure>
			
			<div class="logout">
				<a href="<c:url value="j_spring_security_logout" />" >Logout</a>
			</div>
			
			<div class="clear">
			</div>
			
			<ul class="nav nav-tabs">
  				<li id="upload_de_arquivos"><a href="<c:url value="/upload_de_arquivos" />">Fazer upload de arquivos</a></li>
  				<li id="correcao_de_arquivos"><a href="<c:url value="/alterar_dados/0" />">Inserção de arquivo manual</a></li>
<%--   				<li id="verificacao_de_boletos"><a href="<c:url value="/verificacao_de_boletos" />">Verificação de boletos</a></li> --%>
  				<li id="alterar_proprias_infos"><a href="<c:url value="/alterar_proprias_infos" />">Alterar suas informações</a></li>
  				<li id="usuarios" style="display:none;"><a href="<c:url value="/usuarios" />">Usuários</a></li>
			</ul>
			
			
		</header>
		
		<div class="fundo-branco">