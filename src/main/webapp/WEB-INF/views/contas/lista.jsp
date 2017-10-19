<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/7.0.0/normalize.min.css">
<title>Lista de Contas</title>
</head>
<style>
.container>.card-columns {
	padding-top: 20px;
}

.card-columns {
  @include media-breakpoint-only(lg) {
    column-count: 4;
  }
  @include media-breakpoint-only(xl) {
    column-count: 5;
  }
}

</style>
<body>
	<nav class="navbar navbar-expand-sm bg-light navbar-light"> <a
		href="/" class="navbar-brand">light</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbar4">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="navbar-collapse collapse" id="navbar4">
		<ul class="navbar-nav">
			<li class="nav-item active"><a class="nav-link" href="#">Link
					<span class="sr-only">Home</span>
			</a></li>
			<li class="nav-item"><a class="nav-link" href="//codeply.com">Codeply</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
		</ul>
	</div>
	</nav>


	<div class="container">
<!-- 		<div class="card-columns"> -->
<%-- 			<c:forEach items='${contas}' var='_v'> --%>
<!-- 				<div class="card border-danger "> -->
<!-- 					<div class="card-header">${_v.nome}</div> -->
<!-- 					<div class="card-body "> -->
<%-- 						<h4 class="card-title">${_v.nome}</h4> --%>
<%-- 						<p class="card-text">${_v.descricao}</p> --%>
<!-- 						<p class="card-text"> -->
<%-- 							<fmt:formatDate pattern="dd/MM/yyyy" --%>
<%-- 								value="${_v.dataVencimento.time}" /> --%>
<!-- 						</p> -->
<!-- 						<p class="card-text"> -->
<%-- 							<fmt:formatNumber value="${_v.valor}" type="currency" /> --%>
<!-- 						</p> -->
<!-- 					</div> -->
<!-- 					<div class="card-footer border-danger"> -->
<!-- 						<a href="#" class="card-link text-danger">Efetuar</a>  -->
<!-- 						<a -->
<%-- 							href="${_v.id}" class="card-link text-danger">Editar</a> --%>
<%-- 						<form action="<c:url value='/conta/excluir/${_v.id}'/>" --%>
<!-- 							method="DELETE"> -->
<!-- 							<input class="btn-link" type="submit" value="teste" /> -->
<!-- 						</form> -->

<!-- 					</div> -->

<!-- 				</div> -->
<%-- 			</c:forEach> --%>
<!-- 		</div> -->

	<div class="card-columns">
		<div class="card" style="width: 10rem;">
			<div class="card-body">
				<h4 class="card-title">Card title</h4>
				<h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
				<p class="card-text">Some quick example text to build on the
					card title and make up the bulk of the card's content.</p>
				<a href="#" class="card-link">Card link</a> <a href="#"
					class="card-link">Another link</a>
			</div>
		</div>
		<div class="card" style="width: 10rem;">
			<div class="card-body">
				<h4 class="card-title">Card title</h4>
				<h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
				<p class="card-text">Some quick example text to build on the
					card title and make up the bulk of the card's content.</p>
				<a href="#" class="card-link">Card link</a> <a href="#"
					class="card-link">Another link</a>
			</div>
		</div>
		<div class="card" style="width: 10rem;">
			<div class="card-body">
				<h4 class="card-title">Card title</h4>
				<h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
				<p class="card-text">Some quick example text to build on the
					card title and make up the bulk of the card's content.</p>
				<a href="#" class="card-link">Card link</a> <a href="#"
					class="card-link">Another link</a>
			</div>
		</div>
		<div class="card" style="width: 10rem;">
			<div class="card-body">
				<h4 class="card-title">Card title</h4>
				<h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
				<p class="card-text">Some quick example text to build on the
					card title and make up the bulk of the card's content.</p>
				<a href="#" class="card-link">Card link</a> <a href="#"
					class="card-link">Another link</a>
			</div>
		</div>
		<div class="card" style="width: 10rem;">
			<div class="card-body">
				<h4 class="card-title">Card title</h4>
				<h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
				<p class="card-text">Some quick example text to build on the
					card title and make up the bulk of the card's content.</p>
				<a href="#" class="card-link">Card link</a> <a href="#"
					class="card-link">Another link</a>
			</div>
		</div>
		<div class="card" style="width: 10rem;">
			<div class="card-body">
				<h4 class="card-title">Card title</h4>
				<h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
				<p class="card-text">Some quick example text to build on the
					card title and make up the bulk of the card's content.</p>
				<a href="#" class="card-link">Card link</a> <a href="#"
					class="card-link">Another link</a>
			</div>
		</div>
	</div>
	</div>
</body>
</html>
