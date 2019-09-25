<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
			<table class="table table-hover table-striped">
				<thead>
					<td><spring:message code="lbl.shoppingcart.product.name" /></td>
					<td><spring:message code="lbl.shoppingcart.product.price" /></td>
					<td><spring:message code="lbl.shoppingcart.product.quantity" /></td>
					<td></td>
				</thead>
				<tbody>
					<c:forEach var="cartDetail" items="${cartDetails}">
						<c:url var="addLink" value="/cart/add">
							<c:param name="productId" value="${cartDetail.product.product_id}" />
						</c:url>
						<c:url var="reduceLink" value="/cart/reduce">
							<c:param name="productId" value="${cartDetail.product.product_id}" />
						</c:url>
						<c:url var="removeLink" value="/cart/remove">
							<c:param name="productId" value="${cartDetail.product.product_id}" />
						</c:url>
						
						<tr>
							<td>${cartDetail.product.prodName}</td>
							<td>${cartDetail.product.price}</td>
							<td>
								<a class=danger" href="${reduceLink}"><img width="20px" height="20px" src="/minus.png"/></a>
							 	<input type="text" size="1" readonly value="${cartDetail.quantity}" />
							 	<a class="success" href="${addLink}"><img width="20px" height="20px" src="/plus.png"/></a>
							</td>
							<td><a class=danger" href="${removeLink}"><img width="20px" height="20px" src="/remove.png"/></a></td>
						</tr>
					</c:forEach>
					<c:if test="${empty cartDetails}">
						<tr>
							<td colspan=4><spring:message code="lbl.shoppingcart.cart.cartempty" />!!! <a href="/home"><spring:message code="lbl.shoppingcart.cart.buyitems" /></a></td>
						</tr>
					</c:if>
					<c:if test="${not empty cartDetails}">
						<tr class="primary">
							<td colspan=1><spring:message code="lbl.shoppingcart.cart.cartamount" /></a></td>
							<td colspan=1>${cartAmount}</td>
							<td colspan=2><a class="btn btn-primary" href=""><spring:message code="lbl.shoppingcart.cart.buynow" /></a></td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>

	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>
