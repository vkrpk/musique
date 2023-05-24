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
        return "/liste.jsp" ;
    }
}
