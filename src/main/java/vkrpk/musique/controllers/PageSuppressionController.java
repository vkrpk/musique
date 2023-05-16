package vkrpk.musique.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vkrpk.musique.exception.CommandExecutionException;
import vkrpk.musique.models.Personne;
import java.util.List;
import vkrpk.musique.dao.DaoPersonne;

public class PageSuppressionController implements ICommand {

    private static List<Personne> listePersonnes = new DaoPersonne().findAll();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException
    {
        request.setAttribute("listePersonnes", listePersonnes);

        if(request.getParameterMap().containsKey("supprimerAdherent")){
            Personne personne = new DaoPersonne().findPersonById(Integer.parseInt(request.getParameter("supprimerAdherent")));
            // Personne adherentASupprimer = null;
            // for (Personne p : listePersonnes) {
            //     if (Integer.toString(p.getId()).equals(request.getParameter("supprimerAdherent"))) {
            //         adherentASupprimer = p;
            //         break;
            //     }
            // }
            request.setAttribute("adherentASupprimer", personne);
        }

        if(request.getParameterMap().containsKey("idAdherentASupprimer")){
            // for (Personne p : listePersonnes) {
            //     if (Integer.toString(p.getId()).equals(request.getParameter("idAdherentASupprimer"))) {
            //         listePersonnes.remove(p);
            //         break;
            //     }
            // }
            Personne personne = new DaoPersonne().findPersonById(Integer.parseInt(request.getParameter("idAdherentASupprimer")));

            new DaoPersonne().delete(personne);
            request.setAttribute("adherentASupprimer", null);
            request.setAttribute("suppressionAdherentValide", "Un adhérent a bien été supprimé");
        }
        return "/suppression.jsp" ;
    }
}
