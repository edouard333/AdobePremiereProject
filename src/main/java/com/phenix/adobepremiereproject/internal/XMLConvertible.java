package com.phenix.adobepremiereproject.internal;

/**
 * Une classe qui implémente {@link XMLConvertible} indique qu'elle peut
 * retourner une représentation XML.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public interface XMLConvertible {

    /**
     * Génère le XML pour le projet.
     *
     * @param order Numéro d'ordre.
     * @return La représentation XML.
     */
    public String toXML(int order);
}
