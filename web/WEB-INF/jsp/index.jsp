<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Welcome to Spring Web MVC project</title>
	</head>

	<body>
		<h1>На эту страницу мы не должны попадать</h1>
		<h1>Если же это произошло, срочно покиньте страницу</h1>
		<br />
		<a href="<c:url value="/j_spring_security_logout" />">Выход из чата</a>
	</body>
</html>
