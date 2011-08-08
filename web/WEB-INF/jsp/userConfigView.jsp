<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Welcome to Spring Web MVC project</title>
		<link rel="stylesheet" type="text/css" href="css/userConfig.css">
	</head>

	<body>
		<p>Личные параметры</p>
		${changes_saved}
		${info}
		${session_content}
		${request_content}
		<spring:nestedPath path="user">
			<table class="user_config_table">
				<form action="" method="post">
					<tr>
						<td>Имя:</td>
						<td>
							<spring:bind path="name">
								<input type="text" name="${status.expression}" value="${status.value}" />
							</spring:bind>
						</td>
					</tr>
					<tr>
						<td>Пароль:</td>
						<td>
							<spring:bind path="password">
								<input type="password" name="${status.expression}" value="${status.value}" />
							</spring:bind>
						</td>
					</tr>
					<tr>
						<td>Количество сообщений на странице:</td>
						<td>
							<spring:bind path="messagesOnPage">
								<input type="text" name="${status.expression}" value="${status.value}" />
							</spring:bind>
						</td>
					</tr>
					<tr>
						<td>Количество альбомов на странице:</td>
						<td>
							<spring:bind path="albumsOnPage">
								<input type="text" name="${status.expression}" value="${status.value}" />
							</spring:bind>
						</td>
					</tr>
					<tr>
						<td>Количество фотографий на странице:</td>
						<td>
							<spring:bind path="photosOnPage">
								<input type="text" name="${status.expression}" value="${status.value}" />
							</spring:bind>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>
							<input type="submit" value="Сохранить изменения" />
						</td>
					</tr>
				</form>
				<form action="" method="post" enctype="multipart/form-data">
					<tr>
						<td><img src="${avatar}" /></td>
						<td>
							Загрузить аватар:<br />
							<input type="file" name="avatar" size="50"><br />
							<input type="submit" value="OK" />
						</td>
					</tr>
				</form>
			</table>
		</spring:nestedPath>
		<a href="<c:url value="/index.htm" />">Начальная страница</a>
		<a href="<c:url value="/j_spring_security_logout" />">Выход из чата</a>
	</body>
</html>
