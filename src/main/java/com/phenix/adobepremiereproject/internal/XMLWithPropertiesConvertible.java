package com.phenix.adobepremiereproject.internal;

import com.phenix.adobepremiereproject.column.internal.AddPropertiesXML;
import jakarta.validation.constraints.Null;

/**
 * Une classe qui implémente {@link XMLWithPropertiesConvertible} indique qu'elle
 * peut retourner une représentation XML.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public interface XMLWithPropertiesConvertible {

    /**
     * Retourne une représentation XML de l'élément.
     *
     * @param properties Information à ajouter.
     * @return La représentation XML.
     */
    public String toXML(@Null AddPropertiesXML properties);
}
