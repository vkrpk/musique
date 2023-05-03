package vkrpk.musique.controllers;

import java.util.ArrayList;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vkrpk.musique.models.Personne;

public class PageModificationController implements ICommand {
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

        Personne personneTrouvee = null;
        if(request.getParameter("modifierAdherent") != null){
            for (Personne p : listePersonnes) {
                if (Integer.toString(p.getId()).equals(request.getParameter("modifierAdherent"))) {
                    personneTrouvee = p;
                    break;
                }
            }
            request.setAttribute("personneTrouvee", personneTrouvee);
        }

        if(request.getParameter("nom") != null && request.getParameter("prenom") != null) {
            for (Personne p : listePersonnes) {
                if (Integer.toString(p.getId()).equals(request.getParameter("id")) ) {
                    Personne personneModifiee = new Personne(
                        p.getId(),
                        request.getParameter("nom"),
                        request.getParameter("prenom")
                    );
                    Set<ConstraintViolation<Personne>> violations = validator.validate(personneModifiee);
                    if (violations.isEmpty()) {
                        p.setNom(request.getParameter("nom"));
                        p.setPrenom(request.getParameter("prenom"));
                        request.setAttribute("personneTrouvee", null);
                    } else {
                        request.setAttribute("violations", violations);
                    }
                    break;
                }
            }
        }
        request.setAttribute("listePersonnes", listePersonnes);

        return "/modification.jsp" ;
    }
}
