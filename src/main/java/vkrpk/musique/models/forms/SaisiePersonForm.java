package vkrpk.musique.models.forms;

import jakarta.servlet.http.HttpServletRequest;

public class SaisiePersonForm {
    private String resultat;

    public void verifForm (HttpServletRequest request) {
        if(request.getParameter("nom").equalsIgnoreCase(request.getParameter("prenom"))) {
            this.setResultat("Le nom et le prénom ne peuvent pas être identiques");
        } else {
            this.setResultat("OK");
        }
    }

    public String getResultat() {
        return resultat;
    }
    public void setResultat(String resultat) {
        this.resultat = resultat;
    }
}
