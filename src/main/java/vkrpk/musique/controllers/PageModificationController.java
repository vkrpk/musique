package vkrpk.musique.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vkrpk.musique.exception.CommandExecutionException;
import vkrpk.musique.models.Personne;
import vkrpk.musique.models.forms.SaisiePersonForm;

public class PageModificationController implements ICommand {
    private static final String OLD_PERSON_VALUES = "oldPersonValues";
    private static ArrayList<Personne> listePersonnes = new ArrayList<>();
    private Personne selectedPersonne = new Personne();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException
    {
        String parameterNom = request.getParameter("nom");
        String parameterPrenom = request.getParameter("prenom");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        if(request.getParameterMap().containsKey("modifierAdherent")){
            for (Personne p : listePersonnes) {
                if (Integer.toString(p.getId()).equals(request.getParameter("modifierAdherent"))) {
                    selectedPersonne = p;
                    break;
                }
            }
            request.setAttribute(OLD_PERSON_VALUES, selectedPersonne);
        }

        if(request.getParameterMap().containsKey("nom") && selectedPersonne.getId() != null){
            Personne testValidationPersonne = new Personne(parameterNom, parameterPrenom);
            Set<ConstraintViolation<Personne>> violations = validator.validate(testValidationPersonne);

            SaisiePersonForm saisiePersonForm = new SaisiePersonForm();
            saisiePersonForm.verifForm(request);
            String resultatSaisi = saisiePersonForm.getResultat();

            checkSaisiPersonForm(resultatSaisi, request, testValidationPersonne);
            checkViolations(violations, request, testValidationPersonne);
            checkFormIsValid(violations, request, resultatSaisi);
        }
        request.setAttribute("selectedPersonne", selectedPersonne);
        request.setAttribute("listePersonnes", listePersonnes);
        request.setAttribute("controller", "modification");

        return "/modification.jsp" ;
    }

    private HttpServletRequest checkSaisiPersonForm(String resultat, HttpServletRequest request, Personne testValidationPersonne){
        if(!resultat.equals("OK")) {
            request.setAttribute("duplicateNom", resultat);
            request.setAttribute(OLD_PERSON_VALUES, testValidationPersonne);
        }
        return request;
    }

    private HttpServletRequest checkViolations(Set<ConstraintViolation<Personne>> violations, HttpServletRequest request, Personne testValidationPersonne){
        if(!violations.isEmpty()) {
            request.setAttribute("violations", violations);
            request.setAttribute(OLD_PERSON_VALUES, testValidationPersonne);
        }
        return request;
    }

    private HttpServletRequest checkFormIsValid(Set<ConstraintViolation<Personne>> violations, HttpServletRequest request, String resultat){
        if(violations.isEmpty() && resultat.equals("OK")){
            for (Personne p : listePersonnes) {
                if (p.getId().equals(selectedPersonne.getId())) {
                    p.setNom(request.getParameter("nom"));
                    p.setPrenom(request.getParameter("prenom"));
                    break;
                }
            }
            selectedPersonne = null;
            request.setAttribute("modificationAdherentValide", "Un adhérent a bien été modifié");
        }
        return request;
    }
}
