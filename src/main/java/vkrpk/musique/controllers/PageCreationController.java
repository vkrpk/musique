package vkrpk.musique.controllers;

import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import vkrpk.musique.models.Personne;

public class PageCreationController implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");

        Personne personne = new Personne(null, nom, prenom);

        Set<ConstraintViolation<Personne>> violations = validator.validate(personne);
        request.setAttribute("personne", personne);
        request.setAttribute("violations", violations);
        return "/creation.jsp" ;
    }
}
