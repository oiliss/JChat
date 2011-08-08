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
	</head>

	<body>
		<p>Фото</p>						
		<div class="photo_file"><img src="${photo.file}" /></div>
		<div class="photo_title">${photo.title}</div>
		<div class="photo_description">${photo.description}</div>

		<spring:nestedPath path="photo">
			<form action="<c:url value="/photo.htm" />" method="post">
        <spring:bind path="id">
					<input type="hidden" name="${status.expression}" value="${status.value}" /><br />
        </spring:bind>
        Изменить характеристики фотографии.<br />
        Заголовок:<br />
        <spring:bind path="title">
					<input type="text" name="${status.expression}" value="${status.value}" /><br />
        </spring:bind>
        Описание:<br />
        <spring:bind path="description">
					<textarea name="${status.expression}">${status.value}</textarea><br />
        </spring:bind>
        <input type="submit" value="OK" />
			</form>
		</spring:nestedPath>
		
		<a href="<c:url value="/albumphotos.htm" />">Просмотр фотографий альбома</a>
		<a href="<c:url value="/albums.htm" />">Список альбомов</a>
		<a href="<c:url value="/index.htm" />">Начальная страница</a>
		<a href="<c:url value="/userconfig.htm" />">Настроить личные параметры</a>
		<a href="<c:url value="/j_spring_security_logout" />">Выход из чата</a>
	</body>
</html>
