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
     * @param file
     */
    public void addProperties(PrintWriter file);
}
