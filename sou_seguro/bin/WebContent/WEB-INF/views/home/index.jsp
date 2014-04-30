<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="../layout/header.jsp" />

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/home.css" />">
<script type="text/javascript"
	src="<c:url value="/resources/js/home.js" />"></script>

<div
	class="panel panel-warning quarenta-por-cento-margin-cinco float-left">
	<div class="panel-heading">
		<h3 class="panel-title">Dados recusados do sistema</h3>
	</div>
	<div class="panel-body">
		<c:forEach items="${listaRecusada}" var="recusado">
			<a href="<c:url value="alterar_dados/${recusado.id}" />">
				<p class="clicar-para-alterar">${recusado.nome}-
					${recusado.cSexo} - ${recusado.cidadeRes}</p>
			</a>
		</c:forEach>
	</div>
</div>

<div
	class="panel panel-danger quarenta-por-cento-margin-cinco float-left">
	<div class="panel-heading">
		<h3 class="panel-title">Dados recusados do bradesco</h3>
	</div>
	<div class="panel-body">
		<c:forEach items="${listaRecusadaBradesco}" var="recusadoBradesco">
			<a href="<c:url value="alterar_dados/${recusadoBradesco.id}" />">
				<p class="clicar-para-alterar">${recusadoBradesco.nome}-
					${recusadoBradesco.cSexo} - ${recusadoBradesco.cidadeRes}</p>
			</a>
		</c:forEach>
	</div>
</div>

<c:import url="../layout/footer.jsp" />
