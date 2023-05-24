package com.phenix.adobepremiereproject;

import java.io.PrintWriter;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
abstract class ElementInSequence extends Element {

    /**
     *
     * @param parent
     * @param name
     * @param type_element
     */
    public ElementInSequence(Folder parent, String name, int type_element) {
        super(parent, name, type_element);
    }

    /**
     * Code dans la séquence
     *
     * @param file Flux où écrire les données.
     */
    abstract void inSequence(PrintWriter file);

    /**
     * Données lié à la balise "Media".
     *
     * @param file
     */
    abstract void media(PrintWriter file);

}
