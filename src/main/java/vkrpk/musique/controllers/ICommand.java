package vkrpk.musique.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vkrpk.musique.exception.CommandExecutionException;

public interface ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException;
}