package vkrpk.musique.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vkrpk.musique.dao.DaoPersonne;
import vkrpk.musique.exception.CommandExecutionException;

public class PageListeController implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException
    {
        DaoPersonne daoPersonne = new DaoPersonne();
        request.setAttribute("personnes", daoPersonne.findAll());
        return "/liste.jsp" ;
    }
}
