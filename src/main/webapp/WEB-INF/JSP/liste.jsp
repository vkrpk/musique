<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./includes/header.jsp">
    <jsp:param name="titre" value="Liste Personnes"/>
</jsp:include>
<c:choose>
    <c:when test="${not empty personnes}">
        <h1>${message}</h1>
        <c:forEach items="${personnes}" var="personne">
        <c:out value="${personne.nom}"/><p>
        <br>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <h1>Liste null</h1>
    </c:otherwise>
</c:choose>

<jsp:include page="./includes/footer.jsp"></jsp:include>
