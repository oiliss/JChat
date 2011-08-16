<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
${connected}
<c:forEach var="message" items="${new_messages}" varStatus="loopStatus">
			${message.id}
			<fmt:formatDate value="${message.date}" pattern="dd.MM HH:mm" />
			${message.user.avatar}
			${message.user.name}
			${message.text}
			<<<<<<-- END OF MESSAGE -->>>>>>
</c:forEach>
