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
		<p>Фотографии альбома</p>

		<div class="album_title">${album.title}</div>
		<div class="album_description">${album.description}</div>

		${session_content}
		${title_description_changed}
		${photo_added}		
		<c:forEach var="page_link" items="${page_links}">
			<c:if test="${page_link.link != ''}">
				<a href="<c:url value="${page_link.link}" />"><c:out value="${page_link.title}" /></a>
			</c:if>
			<c:if test="${page_link.link == ''}">
				<c:out value="${page_link.title}" />
			</c:if>
		</c:forEach>
		<table class="table_of_photos">
			<c:forEach var="photo" items="${photos}" varStatus="loopStatus">
				<tr class="${(loopStatus.index % 2 == 0) ? 'photo_one' : 'photo_two'}">
					<td class="photo_file">
						<a href="<c:url value="/photo.htm?photoId=${photo.id}" />">
							<img src="${photo.file}" height="120" border="0" />
						</a>
					</td>
					<td class="photo_title">
						<a href="<c:url value="/photo.htm?photoId=${photo.id}" />">
							<c:out value="${photo.title}" />
						</a>
					</td>
					<td class="photo_description">
						<a href="<c:url value="/photo.htm?photoId=${photo.id}" />">
							<c:out value="${photo.description}" />:
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


		<form action="" method="post" enctype="multipart/form-data">
			Добавить фотографию:<br />
			<input type="file" name="photo" size="50"><br />
			<input type="submit" value="OK" />
		</form>

		<spring:nestedPath path="album">
			<form action="<c:url value="/albumphotos.htm" />" method="post">
        <spring:bind path="id">
					<input type="hidden" name="${status.expression}" value="${status.value}" /><br />
        </spring:bind>
        Изменить характеристики альбома.<br />
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




		<a href="<c:url value="/photo.htm" />">Просмотр фотографии</a>
		<a href="<c:url value="/albums.htm" />">Список альбомов</a>
		<a href="<c:url value="/index.htm" />">Начальная страница</a>
		<a href="<c:url value="/userconfig.htm" />">Настроить личные параметры</a>
		<a href="<c:url value="/j_spring_security_logout" />">Выход из чата</a>
	</body>
</html>
