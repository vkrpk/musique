package vkrpk.musique.controllers;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vkrpk.musique.dao.DaoUser;
import vkrpk.musique.exception.CommandExecutionException;
import vkrpk.musique.exception.ExceptionDAO;
import vkrpk.musique.models.User;
import vkrpk.musique.utils.CsrfTokenValidator;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnexionController implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(ConnexionController.class.getName());

    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException
    {
        try {
            String pseudo = null;
            String password = null;
            if(request.getParameterMap().containsKey("pseudo") || request.getParameterMap().containsKey("password")){
                if(CsrfTokenValidator.isValid(request)) {
                    pseudo = request.getParameter("pseudo");
                    password = request.getParameter("password");
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Accès interdit.");
                }
            } else {
                return "/connexion.jsp";
            }

            User user = new DaoUser().findByPseudo(pseudo);
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);
            boolean passwordMatch = argon2.verify(user.getPassword(), password.toCharArray());
            if (passwordMatch) {
                    request.setAttribute("loginSuccess","Connexion réussie.");
                    HttpSession session = request.getSession();
                    session.setAttribute("role", "admin");
                    session.setAttribute("status", "connected");
                    return "/accueil.jsp";
            } else {
                request.setAttribute("loginFail", "L'identifiant et le mot de passe ne correspondent pas");
                request.setAttribute("oldUsername", pseudo);
            }
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
        return "/connexion.jsp" ;
    }
}