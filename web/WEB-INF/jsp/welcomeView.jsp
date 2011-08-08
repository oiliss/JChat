<%-- 
    Document   : welcomeView
    Created on : 04.07.2011, 14:26:03
    Author     : vernet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>
	<body>
		Добро пожаловать на приветственную страницу чата!		
		<br />
		(Версия от ${version_str})
		<br />
		<a href="<c:url value="/j_spring_security_logout" />">Переход на страницу входа в чат</a>
		<br />
		Ниже приведена схема, в соответствии с которой создается чат.
		<br />
		<img src="<c:url value="/img/JChat_Model.png" />" alt="Image">
	</body>
</html>
