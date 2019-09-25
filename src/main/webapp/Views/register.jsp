<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
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
<title><spring:message code="lbl.shoppingcart.app.title" /></title>

  </head>

  <body>

<%@ include file="header.jsp"%>
    <div class="container">

        <form:form method="POST" modelAttribute="user" class="form-signin">
            <h2 class="form-signin-heading">Create your account</h2>
            <spring:bind path="name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="name" class="form-control" placeholder="Name"
                                autofocus="true"></form:input>
                    <form:errors class="text-danger" path="name"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="address">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="address" class="form-control" placeholder="Address"
                                autofocus="true"></form:input>
                    <form:errors class="text-danger" path="address"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="email">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="email" class="form-control" placeholder="Email"
                                autofocus="true"></form:input>
                    <form:errors class="text-danger" path="email"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="phoneno">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="phoneno" class="form-control" placeholder="Phone Number"
                                autofocus="true"></form:input>
                    <form:errors class="text-danger" path="phoneno"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="user_name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="user_name" class="form-control" placeholder="User Name"
                                autofocus="true"></form:input>
                    <form:errors class="text-danger" path="user_name"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                    <form:errors class="text-danger" path="password"></form:errors>
                </div>
            </spring:bind>

  
            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </form:form>

    </div>
<%@ include file="footer.jsp"%>

  </body>
</html>