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
		<link rel="stylesheet" type="text/css" href="css/index.css">
	</head>

	<body>
		<p>История сообщений</p>
		${session_content}
		<c:forEach var="page_link" items="${page_links}">
			<c:if test="${page_link.link != ''}">
				<a href="<c:url value="${page_link.link}" />"><c:out value="${page_link.title}" /></a>
			</c:if>
			<c:if test="${page_link.link == ''}">
				<c:out value="${page_link.title}" />
			</c:if>
		</c:forEach>
		<table class="table_of_messages">
			<c:forEach var="message" items="${new_messages}" varStatus="loopStatus">
				<tr class="${(loopStatus.index % 2 == 0) ? 'message_one' : 'message_two'}">
					<td class="message_date">
						<%--
						<fmt:formatDate value="${message.date}" pattern="dd-MM-yyyy HH:mm:ss" />
						--%>
						<fmt:formatDate value="${message.date}" pattern="dd.MM HH:mm" />
					</td>
					<td class="message_user_avatar">
						<img src="${message.user.avatar}" height="40" />
					</td>
					<td class="message_user_name">
						${message.user.name}:
					</td>
					<td class="message_text">
						${message.text}<br />
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
		<br />
		<a href="<c:url value="/index.htm" />">Начальная страница</a>
		<a href="<c:url value="/userconfig.htm" />">Настроить личные параметры</a>
		<a href="<c:url value="/j_spring_security_logout" />">Выход из чата</a>
	</body>
</html>
