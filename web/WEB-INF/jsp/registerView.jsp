<%-- 
    Document   : registerView
    Created on : 05.07.2011, 11:20:27
    Author     : vernet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>
	<body>
		<h1>Новый пользователь</h1>
		<spring:nestedPath path="register">
			<form action="" method="post">
        Имя:
        <spring:bind path="name">
					<input type="text" name="${status.expression}" value="${status.value}" />
        </spring:bind>
        Пароль:
        <spring:bind path="password">
					<input type="password" name="${status.expression}" value="${status.value}" />
        </spring:bind>
        <input type="submit" value="OK" />
			</form>
		</spring:nestedPath>
				${user_list_str}
	</body>
</html>
