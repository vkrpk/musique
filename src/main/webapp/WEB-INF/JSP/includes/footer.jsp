<%@page contentType="text/html" pageEncoding="UTF-8"%>
</main>
<footer class="container-fluid bg-secondary py-3">
    <section class="container d-flex flex-col flex-md-row justify-content-evenly">
        <a href="<c:url value='/'/>">Accueil</a>
        <a href="<c:url value='/?cmd=liste'/>">Liste</a>
        <a href="<c:url value='/?cmd=creation'/>">Création</a>
        <a href="<c:url value='/?cmd=modification'/>">Modification</a>
        <a href="<c:url value='/?cmd=suppression'/>">Suppression</a>
    </section>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>