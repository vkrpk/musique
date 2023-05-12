package vkrpk.musique.servlet;
/*
* To change this license header, choose License Headers in Project
Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import vkrpk.musique.controllers.ICommand;
import vkrpk.musique.controllers.PageAccueilController;
import vkrpk.musique.controllers.PageCreationController;
import vkrpk.musique.controllers.PageListeController;
import vkrpk.musique.controllers.PageModificationController;
import vkrpk.musique.controllers.PageSuppressionController;
import vkrpk.musique.exception.CommandExecutionException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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
    private transient Map<String, Object> commands = new HashMap<>();
    private static final String COMPTEUR_PAGE = "compteurPage";
    public EntityManagerFactory entityManagerFactory = null;
    public EntityManager entityManager = null;

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
            if (session.getAttribute(COMPTEUR_PAGE) == null) {
                session.setAttribute(COMPTEUR_PAGE, 0);
            }
            session.setAttribute(COMPTEUR_PAGE, (int) session.getAttribute(COMPTEUR_PAGE) + 1);
            String cmd = request.getParameter("cmd");
            ICommand com = (ICommand) commands.get(cmd);
            urlSuite = com.execute(request, response);
        } catch (CommandExecutionException commandExecutionException) {
            urlSuite = "/erreur.jsp";
            request.setAttribute("message", "Une erreur inconnues est survenue. Veuillez contacter l'administrateur du site.");
            LOGGER.log(Level.SEVERE, commandExecutionException.getMessage());
        } catch (Exception exception) {
            urlSuite = "/erreur.jsp";
            request.setAttribute("message", "Une erreur inconnues est survenue. Veuillez contacter l'administrateur du site.");
            LOGGER.log(Level.SEVERE, exception.getMessage());
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
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("ecfMusique");
            entityManager = entityManagerFactory.createEntityManager();
            // EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ecfMusique");
            // EntityManager entityManager = entityManagerFactory.createEntityManager();
            // entityManager.getTransaction().begin();
            // entityManager.persist(employee);
            // entityManager.getTransaction().commit();
            entityManager.close();
            entityManagerFactory.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } finally {
            if ( entityManager != null ) entityManager.close();
            if ( entityManagerFactory != null ) entityManagerFactory.close();
        }
        // EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        // EntityManager entityManager = entityManagerFactory.createEntityManager();
        // entityManager.getTransaction().begin();
        // entityManager.persist(employee);
        // entityManager.getTransaction().commit();
        // entityManager.close();
        // entityManagerFactory.close();
        // this.entityManagerFactory = Persistence.createEntityManagerFactory("ecfMusique");
        commands.put(null, new PageAccueilController());
        commands.put("liste", new PageListeController());
        commands.put("creation", new PageCreationController());
        commands.put("suppression", new PageSuppressionController());
        commands.put("modification", new PageModificationController());
    }

    public void destroy(){
        // this.entityManager.close();
        // this.entityManagerFactory.close();
    }
}