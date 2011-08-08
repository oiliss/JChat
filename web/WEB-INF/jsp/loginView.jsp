<%-- 
    Document   : loginView
    Created on : 04.07.2011, 14:24:48
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
		Для входа в чат укажите свои данные.
		<br />
		<font color="red">${login_error}</font>
		<br />
		<form name='f' action='j_spring_security_check' method='POST'>
			<table class="form">
				<tr class="form">
					<td class="form">Имя:</td>
					<td class="form"><input type="text" name="j_username" value=""></td>
				</tr>
				<tr class="form">
					<td class="form">Пароль:</td>
					<td class="form"><input type="password" name="j_password" value=""></td>
				</tr>
				<tr class="form">
					<td class="form">&nbsp;</td>
					<td class="form"><input type='checkbox' name='_spring_security_remember_me'/> Запомнить меня.</td>
				</tr>
				<tr class="form">
					<td class="form">&nbsp;</td>
					<td class="form"><input type="submit" value="Войти"></td>
				</tr>
			</table>
		</form>
		<!--
		<form name='...' action='j_spring_security_check' method='POST'>
			<input type='text' name='j_username' value=''></td>
			<input type='password' name='j_password' /></td>
		<input type="submit" value="OK" />
	</form>		
		-->

		<a href="<c:url value="/register.htm" />">Страница регистрации нового пользователя</a>
		<br/>
		<a href="<c:url value="/welcome.htm" />">Приветственная страница</a>
		<br/>
		${user_list_str}

	</body>
</html>
