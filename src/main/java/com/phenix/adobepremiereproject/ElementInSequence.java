/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phenix.adobepremiereproject;

import java.io.PrintWriter;

/**
 *
 * @author Edouard
 */
abstract class ElementInSequence extends Element {

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
