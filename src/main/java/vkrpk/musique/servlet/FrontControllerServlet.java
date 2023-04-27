package vkrpk.musique.servlet;
/*
* To change this license header, choose License Headers in Project
Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;

import vkrpk.musique.controllers.ICommand;
import vkrpk.musique.controllers.PageAccueilController;
import vkrpk.musique.controllers.PageCreationController;
import vkrpk.musique.controllers.PageListeController;
import vkrpk.musique.controllers.PageModificationController;
import vkrpk.musique.controllers.PageSuppressionController;
import vkrpk.musique.log.FormatterLog;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static vkrpk.musique.log.LoggerECFJakarta.LOGGER;

/**
*
* @author victork
*/
@WebServlet(urlPatterns = {"/FrontControllerServlet"}, name = "FrontControllerServlet")
public class FrontControllerServlet extends HttpServlet {

    private Map<String, Object> commands = new HashMap<>();

    /**
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlSuite = null;
        try {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            FileHandler fh = new FileHandler("LogReverso.log", true);
            LOGGER.setUseParentHandlers(false);
            LOGGER.addHandler(fh);
            fh.setFormatter(new FormatterLog());

            LOGGER.log(Level.INFO, "Démarrage de l'application");

            String cmd = request.getParameter("cmd");
            ICommand com = (ICommand) commands.get(cmd);
            urlSuite = com.execute(request, response);
        } catch (Exception e) {
            urlSuite = "/erreur.jsp";
        } finally {
            request.getRequestDispatcher("WEB-INF/JSP" + urlSuite).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
    * Handles the HTTP <code>GET</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /*
    *  Handles the HTTP <code>POST</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void init(){
        commands.put("accueil", new PageAccueilController());
        commands.put("liste", new PageListeController());
        commands.put("creation", new PageCreationController());
        commands.put("suppression", new PageSuppressionController());
        commands.put("modification", new PageModificationController());
    }
}