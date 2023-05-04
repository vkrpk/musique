package vkrpk.musique.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vkrpk.musique.exception.CommandExecutionException;

public class PageAccueilController implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException
    {
        request.setAttribute("test", "testattribute");
        return "/accueil.jsp" ;
    }
}
