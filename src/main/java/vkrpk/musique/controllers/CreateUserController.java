package vkrpk.musique.controllers;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vkrpk.musique.exception.CommandExecutionException;
import vkrpk.musique.models.User;
import vkrpk.musique.servlet.FrontControllerServlet;

public class CreateUserController implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException
    {
        Argon2 argon2 = Argon2Factory.create(
        Argon2Factory.Argon2Types.ARGON2id,
        32,
        64);
        char[] password = "root".toCharArray();
        String hash = argon2.hash(22, 65536, 1, password);

        EntityManager entityManager = FrontControllerServlet.getEntityManager();

        User user = new User("root", hash);

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return "/createUser.jsp" ;
    }
}