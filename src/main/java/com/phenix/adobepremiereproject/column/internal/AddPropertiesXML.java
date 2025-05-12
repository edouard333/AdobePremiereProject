package com.phenix.adobepremiereproject.column.internal;

/**
 * Permet d'indiquer à une fonction qu'on peut ajouter des propriétés au format
 * XML.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
@FunctionalInterface
public interface AddPropertiesXML {

    /**
     * Retourne les informations à ajouter comme "Properties".
     *
     * @return XML à ajouter.
     */
    public String addXMLProperties();
}
