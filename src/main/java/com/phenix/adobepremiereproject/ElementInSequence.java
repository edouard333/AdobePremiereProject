package com.phenix.adobepremiereproject;

/**
 * Element dans une séquence.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public abstract class ElementInSequence extends Element {

    /**
     * Initialise un élément.
     *
     * @param parent
     * @param name
     * @param typeElement
     */
    public ElementInSequence(Folder parent, String name, TypeElement typeElement) {
        super(parent, name, typeElement);
    }

    /**
     * Retourne sous-forme XML le code dans la séquence.
     *
     * @return L'XML.
     */
    public abstract String toXMLinSequence();

    /**
     * Retourne sous-forme XML les données liées à la balise "<em>Media</em>".
     *
     * @return L'XML.
     */
    public abstract String toXMLMedia();
}
