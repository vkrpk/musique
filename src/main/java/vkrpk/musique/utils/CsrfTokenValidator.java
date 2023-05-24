package vkrpk.musique.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Logger;

public class CsrfTokenValidator {
    private static final Logger LOGGER = Logger.getLogger(CsrfTokenValidator.class.getName());

    private CsrfTokenValidator() {
		throw new IllegalStateException("Utility class");
	}

    public static boolean isValid(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String csrfTokenForm = request.getParameter("csrfToken");
        String csrfTokenSession = (String) session.getAttribute("csrfToken");

        if (csrfTokenForm != null && csrfTokenSession != null && csrfTokenForm.equals(csrfTokenSession)) {
            return true;
        } else {
            LOGGER.info("invalid csrf token");
            return false;
        }
    }
}
