package vkrpk.musique.models.forms;

import jakarta.servlet.http.HttpServletRequest;

public class SaisiePersonForm {
    private String resultat;

    public void verifForm (HttpServletRequest request) {
    }

    public String getResultat() {
        return resultat;
    }
    public void setResultat(String resultat) {
        this.resultat = resultat;
    }
}
