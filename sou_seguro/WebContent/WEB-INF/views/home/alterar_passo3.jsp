<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="../layout/header.jsp"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/home.css" />">
<script type="text/javascript" src="<c:url value="/resources/js/home.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.maskedinput-1.1.4.pack.js" />"></script>
	
	<form class="form-inline" name="formulario" method="post" action="<c:url value="/receber_alterar_dados_passo3" />">
		
		<input type="hidden" class="form-control obrigatorio" name="id" value="${arquivoRecusado.id}">
		<input type="hidden" class="form-control obrigatorio" name="valor" value="${arquivoRecusado.valor}">
		<input type="hidden" class="form-control obrigatorio" name="idPai" value="${idPai}">
		
		<div class="panel panel-success setenta-por-cento">
			<div class="panel-heading">
				<h3 class="panel-title">Dados para pagamento</h3>
			</div>
			<div class="panel-body"> 
				<div class="form-group margin-um-por-cento margin-bottom-ajuste">
				   	<p>Banco</p>
				   	<select name="nroBanco" class="form-control form-control obrigatorio">
  						<option value="${arquivoRecusado.nroBanco}">${arquivoRecusado.nroBanco}</option>
  						<option value="BRADESCO">BRADESCO</option>
  						<option value="ITAU">ITAÚ</option>
  						<option value="BANCO_DO_BRASIL">BANCO DO BRASIL</option>						
					</select>	
	
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Número da agência</p>
				   	<input type="text" class="form-control obrigatorio" name="nroAgencia" value="${arquivoRecusado.nroAgencia}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>DV da agência</p>
				   	<input type="text" class="form-control obrigatorio" name="dvAgencia" value="${arquivoRecusado.dvAgencia}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Conta corrente</p>
				   	<input type="text" class="form-control obrigatorio" name="cCorrente" value="${arquivoRecusado.cCorrente}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>DV da conta corrente</p>
				   	<input type="text" class="form-control obrigatorio" name="dvConta" value="${arquivoRecusado.dvConta}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento margin-bottom-ajuste">
				   	<p>Tipo de cobrança</p>
				   	<select name="tpCobr" class="form-control form-control obrigatorio">
  						<option value="${arquivoRecusado.tpCobr}">${arquivoRecusado.tpCobr}</option>
  						<option value="BOLETOBANCARIO_BOLETOBANCARIO">BOLETO BANCARIO E BOLETO BANCARIO</option>
  						<option value="BOLETOBANCARIO_CONTACORRENTE">BOLETO BANCARIO DEP. EM CONTA-CORRENTE</option>
 					</select>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Nome do titular da conta</p>
				   	<input type="text" class="form-control obrigatorio" name="nmTitCorrente" value="${arquivoRecusado.nmTitCorrente}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>CPF do titular da conta</p>
				   	<input type="text" class="form-control obrigatorio cpf" name="cpfTitCorrente" value="${arquivoRecusado.cpfTitCorrente}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				
				<div class="form-group margin-um-por-cento margin-bottom-ajuste">
				   	<p>Parentesco</p>
				   	<select name="cParentescoCobr" class="form-control form-control obrigatorio">
  						<option value="${arquivoRecusado.cParentescoCobr}">${arquivoRecusado.cParentescoCobr}</option>
  						<option value="PAI">PAI</option>
  						<option value="MAE">MÃE</option>
  						<option value="FILHA">FILHA</option>
  						<option value="FILHO">FILHO</option>
  						<option value="CONJUGE">CÔNJUGE</option>
  						<option value="COMPANHEIRO">COMPANHEIRO</option>
  						<option value="AGREGADO">AGREGADO</option>
  						<option value="IRMAO">IRMÃO</option>
  						<option value="IRMA">IRMÃ</option>
  						<option value="SOBRINHO">SOBRINHO</option>
  						<option value="NETO">NETO</option>
  						<option value="AVO">AVO</option>
  						<option value="TIO">TIO</option>
  						<option value="SOGRO">SOGRO</option>
  						<option value="SOGRA">SOGRA</option>
  						<option value="CUNHADO">CUNHADO</option>
  						<option value="TUTELADO">TUTELADO</option>
  						<option value="OUTRO">OUTRO</option>
					</select>			
				</div>
				
				<div class="form-group cem-por-cento para-direita">
				   	<input type="button" onclick="enviarForm()" class="form-control vinte-por-cento fundo-verde" name="enviar" value="enviar">
				</div>	
			</div>
		</div>
	</form>
<c:import url="../layout/footer.jsp"/>