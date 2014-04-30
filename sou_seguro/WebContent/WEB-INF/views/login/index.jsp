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
			
		<title>Sou Super Seguro</title>
	</head>
	<body>
	
		<%
	 		if(request.getParameter("erro") != null) {
		%>
	
			<div class="alerta">
				<p>
					Usuário ou senha inválidos
				</p>
			</div>
		<%
			}
		%>
	
	
	
		<header>
			<figure>
				<img alt="logo" src="<c:url value="/resources/images/logo_super_seguro.jpg" />">
			</figure>
		</header>
		
		<div class="quadrado_verde">
			<form role="form" action="<c:url value="/j_spring_security_check" />" method="post">
			  <div class="form-group">
			    <label for="exampleInputEmail1">Login:</label>
			   	<input type="text" class="form-control" placeholder="login" name="j_username">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputPassword1">Senha: </label>
			    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="senha" name="j_password">
			  </div>
			  <button type="submit" class="btn btn-default">Login</button>
			</form>
			<div class="esqueceu-senha">
				<p class="chamada-clique-aqui">Esqueceu sua senha?</p>
				<p class="clique-aqui"><a href="<c:url value="/recuperacao_de_senha" />">Clique aqui</a></p>
				</div>
		</div>
		
	</body>