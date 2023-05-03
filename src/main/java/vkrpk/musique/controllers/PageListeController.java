package vkrpk.musique.controllers;

import java.util.ArrayList;
import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import vkrpk.musique.models.Personne;

public class PageListeController implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {


        // request.setAttribute("listePersonnes", listePersonnes);
        return "/liste.jsp" ;
    }
}
