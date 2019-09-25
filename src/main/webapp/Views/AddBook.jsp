<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<link href="/shoppingcart.jpg" rel="icon"/>
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
<title><spring:message code="lbl.shoppingcart.app.title" /></title>

</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="container">
		<div class="row">
				<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
					<h1 class="display-4">
						<spring:message code="lbl.shoppingcart.app.title" />
					</h1><br /><br />
					<div class="">
					<form:form id="productForm" action="/product/addBookForm" modelAttribute="book" cssClass="form-horizontal" role="form">
						<table >
								<tr>
									<td>Product Name</td>
									<td><form:input type="text" path="prodName"/></td>
								</tr>
								<tr>
									<td>Product Price</td>
									<td><form:input type="text" path="price"/></td>
								</tr>
								<tr>
									<td>Book Author</td>
									<td><form:input type="text" path="author"/></td>
								</tr>
								<tr>
									<td>Book Genre</td>
									<td><form:input type="text" path="genre"/></td>
								</tr>
								<tr>
									<td>Book publications</td>
									<td><form:input type="text" path="publications"/></td>
								</tr>
								<tr>
										<td colspan="2"><input type="button" class="offset-3 btn btn-lg btn-success"
							value="Save Book" id="saveBook" /></td>
									</tr>
						</table>
						</form:form>
					</div>
				</div>

		</div>

	</div>
	<%@ include file="footer.jsp"%>
</body>
<script type="text/javascript">
	$("#saveBook").click(function(){
		saveBook($(this));
	});
	function saveBook(element){
		var e = $(element);// element not element.id
		var formId = e.closest("form").prop('id');
		$('#'+formId).submit();
	}
</script>
</html>
