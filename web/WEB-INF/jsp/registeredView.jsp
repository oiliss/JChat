<%-- 
    Document   : registeredView
    Created on : 05.07.2011, 13:39:27
    Author     : vernet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>
	<body>
		Пользователь ${registered_user} успешно зарегистрирован.<br />
		<a href="<c:url value="/j_spring_security_logout" />">Переход на страницу входа в чат</a>
	</body>
</html>
