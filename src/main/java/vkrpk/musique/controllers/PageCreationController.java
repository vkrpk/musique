package vkrpk.musique.controllers;

import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import vkrpk.musique.dao.DaoPersonne;
import vkrpk.musique.exception.CommandExecutionException;
import vkrpk.musique.exception.ExceptionDAO;
import vkrpk.musique.models.Personne;
import vkrpk.musique.models.forms.SaisiePersonForm;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PageCreationController implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(PageCreationController.class.getName());

    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException
    {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            if(request.getParameterMap().containsKey("nom")){
                Personne personne = new Personne(request.getParameter("nom"), request.getParameter("prenom"));
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
                    DaoPersonne daoPersonne = new DaoPersonne();
                        daoPersonne.save(personne);
                }
            }
            request.setAttribute("controller", "creation");
        } catch (ExceptionDAO exceptionDAO) {
            switch (exceptionDAO.getGravite()) {
                case 5:
                    exceptionDAO.printStackTrace();
                    LOGGER.log(Level.SEVERE, exceptionDAO.getMessage());
                    System.exit(1);
                    break;
            }
        } catch ( Exception exception) {
            exception.printStackTrace();
            LOGGER.log(Level.SEVERE, exception.getMessage());
            System.exit(1);
            throw new CommandExecutionException(exception.getMessage());
        }
        return "/modification.jsp" ;
    }
}
