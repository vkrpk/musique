package vkrpk.musique.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vkrpk.musique.exception.CommandExecutionException;
import vkrpk.musique.exception.ExceptionDAO;
import vkrpk.musique.models.Personne;
import vkrpk.musique.utils.CsrfTokenValidator;

import java.util.logging.Level;
import java.util.logging.Logger;

import vkrpk.musique.dao.DaoPersonne;

public class PageSuppressionController implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(PageSuppressionController.class.getName());
    private static DaoPersonne daoPersonne = new DaoPersonne();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException
    {
        try {
                if(request.getParameterMap().containsKey("supprimerAdherent") ){
                    if(CsrfTokenValidator.isValid(request)) {
                        LOGGER.info(request.getParameter("csrfToken") + " : token");
                        Integer intIdPersonneASupprimer = Integer.parseInt(request.getParameter("supprimerAdherent"));
                        Personne personne = daoPersonne.findPersonById(intIdPersonneASupprimer);
                        request.setAttribute("adherentASupprimer", personne);
                    } else {
                        return "/erreur.jsp";
                    }
                }

                if(request.getParameterMap().containsKey("idAdherentASupprimer")){
                    if(CsrfTokenValidator.isValid(request)) {
                        Integer intIdPersonneASupprimer = Integer.parseInt(request.getParameter("idAdherentASupprimer"));
                        Personne personne = daoPersonne.findPersonById(intIdPersonneASupprimer);
                        new DaoPersonne().delete(personne);
                        request.setAttribute("adherentASupprimer", null);
                        request.setAttribute("suppressionAdherentValide", "Un adhérent a bien été supprimé");
                    } else {
                        return "/erreur.jsp";
                    }
                }
                request.setAttribute("listePersonnes", daoPersonne.findAll());
        } catch (ExceptionDAO exceptionDAO) {
            if(exceptionDAO.getGravite() == 5) {
                exceptionDAO.printStackTrace();
                    LOGGER.log(Level.SEVERE, exceptionDAO.getMessage());
                    System.exit(1);
            }
            throw new CommandExecutionException(exceptionDAO.getMessage());
        } catch (Exception exception) {
            throw new CommandExecutionException(exception.getMessage());
        }
        return "/suppression.jsp" ;
    }
}
