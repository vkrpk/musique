<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./includes/header.jsp">
    <jsp:param name="titre" value="Suppression Personne"/>
</jsp:include>
<form method="post">
    <label class="mb-2" for="adherent-select">Supprimer un adhérent:</label>
    <select name="supprimerAdherent" class="form-select mb-3" aria-label="Default select example" id="adherent-select">
        <c:forEach items="${listePersonnes}" var="personne">
            <option value="<c:out value="${personne.id}"/>">${personne.nom} ${personne.prenom}</option>
        </c:forEach>
    </select>
    <c:if test="${not empty adherentASupprimer}">
        <input type="hidden" name="idAdherentASupprimer" value="<c:out value="${adherentASupprimer.id}"/>">
    </c:if>
    <button type="submit" class="btn btn-primary text-center">${not empty adherentASupprimer ? "Confirmer" : "Supprimer cet adhérent"}</button>
</form>
<c:if test="${not empty adherentASupprimer}">
    <p class="text-danger">Si vous souhaitez supprimer l'adhérent : "${adherentASupprimer.prenom} ${adherentASupprimer.nom}", veuillez cliquer sur le bouton "Confirmer".</p>
</c:if>
<c:if test="${not empty suppressionAdherentValide}">
    <p class="text-primary">${suppressionAdherentValide}</p>
</c:if>
<jsp:include page="./includes/footer.jsp"></jsp:include>
