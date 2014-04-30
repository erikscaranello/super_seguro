<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="../layout/header.jsp"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/home.css" />">
<script type="text/javascript" src="<c:url value="/resources/js/usuarios.js" />"></script>	
	
	<div class="panel panel-success setenta-por-cento	">
		<div class="panel-heading">
			<h3 class="panel-title">Alteração de seus dados</h3>
		</div>
		
		<input type="hidden" class="form-control" name="role" value="">
		
		<div class="panel-body"> 				
			<div class="form-group">
			   	<input type="text" class="form-control" placeholder="Nome:" name="nome" value="${usuarioLogado.infosPessoais.nome}">
			</div>
			<div class="form-group">
			   	<input type="text" class="form-control" placeholder="Sobrenome:" name="sobrenome" value="${usuarioLogado.infosPessoais.sobrenome}">
			</div>
			<div class="form-group">
			  	<input type="email" class="form-control"  placeholder="Email:" name="email" value="${usuarioLogado.infosPessoais.email}">
			</div>
			<div class="form-group">
			  	<input type="text" class="form-control"  placeholder="Nome de usuário:" name="login" value="${usuarioLogado.username}" disabled="true">
			</div>
			
			<div class="form-group">
			  	<input type="password" class="form-control"  placeholder="Senha:" value="" name="password">
			</div>
			
			<div class="form-group">
			  	<input type="password" class="form-control"  placeholder="Confirmação de senha:" value="" name="confirm_password">
			</div>
					
		  	<button type="button" name="enviar" class="btn btn-default">Enviar dados</button>
		</div>
	</div>
	
	
<c:import url="../layout/footer.jsp"/>	