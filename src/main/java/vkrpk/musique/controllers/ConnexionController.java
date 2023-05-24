package vkrpk.musique.controllers;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vkrpk.musique.dao.DaoUser;
import vkrpk.musique.exception.CommandExecutionException;
import vkrpk.musique.models.User;
import java.util.logging.Logger;

public class ConnexionController implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(ConnexionController.class.getName());

    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException
    {
        String pseudo = request.getParameter("pseudo");
        String password = request.getParameter("password");
        if(pseudo == null && password == null) {
            return "/connexion.jsp";
        }
        try {
            User user = new DaoUser().findByPseudo(pseudo);
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);
            boolean passwordMatch = argon2.verify(user.getPassword(), password.toCharArray());
            if (user != null && passwordMatch) {
                    LOGGER.info("Mot de passe correct");
                    request.setAttribute("loginSuccess","Connexion réussie.");
                    HttpSession session = request.getSession();
                    session.setAttribute("role", "admin");
                    session.setAttribute("status", "connected");
                    return "/accueil.jsp";
            } else {
                request.setAttribute("loginFail", "L'identifiant et le mot de passe ne correspondent pas");
                request.setAttribute("oldUsername", pseudo);
            }
        } catch (Exception e) {
            LOGGER.info("Erreur lors de la connexion");
            request.setAttribute("loginFail", "L'identifiant et le mot de passe ne correspondent pas");
            request.setAttribute("oldUsername", pseudo);
        }
        return "/connexion.jsp" ;
    }
}