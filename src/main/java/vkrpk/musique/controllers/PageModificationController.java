package vkrpk.musique.controllers;

import java.util.List;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vkrpk.musique.dao.DaoPersonne;
import vkrpk.musique.exception.CommandExecutionException;
import vkrpk.musique.exception.ExceptionDAO;
import vkrpk.musique.models.Personne;
import vkrpk.musique.models.forms.SaisiePersonForm;
import vkrpk.musique.utils.CsrfTokenValidator;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PageModificationController implements ICommand {
    private static final String OLD_PERSON_VALUES = "oldPersonValues";
    private Personne selectedPersonne = new Personne();
    private DaoPersonne daoPersonne = new DaoPersonne();
    private static final Logger LOGGER = Logger.getLogger(PageModificationController.class.getName());

    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException
    {
        try {
            String parameterNom = request.getParameter("nom");
            String parameterPrenom = request.getParameter("prenom");

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            List<Personne> listePersonnes = daoPersonne.findAll();

            if(request.getParameterMap().containsKey("modifierAdherent")){
                if(CsrfTokenValidator.isValid(request)) {
                    Integer intIdPersonneAModifier = Integer.parseInt(request.getParameter("modifierAdherent"));
                    selectedPersonne = daoPersonne.findPersonById(intIdPersonneAModifier);
                    request.setAttribute(OLD_PERSON_VALUES, selectedPersonne);
                } else {
                    return "/erreur.jsp";
                }
            }

            if(request.getParameterMap().containsKey("nom") && selectedPersonne.getId() != null){
                if(CsrfTokenValidator.isValid(request)) {
                    Personne testValidationPersonne = new Personne(parameterNom, parameterPrenom);
                    Set<ConstraintViolation<Personne>> violations = validator.validate(testValidationPersonne);

                    SaisiePersonForm saisiePersonForm = new SaisiePersonForm();
                    saisiePersonForm.verifForm(request);
                    String resultatSaisi = saisiePersonForm.getResultat();

                    checkSaisiPersonForm(resultatSaisi, request, testValidationPersonne);
                    checkViolations(violations, request, testValidationPersonne);
                    checkFormIsValid(violations, request, resultatSaisi);
                } else {
                    return "/erreur.jsp";
                }
            }
            request.setAttribute("selectedPersonne", selectedPersonne);
            request.setAttribute("listePersonnes", listePersonnes);
            request.setAttribute("controller", "modification");
        } catch (ExceptionDAO exceptionDAO) {
            if(exceptionDAO.getGravite() == 5) {
                exceptionDAO.printStackTrace();
                    LOGGER.log(Level.SEVERE, exceptionDAO.getMessage());
                    System.exit(1);
            }
            throw new CommandExecutionException(exceptionDAO.getMessage());
        } catch ( Exception exception) {
            throw new CommandExecutionException(exception.getMessage());
        }
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

    private HttpServletRequest checkFormIsValid(Set<ConstraintViolation<Personne>> violations, HttpServletRequest request, String resultat) throws ExceptionDAO{
        if(violations.isEmpty() && resultat.equals("OK")){
            selectedPersonne.setNom(request.getParameter("nom"));
            selectedPersonne.setPrenom(request.getParameter("prenom"));
            daoPersonne.save(selectedPersonne);
        }
        selectedPersonne = null;
        request.setAttribute("modificationAdherentValide", "Un adhérent a bien été modifié");
        return request;
    }
}
