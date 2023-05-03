<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./includes/header.jsp">
    <jsp:param name="titre" value="${controller eq 'creation' ? 'Ajouter un adhérent' : 'Modifier un adhérent'}"/>
</jsp:include>
    <form method="post">
        <c:if test="${controller eq 'modification'}">
            <label class="mb-2" for="adherent-select">Modifier un adhérent:</label>
            <select name="modifierAdherent" class="form-select mb-3" aria-label="Default select example" id="adherent-select">
                <c:forEach items="${listePersonnes}" var="personne">
                    <option value="${personne.id}">${personne.nom} ${personne.prenom}</option>
                </c:forEach>
            </select>
        </c:if>
        <c:if test="${not empty personneTrouvee or controller eq 'creation'}">
            <div class="mb-3">
                <label for="nom" class="form-label">Nom</label>
                <input type="text" class="form-control" id="nom" aria-describedby="nomHelp" name="nom" value="${controller eq 'creation' ? '' : personneTrouvee.nom}">
                <div id="nomHelp" class="form-text">Le nom ne doit pas dépasser 30 caractères.</div>
            </div>
            <div class="mb-3">
                <label for="prenom" class="form-label">Prénom</label>
                <input type="text" class="form-control" id="prenom" aria-describedby="prenomHelp" name="prenom" value="${controller eq 'creation' ? '' : personneTrouvee.prenom}">
                <div id="prenomHelp" class="form-text">Le prénom doit contenir entre 2 et 30 caractères.</div>
            </div>
            <c:if test="${controller eq 'modification'}">
                <input type="hidden" name="id" value="${personneTrouvee.id}">
            </c:if>
        </c:if>
        <button type="submit" class="btn btn-primary text-center">${controller eq 'creation' ? 'Créer un adhérent' : 'Modifier un adhérent'}</button>
    </form>
    <c:if test="${not empty violations}">
        <c:forEach items="${violations}" var="violation">
            <p class="text-danger">${violation.message}</p>
        </c:forEach>
    </c:if>
    <c:if test="${not empty duplicateNom}">
        <p class="text-danger">${duplicateNom}</p>
    </c:if>
    <c:if test="${not empty creationAdherentValide}">
        <p class="text-primary">${creationAdherentValide}</p>
    </c:if>
    <c:if test="${not empty modificationAdherentValide}">
        <p class="text-primary">${modificationAdherentValide}</p>
    </c:if>
<jsp:include page="./includes/footer.jsp"></jsp:include>