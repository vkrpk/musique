package vkrpk.musique.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vkrpk.musique.exception.ExceptionDAO;
import vkrpk.musique.models.Personne;
import vkrpk.musique.servlet.FrontControllerServlet;

public class DaoPersonne {
    private EntityManager entityManager = FrontControllerServlet.getEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    public List<Personne> findAll() throws ExceptionDAO {
        List<Personne> listePersonnes = null;
        try {
            transaction.begin();
            listePersonnes = entityManager.createQuery("FROM Personne", Personne.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                transaction.rollback();
            }
            throw new ExceptionDAO("Erreur lors de la recherche de la liste des personnes : " + e.getMessage(), 5);
        }
        return listePersonnes;
    }

    public Personne findPersonById(int id) throws ExceptionDAO {
        TypedQuery<Personne> query = null;
        try {
            transaction.begin();
            query = entityManager.createQuery("SELECT p FROM Personne p WHERE p.id = :id", Personne.class);
            query.setParameter("id", id);
            transaction.commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                transaction.rollback();
            }
            throw new ExceptionDAO("Erreur lors de la recherche d'une personne : " + e.getMessage(), 5);
        }
        return query.getSingleResult();
    }

    public void delete(Personne personne) throws ExceptionDAO {
        try {
            transaction.begin();
            entityManager.remove(personne);
            transaction.commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                transaction.rollback();
            }
            throw new ExceptionDAO("Erreur lors de la suppression de la personne : " + e.getMessage(), 5);
        }
    }

    public void save(Personne personne) throws ExceptionDAO {
        try {
            transaction.begin();
            if(personne.getId() == null) {
                entityManager.persist(personne);
            } else {
                entityManager.merge(personne);
            }
            entityManager.flush();
            transaction.commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                transaction.rollback();
            }
            throw new ExceptionDAO("Erreur lors de la sauvegarde de la personne : " + e.getMessage(), 5);
        }
    }
}
