package com.phenix.adobepremiereproject.internal;

/**
 * Une classe qui implémente {@link XMLSimpleConvertible} indique qu'elle peut
 * retourner une représentation XML.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public interface XMLSimpleConvertible {

    /**
     * Retourne la représentation XML de l'élément.
     *
     * @return La représentation XML.
     */
    public String toXML();
}
