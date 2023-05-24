package vkrpk.musique.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vkrpk.musique.dao.DaoPersonne;
import vkrpk.musique.exception.CommandExecutionException;
import vkrpk.musique.exception.ExceptionDAO;

public class PageListeController implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(PageListeController.class.getName());

    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException
    {
        DaoPersonne daoPersonne = new DaoPersonne();
        try {
            request.setAttribute("personnes", daoPersonne.findAll());
        } catch (ExceptionDAO exceptionDAO) {
            if(exceptionDAO.getGravite() == 5) {
                exceptionDAO.printStackTrace();
                    LOGGER.log(Level.SEVERE, exceptionDAO.getMessage());
                    System.exit(1);
            }
        } catch ( Exception exception) {
            throw new CommandExecutionException(exception.getMessage());
        }
        return "/liste.jsp" ;
    }
}
