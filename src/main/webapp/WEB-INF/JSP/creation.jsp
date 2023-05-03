<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./includes/header.jsp">
    <jsp:param name="titre" value="Création Personne"/>
</jsp:include>
    <form method="post">
        <div class="mb-3">
            <label for="nom" class="form-label">Nom</label>
            <input type="text" class="form-control" id="nom" aria-describedby="nomHelp" name="nom">
            <div id="nomHelp" class="form-text">Le nom ne doit pas dépasser 30 caractères.</div>
        </div>
        <div class="mb-3">
            <label for="prenom" class="form-label">Prénom</label>
            <input type="text" class="form-control" id="prenom" aria-describedby="prenomHelp" name="prenom">
            <div id="prenomHelp" class="form-text">Le prénom doit contenir entre 2 et 30 caractères.</div>
        </div>
        <button type="submit" class="btn btn-primary">Envoyer</button>
    </form>
<jsp:include page="./includes/footer.jsp"></jsp:include>
