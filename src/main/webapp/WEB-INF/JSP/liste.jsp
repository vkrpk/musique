<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./includes/header.jsp">
    <jsp:param name="titre" value="Liste Personnes"/>
</jsp:include>
<c:choose>
    <c:when test="${not empty personnes}">
        <h4>Liste des personnes adhérentes au club de musique :</h4>
        <ul>
            <c:forEach items="${personnes}" var="personne">
                <li><c:out value="${personne.nom}"/> <c:out value="${personne.prenom}"/></li>
            </c:forEach>
        </ul>
    </c:when>
    <c:otherwise>
        <h1>Liste null</h1>
    </c:otherwise>
</c:choose>

<jsp:include page="./includes/footer.jsp"></jsp:include>
