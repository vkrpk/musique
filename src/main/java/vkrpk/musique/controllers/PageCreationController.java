package vkrpk.musique.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import vkrpk.musique.exception.CommandExecutionException;
import vkrpk.musique.models.Personne;
import vkrpk.musique.models.forms.SaisiePersonForm;

public class PageCreationController implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Personne personne1 = new Personne(2, "Nom 2", "Prenom 2");
        Personne personne2 = new Personne(3, "Nom 3", "Prénom 3");
        ArrayList<Personne> listePersonnes = new ArrayList<>();
        Collections.addAll(listePersonnes, personne1, personne2);

        if(request.getParameterMap().containsKey("nom")){
            Personne personne = new Personne(null, request.getParameter("nom"), request.getParameter("prenom"));
            Set<ConstraintViolation<Personne>> violations = validator.validate(personne);
            SaisiePersonForm saisiePersonForm = new SaisiePersonForm();
            saisiePersonForm.verifForm(request);
            String resultatSaisi = saisiePersonForm.getResultat();

            if(!resultatSaisi.equals("OK")) {
                request.setAttribute("duplicateNom", resultatSaisi);
                request.setAttribute("oldPersonValues", personne);
            }
            if(!violations.isEmpty()) {
                request.setAttribute("violations", violations);
                request.setAttribute("oldPersonValues", personne);
            }

            if(violations.isEmpty() && resultatSaisi.equals("OK")){
                request.setAttribute("creationAdherentValide", "Un adhérent a bien été créé");
                listePersonnes.add(personne);
            }
        }
        request.setAttribute("controller", "creation");
        return "/modification.jsp" ;
    }
}
