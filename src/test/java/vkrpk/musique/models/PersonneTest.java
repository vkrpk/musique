package vkrpk.musique.models;

import java.util.Set;

import org.junit.jupiter.api.Test;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;

public class PersonneTest {

    @Test
    public void testSetPrenom(String prenom) {
        Set<ConstraintViolation<Personne>> violations = validator.validate(personne);

    }
}
