<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <title>${param.titre}</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <nav class="navbar navbar-expand-lg bg-body-tertiary w-100">
        <div class="container-fluid">
          <a class="navbar-brand" href="<c:url value='/'/>">Music School</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
              <li class="nav-item">
                <a class="nav-link" aria-current="page" href="<c:url value='/'/>">Accueil</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="<c:url value='/?cmd=liste'/>">Liste</a>
              </li>
              <c:if test="${sessionScope.status eq 'connected'}">
                <li class="nav-item">
                  <a class="nav-link" href="<c:url value='/?cmd=creation'/>">Creation</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="<c:url value='/?cmd=modification'/>">Modification</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="<c:url value='/?cmd=suppression'/>">Suppression</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="<c:url value='/?cmd=deconnexion'/>">Déconnexion</a>
                </li>
              </c:if>
              <c:if test="${sessionScope.status ne 'connected'}">
                <li class="nav-item">
                  <a class="nav-link" href="<c:url value='/?cmd=connexion'/>">Connexion</a>
                </li>
              </c:if>
            </ul>
          </div>
        </div>
      </nav>
      <main>
