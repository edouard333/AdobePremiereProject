package com.phenix.adobepremiereproject;

import java.io.PrintWriter;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public abstract class ElementInSequence extends Element {

    /**
     *
     * @param parent
     * @param name
     * @param type_element
     */
    public ElementInSequence(Folder parent, String name, TypeElement type_element) {
        super(parent, name, type_element);
    }

    /**
     * Code dans la séquence
     *
     * @param writer Flux où écrire les données.
     */
    abstract void inSequence(PrintWriter writer);

    /**
     * Données liées à la balise "Media".
     *
     * @param writer Flux où écrire les données.
     */
    abstract void media(PrintWriter writer);
}
