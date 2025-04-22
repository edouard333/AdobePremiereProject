package com.phenix.adobepremiereproject.exception;

import jakarta.validation.constraints.NotNull;

/**
 * Exception de base pour toutes les erreurs survenant dans le projet.<br>
 * <br>
 * Toutes les exceptions spécifiques doivent hériter de cette classe.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class AdobePremiereProjectException extends Exception {

    /**
     * Construit une {@link AdobePremiereProjectException}.
     */
    public AdobePremiereProjectException() {
        super();
    }

    /**
     * Construit une {@link AdobePremiereProjectException} avec un message.
     *
     * @param message Le message.
     */
    public AdobePremiereProjectException(String message) {
        super(message);
    }

    /**
     * Construit une {@link AdobePremiereProjectException} avec un message et
     * une cause.
     *
     * @param message Le message.
     * @param cause La cause.
     */
    public AdobePremiereProjectException(String message, @NotNull Throwable cause) {
        super(message, cause);
    }
}
