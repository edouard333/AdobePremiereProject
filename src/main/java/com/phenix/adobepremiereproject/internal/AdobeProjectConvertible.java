package com.phenix.adobepremiereproject.internal;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public interface AdobeProjectConvertible {

    /**
     * Retourne la valeur pour le projet Adobe.
     *
     * @return La valeur pour le projet Adobe.
     */
    @NotNull
    public String toAdobeProject();
}
