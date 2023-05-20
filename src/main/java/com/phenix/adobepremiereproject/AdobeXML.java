package com.phenix.adobepremiereproject;

import java.io.PrintWriter;

/**
 * Indique ce qui doit être implémenté pour que l'XML Adobe soit OK.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public interface AdobeXML {

    /**
     * Génère le XML pour le projet.
     *
     * @param file Flux où on ajoute les informations.
     * @param order Numéro d'ordre.
     */
    public void toXML(PrintWriter file, int order);
}
