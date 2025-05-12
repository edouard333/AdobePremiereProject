package com.phenix.adobepremiereproject;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public abstract class ElementInSequence extends Element {

    /**
     *
     * @param parent
     * @param name
     * @param typeElement
     */
    public ElementInSequence(Folder parent, String name, TypeElement typeElement) {
        super(parent, name, typeElement);
    }

    /**
     * Code dans la séquence
     *
     * @return
     */
    public abstract String toXMLinSequence();

    /**
     * Données liées à la balise "Media".
     *
     * @return
     */
    public abstract String toXMLMedia();
}
