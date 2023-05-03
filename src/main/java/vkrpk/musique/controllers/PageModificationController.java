package vkrpk.musique.controllers;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vkrpk.musique.models.Personne;

public class PageModificationController implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Personne personne = new Personne(1, "Nom 1", "Prenom 1");
        Personne personne1 = new Personne(2, "Nom 2", "Prenom 2");
        Personne personne2 = new Personne(3, "Nom 3", "Prénom 3");
        ArrayList<Personne> listePersonnes = new ArrayList<>();
        listePersonnes.add(personne);
        listePersonnes.add(personne1);
        listePersonnes.add(personne2);
        request.setAttribute("listePersonnes", listePersonnes);

        if(request.getParameter("modifierAdherent") != null){
            Personne personneTrouvee = null;
            for (Personne p : listePersonnes) {
                if (p.getId().toString() == request.getParameter("modifierAdherent")) {
                    personneTrouvee = p;
                    break;
                }
            }
            request.setAttribute("personneTrouvee", personneTrouvee);
        }

        if(request.getParameter("nom") != null && request.getParameter("prenom") != null) {
            Personne personneModifiee = null;
            for (Personne p : listePersonnes) {
                if (p.getId().toString() == request.getParameter("id")) {
                    personneModifiee = p;
                    break;
                }
            }
            personneModifiee.setNom(request.getParameter("nom"));
            personneModifiee.setPrenom(request.getParameter("prenom"));
            request.setAttribute("personneModifiee", personneModifiee);
        }
        return "/modification.jsp" ;
    }
}
