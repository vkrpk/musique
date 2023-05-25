<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./includes/header.jsp">
    <jsp:param name="titre" value="Accueil"/>
</jsp:include>
<c:if test="${not empty message}">
  <div class="alert alert-danger" role="alert">
    ${message}
  </div>
</c:if>
<c:if test="${empty message}">
  <div class="alert alert-danger" role="alert">
    <p>Désolé, un problème est survenu. Veuillez réessayer plus tard.</p>
  </div>
</c:if>
<jsp:include page="./includes/footer.jsp"></jsp:include>
