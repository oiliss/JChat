<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:forEach var="message" items="${new_messages}" varStatus="loopStatus">
	<tr class="${(loopStatus.index % 2 == 0) ? 'message_one' : 'message_two'}">
		<td class="message_date new_message" >
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
