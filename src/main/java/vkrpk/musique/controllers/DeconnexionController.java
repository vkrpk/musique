package vkrpk.musique.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vkrpk.musique.exception.CommandExecutionException;

public class DeconnexionController implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException
    {
       request.getSession().invalidate();
       request.setAttribute("logoutSuccess","Déconnexion réussie.");
        return "/accueil.jsp" ;
    }
}
