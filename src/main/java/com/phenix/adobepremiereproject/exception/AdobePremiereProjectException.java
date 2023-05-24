package com.phenix.adobepremiereproject.exception;

/**
 * Erreur générique spécifique au projet.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class AdobePremiereProjectException extends Exception {

    public AdobePremiereProjectException() {
    }

    /**
     * Erreur avec un message.
     *
     * @param message Le message.
     */
    public AdobePremiereProjectException(String message) {
        super(message);
    }

}
