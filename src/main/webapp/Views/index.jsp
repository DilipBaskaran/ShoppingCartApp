<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<link href="shoppingcart.jpg" rel="icon"/>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<title><spring:message code="lbl.assessment.propertytax" /></title>

</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="container">
		<div class="row">
			<div class="jumbotron">
				<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
					<h1 class="display-4">
						<spring:message code="lbl.assessment.propertytax" />
					</h1><br /><br />
					
						<p>
						<a href="/home">Home </a>
							<!--<a href="<c:url value='/login'/>"><spring:message
									code="lbl.assessment.form.self" /></a>
						--></p>
					
				</div>
			</div>
		</div>

	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>
