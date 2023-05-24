package vkrpk.musique.dao;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vkrpk.musique.exception.ExceptionDAO;
import vkrpk.musique.models.User;
import vkrpk.musique.servlet.FrontControllerServlet;

public class DaoUser {
    private jakarta.persistence.EntityManager entityManager = FrontControllerServlet.getEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    public User findByPseudo(String pseudo) throws ExceptionDAO {
        TypedQuery<User> query = null;
        try {
            transaction.begin();
            query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", pseudo);
            transaction.commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                transaction.rollback();
            }
            throw new ExceptionDAO("Erreur lors de la recherche d'une personne : " + e.getMessage(), 5);
        }
        return query.getSingleResult();
    }
}
