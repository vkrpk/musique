<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./includes/header.jsp">
    <jsp:param name="titre" value="Modification Personne"/>
</jsp:include>
    <form method="post">
        <label class="mb-2" for="adherent-select">Modifier un adhérent:</label>
        <select name="modifierAdherent" class="form-select mb-3" aria-label="Default select example" id="adherent-select">
            <c:forEach items="${listePersonnes}" var="personne">
                <option value="${personne.id}">${personne.nom} ${personne.prenom}</option>
            </c:forEach>
        </select>
        <c:if test="${not empty personneTrouvee}">
            <div class="mb-3">
                <label for="nom" class="form-label">Nom</label>
                <input type="text" class="form-control" id="nom" aria-describedby="nomHelp" name="nom" value="${personneTrouvee.nom}">
                <div id="nomHelp" class="form-text">Le nom ne doit pas dépasser 30 caractères.</div>
            </div>
            <div class="mb-3">
                <label for="prenom" class="form-label">Prénom</label>
                <input type="text" class="form-control" id="prenom" aria-describedby="prenomHelp" name="prenom" value="${personneTrouvee.prenom}">
                <div id="prenomHelp" class="form-text">Le prénom doit contenir entre 2 et 30 caractères.</div>
            </div>
            <input type="hidden" name="id" value="${personneTrouvee.id}">
        </c:if>
        <button type="submit" class="btn btn-primary">Modifier cet adhérent</button>
    </form>
    <c:if test="${not empty violations}">
        <ul>
            <c:forEach items="${violations}" var="violation">
                <li>${violation.message}</li>
            </c:forEach>
        </ul>
    </c:if>
<jsp:include page="./includes/footer.jsp"></jsp:include>