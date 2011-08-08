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
		<link rel="stylesheet" type="text/css" href="css/albums.css">
	</head>

	<body>
		<p>Альбомы</p>
		${session_content}
		<c:forEach var="page_link" items="${page_links}">
			<c:if test="${page_link.link != ''}">
				<a href="<c:url value="${page_link.link}" />"><c:out value="${page_link.title}" /></a>
			</c:if>
			<c:if test="${page_link.link == ''}">
				<c:out value="${page_link.title}" />
			</c:if>
		</c:forEach>
		<table class="table_of_albums">
			<c:forEach var="album" items="${albums}" varStatus="loopStatus">
				<tr class="${(loopStatus.index % 2 == 0) ? 'album_one' : 'album_two'}">
					<td class="album_title">
						<a href="<c:url value="/albumphotos.htm?albumId=${album.id}" />">
							<c:out value="${album.title}" />
						</a>
					</td>
					<td class="album_description">
						<a href="<c:url value="/albumphotos.htm?albumId=${album.id}" />">
							<c:out value="${album.description}" />
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<c:forEach var="page_link" items="${page_links}">
			<c:if test="${page_link.link != ''}">
				<a href="<c:url value="${page_link.link}" />"><c:out value="${page_link.title}" /></a>
			</c:if>
			<c:if test="${page_link.link == ''}">
				<c:out value="${page_link.title}" />
			</c:if>
		</c:forEach>

		<spring:nestedPath path="new_album">
			<form action="" method="post">
        Создать новый альбом.<br />
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
		<a href="<c:url value="/index.htm" />">Начальная страница</a>
		<a href="<c:url value="/userconfig.htm" />">Настроить личные параметры</a>
		<a href="<c:url value="/j_spring_security_logout" />">Выход из чата</a>
	</body>
</html>
