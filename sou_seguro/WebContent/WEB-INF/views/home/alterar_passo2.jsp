<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="../layout/header.jsp"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/home.css" />">
<script type="text/javascript" src="<c:url value="/resources/js/home.js" />"></script>	
<script type="text/javascript" src="<c:url value="/resources/js/jquery.maskedinput-1.1.4.pack.js" />"></script>
	
	<form class="form-inline" name="formulario" method="post" action="<c:url value="/receber_alterar_dados_passo2" />">
		
		<input type="hidden" class="form-control obrigatorio" name="id" value="${arquivoRecusado.id}">
		<input type="hidden" class="form-control obrigatorio" name="idPai" value="${idPai}">
		
		<div class="panel panel-success setenta-por-cento">
			<div class="panel-heading">
				<h3 class="panel-title">Contratante responsável pelos Pagamentos</h3>
			</div>
			<div class="panel-body"> 
				<div class="form-group margin-um-por-cento">
				   	<p>Nome do responsável</p>
				   	<input type="text" class="form-control obrigatorio" name="nmCobr" value="${arquivoRecusado.nmCobr}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Data de nascimento</p>
				   	<input type="text" class="form-control obrigatorio" name="dtNascCobr" value="<fmt:formatDate pattern="dd-MM-yyyy" 
	            value="${arquivoRecusado.dtNascCobr.time}" />">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>CPF</p>
				   	<input type="text" class="form-control obrigatorio cpf" name="cpfCobr" value="${arquivoRecusado.cpfCobr}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
															
				<div class="form-group margin-um-por-cento">
				   	<p>Logradouro</p>
				   	<input type="text" class="form-control obrigatorio" name="rLogradCobr" value="${arquivoRecusado.rLogradCobr}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Número</p>
				   	<input type="text" class="form-control obrigatorio" name="rNumeroCobr" value="${arquivoRecusado.rNumeroCobr}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Complemento</p>
				   	<input type="text" class="form-control" name="compCobr" value="${arquivoRecusado.compCobr}">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Bairro</p>
				   	<input type="text" class="form-control obrigatorio" name="bairroCobr" value="${arquivoRecusado.bairroCobr}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Cidade</p>
				   	<input type="text" class="form-control obrigatorio" name="cidadeCobr" value="${arquivoRecusado.cidadeCobr}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Id cidade</p>
				   	<input type="text" class="form-control obrigatorio" name="idCidadeCobr" value="${arquivoRecusado.idCidadeCobr}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>UF</p>
				   	<input type="text" class="form-control obrigatorio" name="ufCobr" value="${arquivoRecusado.ufCobr}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>CEP</p>
				   	<input type="text" class="form-control obrigatorio" name="cepCobr" value="${arquivoRecusado.cepCobr}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>DDD</p>
				   	<input type="text" class="form-control obrigatorio" name="dFone1" value="${arquivoRecusado.dFone1}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Telefone</p>
				   	<input type="text" class="form-control obrigatorio" name="nFone1" value="${arquivoRecusado.nFone1}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Complemento do telefone</p>
				   	<input type="text" class="form-control" name="cFone1" value="${arquivoRecusado.cFone1}">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Segundo DDD</p>
				   	<input type="text" class="form-control" name="dFone2" value="${arquivoRecusado.dFone2}">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Segundo telefone</p>
				   	<input type="text" class="form-control" name="nFone2" value="${arquivoRecusado.nFone2}">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Seg. complemento do telefone</p>
				   	<input type="text" class="form-control" name="cFone2" value="${arquivoRecusado.cFone2}">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Terceiro DDD</p>
				   	<input type="text" class="form-control" name="dFone3" value="${arquivoRecusado.dFone3}">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Terceiro telefone</p>
				   	<input type="text" class="form-control" name="nFone3" value="${arquivoRecusado.nFone3}">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Terc. complemento do telefone</p>
				   	<input type="text" class="form-control" name="cFone3" value="${arquivoRecusado.cFone3}">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>E-mail</p>
				   	<input type="email" class="form-control obrigatorio" name="email" value="${arquivoRecusado.email}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Dia de vencimento</p>
				   	<input type="email" class="form-control obrigatorio" name="diaVenc" value="${arquivoRecusado.diaVenc}">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Data do início da cobrança</p>
				   	<input type="email" class="form-control obrigatorio" name="dataInicioCobr" value="<fmt:formatDate pattern="dd-MM-yyyy" 
	            value="${arquivoRecusado.dataInicioCobr.time}" />">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group cem-por-cento para-direita">
				   	<input type="button" onclick="enviarForm()" class="form-control vinte-por-cento fundo-verde" name="enviar" value="enviar">
				</div>	
			</div>
		</div>
	</form>
<c:import url="../layout/footer.jsp"/>