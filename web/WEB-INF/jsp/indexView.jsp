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
		<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
	</head>

	<body>
		<p>Последние сообщения</p>
		${session_content}
		<form id="textFormId" action="<c:url value="/index.htm" />" method="post">
			Сообщение:<br />
			<textarea id="textId" name="text" rows="5" cols="60"></textarea>
			<br />
			<input type="submit" value="OK" /> или Ctrl + Enter
		</form>

		<a href="<c:url value="/index.htm?allMessagesReaded=allMessagesReaded" />">Все сообщения прочитаны</a>

		<table id="messages" class="table_of_messages">
			<c:forEach var="message" items="${new_messages}" varStatus="loopStatus">
				<tr class="${(loopStatus.index % 2 == 0) ? 'message_one' : 'message_two'}">
					<td class="message_date${(message.id > last_readed_message_id) ? ' new_message' : ''}">
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

<!-- <a href="<c:url value="/index.htm" />">Есть еще сообщения?</a> -->

		<a href="<c:url value="/history.htm" />">История сообщений</a>
		<a href="<c:url value="/albums.htm" />">Список альбомов</a>
		<a href="<c:url value="/userconfig.htm" />">Настроить личные параметры</a>
		<a href="<c:url value="/j_spring_security_logout" />">Выход из чата</a>


		<script type="text/javascript">
			function getNewMessages()
			{
				$.ajax({
					url: "ajax.htm",
					cache: false,
					success: function(html){
						//$("td").prepend(html);
						html = jQuery.trim(html);
						//$("#log").append(".");
						//$("#log").append(html);
						//if (!html.empty()) {
						$("#messages").prepend(html);
						//}
					}
				});
			}

			$(document).ready(function(){

				$('#textId').keydown(function (e) {
					if (e.ctrlKey && e.keyCode == 13) {						
						document.forms["textFormId"].submit();
					}
				});

				getNewMessages();
				setInterval('getNewMessages()',5000);
			});
		</script>

		<!-- <div id="log">log</div> -->
	</body>
</html>
