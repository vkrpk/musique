<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
String csrfToken = vkrpk.musique.utils.TokenHelper.getToken();
session.setAttribute("csrfToken", csrfToken);
%>
<jsp:include page="./includes/header.jsp">
    <jsp:param name="titre" value="Connexion"/>
</jsp:include>
<c:if test="${not empty loginFail}">
  <div class="alert alert-danger" role="alert">
    ${loginFail}
  </div>
</c:if>
<form method="post">
    <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"/>
    <div class="mb-3">
        <label for="pseudo" class="form-label">Pseudo</label>
        <input type="pseudo" class="form-control" id="pseudo" aria-describedby="emailHelp" name="pseudo" value="<c:out value="${not empty oldUsername ? oldUsername : ''}"/>">
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Mot de passe</label>
        <input type="password" class="form-control" id="password" name="password">
      </div>
      <button type="submit" class="btn btn-primary">Connexion</button>
</form>


<jsp:include page="./includes/footer.jsp"></jsp:include>
