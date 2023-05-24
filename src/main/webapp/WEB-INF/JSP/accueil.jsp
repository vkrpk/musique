<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./includes/header.jsp">
    <jsp:param name="titre" value="Accueil"/>
</jsp:include>
<c:if test="${not empty loginSuccess}">
  <div class="alert alert-success" role="alert">
    ${loginSuccess}
  </div>
</c:if>
<c:if test="${not empty logoutSuccess}">
  <div class="alert alert-success" role="alert">
    ${logoutSuccess}
  </div>
</c:if>
<jsp:include page="./includes/footer.jsp"></jsp:include>
