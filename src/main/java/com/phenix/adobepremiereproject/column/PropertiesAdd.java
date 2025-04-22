package com.phenix.adobepremiereproject.column;

import java.io.PrintWriter;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
@FunctionalInterface
public interface PropertiesAdd {

    /**
     *
     * @param writer Flux où on écrit les informations.
     */
    public void addProperties(PrintWriter writer);
}
