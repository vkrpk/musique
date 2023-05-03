package vkrpk.musique.controllers;

import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import vkrpk.musique.models.Personne;
import vkrpk.musique.models.forms.SaisiePersonForm;

public class PageCreationController implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        if(request.getParameterMap().containsKey("nom")){
            Personne personne = new Personne(null, request.getParameter("nom"), request.getParameter("prenom"));
            Set<ConstraintViolation<Personne>> violations = validator.validate(personne);
            SaisiePersonForm saisiePersonForm = new SaisiePersonForm();
            saisiePersonForm.verifForm(request);
            String resultatSaisi = saisiePersonForm.getResultat();
            if(violations.isEmpty() && resultatSaisi.equals("OK")){
                request.setAttribute("creationAdherentValide", "Un adhérent a bien été créé");
            }
            if(!resultatSaisi.equals("OK")) {
                request.setAttribute("duplicateNom", resultatSaisi);
            }
            if(!violations.isEmpty()) {
                request.setAttribute("violations", violations);
            }
        }
        request.setAttribute("controller", "creation");
        return "/modification.jsp" ;
    }
}
