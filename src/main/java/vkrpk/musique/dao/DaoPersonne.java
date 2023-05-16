package vkrpk.musique.dao;


import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vkrpk.musique.models.Personne;
import vkrpk.musique.servlet.FrontControllerServlet;

public class DaoPersonne {
    private EntityManager entityManager = FrontControllerServlet.getEntityManager();

    public List<Personne> findAll() {
        return entityManager.createQuery("FROM Personne", Personne.class).getResultList();
    }

    public Personne findPersonById(int id) {
        TypedQuery<Personne> query = entityManager.createQuery("SELECT p FROM Personne p WHERE p.id = :id", Personne.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public void delete(Personne personne) {
        entityManager.remove(personne);
    }

    public void save(Personne personne) {
        if(personne.getId() == null) {
            entityManager.persist(personne);
        } else {
            entityManager.merge(personne);
        }
    }
}
