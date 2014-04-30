<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<c:import url="../layout/header.jsp"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/usuarios.css" />">

<script type="text/javascript" src="<c:url value="/resources/js/usuarios.js" />"></script>
	
	<div class="panel panel-success quarenta-por-cento-margin-cinco float-left">
		<div class="panel-heading">
			<h3 class="panel-title">Lista de usuários que acessam o sistema</h3>
		</div>
		<div class="panel-body"> 
			<c:forEach items="${listaUsuarios}" var="usuario">
				<div class="lista-infos">
					<h4>${usuario.infosPessoais.nome} ${usuario.infosPessoais.sobrenome}</h4>
					<p class="login">login: <span class="get-login">${usuario.username}</span></p>
					<p class="logicas" onclick="alterar(this)">Alterar</p>
<!-- 					<p class="logicas" onclick="desativar(this)">Desativar</p> -->
					<p class="logicas" onclick="remover(this)">Excluir</p>
					<p class="clear"></p>
				</div>
			</c:forEach>
		</div>
	</div>
	
	<div class="panel panel-success quarenta-por-cento-margin-cinco float-left">
		<div class="panel-heading">
			<h3 class="panel-title">Inserção/alteração de novo usuário</h3>
		</div>
		<div class="panel-body"> 				
			<div class="form-group">
			   	<input type="text" class="form-control" placeholder="Nome:" name="nome">
			</div>
			<div class="form-group">
			   	<input type="text" class="form-control" placeholder="Sobrenome:" name="sobrenome">
			</div>
			<div class="form-group">
			  	<input type="email" class="form-control"  placeholder="Email:" name="email">
			</div>
			<div class="form-group">
			  	<input type="text" class="form-control"  placeholder="Nome de usuário:" name="login">
			</div>
			
			<div class="form-group">
			  	<input type="password" class="form-control"  placeholder="Senha:" name="password">
			</div>
			
			<div class="form-group">
			  	<input type="password" class="form-control"  placeholder="Confirmação de senha:" name="confirm_password">
			</div>
			
			<div class="form-group float-left quarenta-por-cento-margin-cinco">
			  	<p>Usuario: <input type="radio" class="form-control" name="role" value="ROLE_USER" checked="true"></p>
			</div>
			
			<div class="form-group float-left quarenta-por-cento-margin-cinco">
			  	<p>Administrador: <input type="radio" class="form-control" name="role" value="ROLE_ADMIN"></p>
			</div>
			<input type="hidden" name="alteracao" value="false" />
			
		  	<button type="button" name="enviar" class="btn btn-default">Enviar dados</button>
		</div>
	</div>
	
<c:import url="../layout/footer.jsp"/>