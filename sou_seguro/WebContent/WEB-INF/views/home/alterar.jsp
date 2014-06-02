<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:import url="../layout/header.jsp"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/home.css" />">
<script type="text/javascript" src="<c:url value="/resources/js/home.js" />"></script>
<%-- <script type="text/javascript" src="<c:url value="/resources/js/pegar_enums.js" />"></script>	 --%>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.maskedinput-1.1.4.pack.js" />"></script>
	
	<form class="form-inline" name="formulario" method="post" action="<c:url value="/receber_alterar_dados" />">
	
		<div class="panel panel-success setenta-por-cento">
			<div class="panel-heading">
				<h3 class="panel-title">Movimentação de Associados</h3>
			</div>
			<div class="panel-body"> 
<!-- 				<div class="form-group margin-um-por-cento"> -->
<!-- 				   	<p>Contrato</p> -->
<%-- 				   	<input type="text" class="form-control obrigatorio" name="contrato" value="${arquivoRecusado.contrato}"> --%>
<!-- 					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span> -->
<!-- 				</div> -->
				
				<input type="hidden" class="form-control obrigatorio" name="id" value="${arquivoRecusado.id}">
				<input type="hidden" class="form-control obrigatorio" name="recebidoBradesco" value="${arquivoRecusado.recebidoBradesco}">
				<input type="text" class="form-control obrigatorio" name="nroProposta" value="${arquivoRecusado.nroProposta}">
					
				<div class="form-group margin-um-por-cento margin-bottom-ajuste">
				   	<p>Status</p>
					<select name="cStatus" class="form-control form-control obrigatorio">
  						<option value="${arquivoRecusado.cStatus}">${arquivoRecusado.cStatus}</option>
  						
  						<option value="I">Inclus&atilde;o</option>
  						<option value="A">Altera&ccedil;&atilde;o</option>
  						<option value="E">Exclus&atilde;o</option>
					</select>				 
				</div>
				
				<div class="form-group margin-um-por-cento margin-bottom-ajuste">
				   	<p>Categoria</p>
				   	<select name="cCategoria" class="form-control form-control obrigatorio">
  						<option value="${arquivoRecusado.cCategoria}">${arquivoRecusado.cCategoria}</option>
  						<option value="TITULAR">TITULAR</option>
  						<option value="DEPENDENTES">DEPENDENTES</option>
					</select>
				</div>
				
<!-- 				<div class="form-group margin-um-por-cento"> -->
<!-- 				   	<p>Número da proposta</p> -->
<%-- 				   	<input type="text" class="form-control obrigatorio" name="nroProposta" value="${arquivoRecusado.nroProposta}"> --%>
<!-- 					<span class="glyphicon glyphicon-ok form-control-feedback"></span> -->
<!-- 				</div> -->
				
<!-- 				<div class="form-group margin-um-por-cento"> -->
<!-- 				   	<p>Matrícula</p> -->
<%-- 				   	<input type="text" class="form-control obrigatorio" name="cdMatricula" value="${arquivoRecusado.cdMatricula}"> --%>
<!-- 					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span> -->
<!-- 				</div> -->
				
				<div class="form-group margin-um-por-cento margin-bottom-ajuste">
				   	<p>Parentesco</p>
				   	<select name="cParentesco" class="form-control form-control obrigatorio">
  						<option value="${arquivoRecusado.cParentesco}">${arquivoRecusado.cParentesco}</option>
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
				
				<div class="form-group margin-um-por-cento">
				   	<p>Nome</p>
				   	<input type="text" class="form-control obrigatorio" name="nome" value="${arquivoRecusado.nome}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Data de Nascimento</p>
				   	<input type="text" class="form-control obrigatorio" name="dtNascimento" value="<fmt:formatDate pattern="dd-MM-yyyy" 
	            value="${arquivoRecusado.dtNascimento.time}" />">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento margin-bottom-ajuste">
				   	<p>Sexo</p>
				   	<select name="cSexo" class="form-control form-control obrigatorio">
  						<option value="${arquivoRecusado.cSexo}">${arquivoRecusado.cSexo}</option>
  						<option value="Masculino">Masculino</option>
  						<option value="Feminino">Feminino</option>
					</select>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>CPF</p>
				   	<input type="text" class="form-control obrigatorio cpf" name="cpf" value="${arquivoRecusado.cpf}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>PIS/PASEP/NIT</p>
				   	<input type="text" class="form-control" name="pisPasepNit" value="${arquivoRecusado.pisPasepNit}">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>CNS</p>
				   	<input type="text" class="form-control" name="cns" value="${arquivoRecusado.cns}">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Nome da mãe</p>
				   	<input type="text" class="form-control obrigatorio" name="nomeMae" value="${arquivoRecusado.nomeMae}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>DNV</p>
				   	<input type="text" class="form-control" name="dnv" value="${arquivoRecusado.dnv}">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Estado civil</p>
				   	<select name="cEstCivil" class="form-control form-control obrigatorio">
  						<option value="${arquivoRecusado.cEstCivil}">${arquivoRecusado.cEstCivil}</option>
  						<option value="CASADO">CASADO</option>
  						<option value="DESQUITADO">DESQUITADO</option>
  						<option value="DIVORCIADO">DIVORCIADO</option>
  						<option value="SEPARADO">SEPARADO</option>
  						<option value="SOLTEIRO">SOLTEIRO</option>
  						<option value="VIUVO">VIÚVO</option>
					</select>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Logradouro</p>
				   	<input type="text" class="form-control obrigatorio" name="rLogradores" value="${arquivoRecusado.rLogradores}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Número</p>
				   	<input type="text" class="form-control obrigatorio" name="rNumeroRes" value="${arquivoRecusado.rNumeroRes}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Complemento</p>
				   	<input type="text" class="form-control" name="compRes" value="${arquivoRecusado.compRes}">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Bairro</p>
				   	<input type="text" class="form-control obrigatorio" name="bairroRes" value="${arquivoRecusado.bairroRes}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Cidade</p>
				   	<input type="text" class="form-control obrigatorio" name="cidadeRes" value="${arquivoRecusado.cidadeRes}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Id cidade</p>
				   	<input type="text" class="form-control obrigatorio" name="idCidadeRes" value="${arquivoRecusado.idCidadeRes}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>UF</p>
				   	<input type="text" class="form-control obrigatorio" name="ufRes" value="${arquivoRecusado.ufRes}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>CEP</p>
				   	<input type="text" class="form-control obrigatorio" name="cepRes" value="${arquivoRecusado.cepRes}">
					<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Referência</p>
				   	<input type="text" class="form-control" name="referenciaRes" value="${arquivoRecusado.referenciaRes}">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
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
				   	<p>Plano</p>
				   	<input type="text" class="form-control" name="cPlano" value="${arquivoRecusado.cPlano}">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Data da adesão</p>
				   	<input type="text" class="form-control" name="dtAdesao" value="<fmt:formatDate pattern="dd-MM-yyyy" 
	            value="${arquivoRecusado.dtAdesao.time}" />">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Data do cancelamento</p>
				   	<input type="text" class="form-control" name="dtCancelamento" value="<fmt:formatDate pattern="dd-MM-yyyy" 
	            value="${arquivoRecusado.dtCancelamento.time}" />">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				
				<div class="form-group margin-um-por-cento">
				   	<p>Motivo do cancelamento</p>
				   	<input type="text" class="form-control" name="cMotivoCan" value="${arquivoRecusado.cMotivoCan}">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				
				<div class="form-group cem-por-cento para-direita">
				   	<input type="button" onclick="enviarForm()" class="form-control vinte-por-cento fundo-verde" name="enviar" value="enviar">
				</div>	
			</div>
		</div>
	</form>
<c:import url="../layout/footer.jsp"/>	