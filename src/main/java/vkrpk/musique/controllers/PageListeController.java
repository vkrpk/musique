package vkrpk.musique.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PageListeController implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        return "/liste.jsp" ;
    }
}
