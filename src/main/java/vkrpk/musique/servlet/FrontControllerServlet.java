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
import java.util.logging.Logger;

import vkrpk.musique.controllers.ICommand;
import vkrpk.musique.controllers.PageAccueilController;
import vkrpk.musique.controllers.PageCreationController;
import vkrpk.musique.controllers.PageListeController;
import vkrpk.musique.controllers.PageModificationController;
import vkrpk.musique.controllers.PageSuppressionController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
*
* @author victork
*/
@WebServlet(urlPatterns = {"/FrontControllerServlet"}, name = "FrontControllerServlet")
public class FrontControllerServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(FrontControllerServlet.class.getName());
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
            HttpSession session = request.getSession();
            Cookie cookie = new Cookie("expiration", "1an");
            cookie.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(cookie);
            if (session.getAttribute("compteurPage") == null) {
                session.setAttribute("compteurPage", 0);
            }
            session.setAttribute("compteurPage", (int) session.getAttribute("compteurPage") + 1);
            String cmd = request.getParameter("cmd");
            ICommand com = (ICommand) commands.get(cmd);
            urlSuite = com.execute(request, response);

        } catch (Exception e) {
            urlSuite = "/erreur.jsp";
            request.setAttribute("message", "Une erreur inconnues est survenue. Veuillez contacter l'administrateur du site.");
            LOGGER.log(Level.SEVERE, e.getMessage());
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
        commands.put(null, new PageAccueilController());
        commands.put("liste", new PageListeController());
        commands.put("creation", new PageCreationController());
        commands.put("suppression", new PageSuppressionController());
        commands.put("modification", new PageModificationController());
    }
}