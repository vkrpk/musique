package vkrpk.musique.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Set;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import vkrpk.musique.models.Personne;

public class PageSuppressionController implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Personne personne = new Personne(1, "Nom 1", "Prenom 1");
        Personne personne1 = new Personne(2, "Nom 2", "Prenom 2");
        Personne personne2 = new Personne(3, "Nom 3", "Prénom 3");
        ArrayList<Personne> listePersonnes = new ArrayList<>();
        listePersonnes.add(personne);
        listePersonnes.add(personne1);
        listePersonnes.add(personne2);

        request.setAttribute("listePersonnes", listePersonnes);

        if(request.getParameterMap().containsKey("supprimerAdherent")){
            Personne adherentASupprimer = null;
            for (Personne p : listePersonnes) {
                if (Integer.toString(p.getId()).equals(request.getParameter("supprimerAdherent"))) {
                    adherentASupprimer = p;
                    break;
                }
            }
            request.setAttribute("adherentASupprimer", adherentASupprimer);
        }

        if(request.getParameterMap().containsKey("idAdherentASupprimer")){
            for (Personne p : listePersonnes) {
                if (Integer.toString(p.getId()).equals(request.getParameter("idAdherentASupprimer"))) {
                    listePersonnes.remove(p);
                    break;
                }
            }
            request.setAttribute("adherentASupprimer", null);
        }
        return "/suppression.jsp" ;
    }
}
